package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping()
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping("/:{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerRepository.findById(id).get();
    }

    @PostMapping("/sign_up")
    public Response addCustomer(@RequestBody Customer c) {
        Response res = new Response("Customer added", Boolean.FALSE);
        customerRepository.save(c);
        res.setStatus(Boolean.TRUE);
        return res;
    }

    @PostMapping("login")
    public Response login(@RequestBody Customer c) {
        Response res = new Response("Login successful", Boolean.FALSE);
        return res;
    }
}
