package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.repositories.BuyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class BuyOrderController {
    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @RequestMapping()
    public Iterable<BuyOrder> getAllBuyOrders() {
        return buyOrderRepository.findAll();
    }

    @RequestMapping("/:{customerId}")
    public Iterable<BuyOrder> getAllBuyOrdersFromCustomerId(@PathVariable long customerId) {
        return buyOrderRepository.findAllByCustomer_Id(customerId);
    }
}
