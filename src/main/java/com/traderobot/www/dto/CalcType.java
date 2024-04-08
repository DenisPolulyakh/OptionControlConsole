package com.traderobot.www.dto;

public enum CalcType {
    A("американский уплата премии"),
    B("американский маржируемый"),
    C("европейский уплата премии");


    private String value;

    CalcType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    }
