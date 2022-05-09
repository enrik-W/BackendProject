package com.example.backendprojectone.wrapper;


import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.models.Item;

public class CustomerItemWrapper {
    private Customer customer;
    private Item item;

    //Only for test
    public CustomerItemWrapper(Customer customer, Item item) {
        this.customer = customer;
        this.item = item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Item getItem() {
        return item;
    }
}
