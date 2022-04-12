package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.ItemRepository;
import com.example.backendprojectone.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

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
}
