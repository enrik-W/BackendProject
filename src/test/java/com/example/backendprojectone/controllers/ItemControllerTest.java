package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.BuyOrder;
import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.models.Item;
import com.example.backendprojectone.repositories.BuyOrderRepository;
import com.example.backendprojectone.repositories.ItemRepository;
import com.example.backendprojectone.wrapper.CustomerItemWrapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ItemRepository mockItemRepository;

    @MockBean
    private BuyOrderRepository mockBuyOrderRepository;

    @BeforeEach
    public void init() {
        Item i1 = new Item(1L, "A thing");
        Item i2 = new Item(2L, "Bicycle");
        Item i3 = new Item(3L, "Phone");

        when(mockItemRepository.findAll()).thenReturn(Arrays.asList(i1, i2, i3));
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(i1));
    }

    @Test
    void getAllItemsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"A thing\"},{\"id\":2,\"name\":\"Bicycle\"}," +
                        "{\"id\":3,\"name\":\"Phone\"}]"));
    }

    @Test
    void getItemByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items/:1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"A thing\"}"));
    }

    @Test
    void addItemTest() throws Exception {
        Item i4 = new Item(4L, "Keyboard");
        when(mockItemRepository.save(i4)).thenReturn(i4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(i4));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Item added\",\"status\":true}"));
    }

    @Test
    void addBuyOrderTest() throws Exception {
        BuyOrder bo = new BuyOrder();
        Customer tempCustomer = new Customer(1L, "Henrik");
        Item tempItem = new Item();
        CustomerItemWrapper ci = new CustomerItemWrapper(tempCustomer, tempItem);
        when(mockBuyOrderRepository.save(bo)).thenReturn(bo);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/items/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ci));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Buy order added\",\"status\":true}"));
    }
}