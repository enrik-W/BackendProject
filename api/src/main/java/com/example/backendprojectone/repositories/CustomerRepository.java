package com.example.backendprojectone.repositories;

import com.example.backendprojectone.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
