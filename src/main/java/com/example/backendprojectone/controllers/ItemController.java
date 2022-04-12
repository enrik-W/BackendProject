package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.BuyOrderRepository;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.repositories.ItemRepository;
import com.example.backendprojectone.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

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
    public Response addBuyOrder(@RequestParam long customer, @RequestParam long item) {
        BuyOrder bo = new BuyOrder();
        Response res = new Response("Buy order added", Boolean.FALSE);
        bo.setCustomer(customerRepository.findById(customer).get());
        bo.setItem(itemRepository.findById(item).get());
        buyOrderRepository.save(bo);
        res.setStatus(Boolean.TRUE);
        return res;
    }
}
