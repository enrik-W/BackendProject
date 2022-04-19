package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @Autowired
    private ObjectMapper mapper;

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
    void getCustomerByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/:1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Henrik\"}"));
    }

    @Test
    void addCustomerTest() throws Exception {
        Customer c4 = new Customer(4L, "Bob");
        when(mockCustomerRepository.save(c4)).thenReturn(c4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(c4));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Customer added\",\"status\":true}"));
    }
}