package com.example.backendprojectone.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    //Only for tests
    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

    }
}
