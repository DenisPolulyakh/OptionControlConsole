package com.traderobot.www.model;

import jakarta.persistence.*;

import java.io.Serializable;


@MappedSuperclass
public abstract class GenericEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}