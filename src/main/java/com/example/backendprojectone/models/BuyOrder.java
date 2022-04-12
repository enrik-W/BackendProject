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
}
