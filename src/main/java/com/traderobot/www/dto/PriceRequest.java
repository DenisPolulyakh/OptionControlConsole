package com.traderobot.www.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequest {

    /*
     * Название команды
     */
    private String command;

    /**
     * Код опциона
     */
    private String optionCode;


}
