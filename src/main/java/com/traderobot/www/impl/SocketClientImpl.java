package com.traderobot.www.impl;


import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.dto.QuikResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SocketClientImpl {


    private final SocketGatewayImpl socketGateway;

    public SocketClientImpl(SocketGatewayImpl socketGateway) {
        this.socketGateway = socketGateway;
    }


    public QuikResponse sendMessage(QuikRequest quikRequest) {
        return socketGateway.send(quikRequest);
    }





}
