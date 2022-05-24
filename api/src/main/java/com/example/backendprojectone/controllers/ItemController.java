package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.BuyOrderRepository;
import com.example.backendprojectone.repositories.ItemRepository;
import com.example.backendprojectone.wrapper.CustomerItemWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BuyOrderRepository buyOrderRepository;

    @RequestMapping()
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @RequestMapping("/:{id}")
    public Item getItemById(@PathVariable long id) {
        return itemRepository.findById(id).get();
    }

    @PostMapping()
    public ResponseEntity<String> addItem(@RequestBody Item i) {
        itemRepository.save(i);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created");
    }

    @PostMapping("/buy")
    public ResponseEntity<String> addBuyOrder(@RequestBody CustomerItemWrapper customerItem) {
        BuyOrder bo = new BuyOrder();
        bo.setCustomer(customerItem.getCustomer());
        bo.setItem(customerItem.getItem());
        buyOrderRepository.save(bo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Buy order created");
    }
}
