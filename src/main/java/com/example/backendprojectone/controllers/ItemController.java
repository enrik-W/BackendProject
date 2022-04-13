package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.BuyOrderRepository;
import com.example.backendprojectone.repositories.ItemRepository;
import com.example.backendprojectone.response.Response;
import com.example.backendprojectone.wrapper.CustomerItemWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response addItem(@RequestBody Item i) {
        Response res = new Response("Item added", Boolean.FALSE);
        itemRepository.save(i);
        res.setStatus(Boolean.TRUE);
        return res;
    }

    @PostMapping("/buy")
    public Response addBuyOrder(@RequestBody CustomerItemWrapper customerItem) {
        BuyOrder bo = new BuyOrder();
        Response res = new Response("Buy order added", Boolean.FALSE);
        bo.setCustomer(customerItem.getCustomer());
        bo.setItem(customerItem.getItem());
        buyOrderRepository.save(bo);
        res.setStatus(Boolean.TRUE);
        return res;
    }
}
