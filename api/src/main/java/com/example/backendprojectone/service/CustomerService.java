package com.example.backendprojectone.service;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.example.backendprojectone.security.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerService(@Lazy CustomerRepository customerRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerCustomer(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setId(customer.getId());
        newCustomer.setName(customer.getName());
        newCustomer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(newCustomer);
    }

    public Customer findCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Customer not found"));
    }

    public boolean login(Customer customerInDatabase, CharSequence rawPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, customerInDatabase.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = new Customer();
        return new User(customer.getName(), customer.getPassword(), Collections.emptyList());
    }
}
