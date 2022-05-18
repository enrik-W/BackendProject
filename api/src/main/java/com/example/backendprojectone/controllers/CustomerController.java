package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addCustomer(@RequestBody Customer c) {
        customerService.registerCustomer(c);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created");
    }

    @PostMapping("login") //TODO: Add jwt
    public String login(@RequestBody Customer loginDetails) {
        Customer customerInDatabase;

        if (customerRepository.existsCustomerByName(loginDetails.getName())) {
            customerInDatabase = customerRepository.findCustomerByName(loginDetails.getName());
        } else {
            return "Customer not found";
        }

        if (customerService.login(customerInDatabase, loginDetails.getPassword())) {
            return "Log in successful";
        }
        return "Wrong password";
    }
}
