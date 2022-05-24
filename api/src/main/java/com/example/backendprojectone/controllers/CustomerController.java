package com.example.backendprojectone.controllers;

import com.example.backendprojectone.jwt.JwtUtil;
import com.example.backendprojectone.models.AuthenticationResponse;
import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

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

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Customer customer) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userdetails = customerService.loadUserByUsername(customer.getName());
        final String jwt = jwtTokenUtil.generateToken(userdetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
