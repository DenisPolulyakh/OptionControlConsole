package com.traderobot.www.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.traderobot.www.config.LocalDateTypeAdapter;
import com.traderobot.www.dto.Commands;
import com.traderobot.www.dto.OptionSecurity;
import com.traderobot.www.dto.QuikRequest;
import com.traderobot.www.dto.QuikResponse;
import com.traderobot.www.intf.OptionCache;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OptionCacheImpl implements OptionCache {

    private final SocketClientImpl socketClient;
    private List<OptionSecurity> options = new ArrayList<>();

    public OptionCacheImpl(SocketClientImpl socketClient) {
        this.socketClient = socketClient;
        //Первичная инициализация
        //options.addAll(requestToQuik());
    }

    @Override
    public List<OptionSecurity> getOptions() {
        log.info("Пока не опрашиваем QUIK");
        return Collections.emptyList();
        /*if (options == null || options.isEmpty()) {
            log.info("Кеш пуст, делаем запрос в QUIK");
            return requestToQuik();
        }
        log.info("Данные из кеша");
        return options;*/
    }

    private List<OptionSecurity> requestToQuik() {
        QuikRequest quikRequest = QuikRequest.newBuilder().addCommand(Commands.GET_OPTIONS.name()).build();
        QuikResponse quikResponse = socketClient.sendMessage(quikRequest);
        if (quikResponse.isSuccess() && !StringUtils.isEmpty(quikResponse.getPayload())) {
            Type listType = new TypeToken<List<OptionSecurity>>() {
            }.getType();

            Gson gson = new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();
            List<OptionSecurity> optionSecurities = gson.fromJson(quikResponse.getPayload(), listType);
            options.clear();
            options.addAll(optionSecurities);
        }
        return options;
    }
}

