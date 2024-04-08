package com.traderobot.www.dto;

public enum Active {
    RI("Индекс РТС"),
    Si("курс доллар США – российский рубль"),
    SR("ПАО Сбербанк (о.а.)"),
    GZ("ПАО \"Газпром\" (о.а.)"),
    BR("нефть BRENT");

    private String value;

    Active(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    }
