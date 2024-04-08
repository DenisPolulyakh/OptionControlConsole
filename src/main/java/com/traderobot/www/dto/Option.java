package com.traderobot.www.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Option {

    //Название базового актива
    String activeName;

    //Цена исполнения
    String strike;

    //Тип расчетов
    String calcType;

    //Месяц исполнения опционов
    String month;

    //Дата исполнения
    String date;

    //Дней до экспирации
    private Integer DaysExpire;

    //Дата экспирации
    private LocalDate DateExpire;




}
