package com.traderobot.www.impl;

import com.google.gson.Gson;
import com.traderobot.www.dto.PriceResponse;
import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.dto.QuikResponse;
import com.traderobot.www.intf.PriceService;
import io.micrometer.common.util.StringUtils;
import jakarta.inject.Singleton;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final SocketClientImpl socketClient;

    public PriceServiceImpl(SocketClientImpl socketClient) {
        this.socketClient = socketClient;
    }

    @Override
    public PriceResponse getPrices(QuikRequest quikRequest) {
        PriceResponse priceResponse = new PriceResponse();
        QuikResponse quikResponse = socketClient.sendMessage(quikRequest);
        if (quikResponse.isSuccess() && !StringUtils.isEmpty(quikResponse.getPayload())) {
            priceResponse = new Gson().fromJson(quikResponse.getPayload(), PriceResponse.class);
        }
        return priceResponse;
    }
}
