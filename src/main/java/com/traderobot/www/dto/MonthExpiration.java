package com.traderobot.www.dto;

public enum MonthExpiration {
    A("Январь"),
    B("Февраль"),
    C("Март"),
    D("Апрель"),
    E("Май"),
    F("Июнь"),
    G("Июль"),
    H("Август"),
    I("Сентябрь"),
    J("Октябрь"),
    K("Ноябрь"),
    L("Декабрь"),
    M("Январь"),
    N("Февраль"),
    O("Март"),
    P("Апрель"),
    Q("Май"),
    R("Июнь"),
    S("Июль"),
    T("Август"),
    U("Сентябрь"),
    V("Октябрь"),
    W("Ноябрь"),
    X("Декабрь");

    private String value;

    MonthExpiration(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    }
