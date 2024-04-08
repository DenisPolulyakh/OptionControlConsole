package com.traderobot.www.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OptionSecurity {
    private String Optionbase;
    /**
     * Тип опицона
     */
    private String Optiontype;
    /**
     * Код опциона
     */
    private String SecCode;
    /**
     * Полное наименование опциона
     */
    private String Name;
    /**
     * Дней до экспирации
     */
    private Integer DaysExpire;
    /**
     * Дата экспирации
     */
    private LocalDate DateExpire;
}
