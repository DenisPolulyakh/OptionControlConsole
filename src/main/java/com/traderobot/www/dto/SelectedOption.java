package com.traderobot.www.dto;


import lombok.Getter;
import lombok.Setter;


/**
 * Данные выбранного опциона
 */
@Setter
@Getter
public class SelectedOption {

    /*
     * Название команды
     */
    private String command;

    /**
     * Тип опциона
     */
    private String typeOption;

    /**
     * Тип базового актива
     */
    private String typeBase;

    /**
     * Счет
     */
    private String account;

    /**
     * Код опциона
     */
    private String optionCode;

    /**
     * Количество контрактов
     */
    private String contracts;

    /**
     * Тип сделки
     */
    private String typeOrder;

    /**
     * Минимальный отступ от цены
     */
    private String minPrice;

}
