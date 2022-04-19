package com.example.backendprojectone.wrapper;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.models.Item;
import lombok.Data;

@Data
public class CustomerItemWrapper {
    private Customer customer;
    private Item item;

    //Only for test
    public CustomerItemWrapper(Customer customer, Item item) {
        this.customer = customer;
        this.item = item;
    }
}
