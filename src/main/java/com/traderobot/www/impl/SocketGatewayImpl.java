package com.traderobot.www.impl;


import com.google.gson.Gson;
import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.dto.QuikResponse;
import com.traderobot.www.dto.SocketResponse;
import com.traderobot.www.exception.SocketException;
import com.traderobot.www.intf.SocketGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;


@Slf4j
@Service
public class SocketGatewayImpl implements SocketGateway {

    private static final int DEFAULT_ATTEMPT = 10;

    private Socket clientSocket;
    private BufferedWriter toSocketServer;
    private BufferedReader fromSocketServer;
    private final Gson gson;

    public SocketGatewayImpl() {
        this.gson = new Gson();
    }

    @Override
    public QuikResponse send(QuikRequest quikRequest) {
        QuikResponse quikResponse;
        try {
            quikResponse = sendToSocket(DEFAULT_ATTEMPT, gson.toJson(quikRequest));
        } catch (SocketException e) {
            return QuikResponse.fail("Ошибка отправки через сокет: " + e.getMessage());
        }

        return quikResponse;
    }

    private QuikResponse sendToSocket(int attemptCount, String message) throws SocketException {
        String serverResponse = "";
        attemptCount=0;
        if (attemptCount <= 0) {
            return QuikResponse.fail("Ошибка отправки серверу, возможно он выключен");
        }
        try {

                clientSocket = new Socket("localhost", 3585);

            // для чтения ответа сервера
            fromSocketServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // запрос серверу
            toSocketServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            toSocketServer.write(message + "\n"); // отправляем сообщение на сервер
            toSocketServer.flush();
            serverResponse = fromSocketServer.readLine();// читаем ответ
            SocketResponse socketResponse = gson.fromJson(serverResponse, SocketResponse.class);
            return QuikResponse.success(socketResponse.getPayload());
        } catch (Exception e) {
            log.info("Ошибка передачи сообщения через сокет", e);
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                    clientSocket = null;
                }
                if (toSocketServer != null) {
                    toSocketServer.close();
                }
                if (fromSocketServer != null) {
                    fromSocketServer.close();
                }
            } catch (IOException ex) {
                String errorMessage = "Ошибка закрытия потоков";
                log.info(errorMessage, ex);
                throw new SocketException(errorMessage, ex);
            }
            //попробуем еще раз
            return  sendToSocket(attemptCount - 1, message);
        }
        //return QuikResponse.fail("Ошибка отправки серверу, возможно он выключен");
    }

}

