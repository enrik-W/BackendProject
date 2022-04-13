package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.BuyOrderRepository;
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
class BuyOrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BuyOrderRepository mockOrderRepository;

    @BeforeEach
    public void init() {
        Customer c1 = new Customer(10L, "Henrik");
        Customer c2 = new Customer(11L, "Felicia");
        Customer c3 = new Customer(12L, "Lennart");

        Item i1 = new Item(20L, "A thing");
        Item i2 = new Item(21L, "Bicycle");
        Item i3 = new Item(22L, "Phone");

        BuyOrder bo1 = new BuyOrder(1L, c1, i1);
        BuyOrder bo2 = new BuyOrder(2L, c2, i2);
        BuyOrder bo3 = new BuyOrder(3L, c3, i3);
        BuyOrder bo4 = new BuyOrder(4L, c1, i3);

        when(mockOrderRepository.findAllByCustomer_Id(10L)).thenReturn(Arrays.asList(bo1, bo4));
        when(mockOrderRepository.findAll()).thenReturn(Arrays.asList(bo1, bo2, bo3, bo4));
    }

    @Test
    void getAllBuyOrdersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"customer\":{\"id\":10,\"name\":\"Henrik\"},\"item\":{\"id\":20,\"name\":\"A thing\"}}," +
                        "{\"id\":2,\"customer\":{\"id\":11,\"name\":\"Felicia\"},\"item\":{\"id\":21,\"name\":\"Bicycle\"}}," +
                        "{\"id\":3,\"customer\":{\"id\":12,\"name\":\"Lennart\"},\"item\":{\"id\":22,\"name\":\"Phone\"}}," +
                        "{\"id\":4,\"customer\":{\"id\":10,\"name\":\"Henrik\"},\"item\":{\"id\":22,\"name\":\"Phone\"}}]"));
    }

    @Test
    void getAllBuyOrdersFromCustomerIdTest() {
    }
}