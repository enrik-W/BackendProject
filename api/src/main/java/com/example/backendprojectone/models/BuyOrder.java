package com.example.backendprojectone.models;

import javax.persistence.*;

@Entity
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
