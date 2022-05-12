package com.example.backendprojectone.models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class BuyOrder {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Item item;

    //Only for tests
    public BuyOrder(long id, Customer customer, Item item) {
        this.id = id;
        this.customer = customer;
        this.item = item;
    }

    public BuyOrder() {

    }
}