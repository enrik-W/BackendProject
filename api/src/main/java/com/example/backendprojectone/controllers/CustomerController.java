package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.response.Response;
import com.example.backendprojectone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping("/:{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/sign_up")
    public void addCustomer(@RequestBody Customer c) {
        customerService.registerCustomer(c);
    }

    @PostMapping("login")
    public Response login(@RequestBody Customer c) {
        Response res = new Response("Login successful", Boolean.FALSE);
        return res;
    }
}
