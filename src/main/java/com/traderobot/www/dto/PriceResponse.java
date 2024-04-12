package com.traderobot.www.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceResponse {

    /*
     * Теоретическая цена
     */
    private String theor;

    /**
     * Цена спроса
     */
    private String bid;

    /**
     * Цена предложения
     */
    private String offer;

    /**
     * Стоимость шага цены
     */
    private String priceStep;

    /**
     * Шаг цены
     */
    private String step;
}
