package com.traderobot.www.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property")
@Getter
@Setter
public class Property extends GenericEntity<Long> {

    //Торговый счет
    public static final String ACCOUNT="ACCOUNT";
    /**
     * Ключ
     */
    @Column(name = "key")
    private String key;
    /**
     * Значени
     */
    @Column(name = "value")
    private String value;

    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
}
