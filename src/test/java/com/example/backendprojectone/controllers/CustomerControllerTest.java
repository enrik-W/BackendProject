package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @BeforeEach
    public void init() {
        Customer c1 = new Customer(1L, "Henrik");
        Customer c2 = new Customer(2L, "Felicia");
        Customer c3 = new Customer(3L, "Lennart");

        when(mockCustomerRepository.findAll()).thenReturn(Arrays.asList(c1, c2, c3));
        when(mockCustomerRepository.findById(1L)).thenReturn(Optional.of(c1));
    }

    @Test
    void getAllCustomersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Henrik\"},{\"id\":2,\"name\":\"Felicia\"}," +
                        "{\"id\":3,\"name\":\"Lennart\"}]"));
    }

    @Test
    void getCustomerByIdTest() throws Exception{
    }

    @Test
    void addCustomerTest() throws Exception {
    }
}