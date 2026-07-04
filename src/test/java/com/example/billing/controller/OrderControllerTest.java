package com.example.billing.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.billing.model.BillingOrder;
import com.example.billing.service.OrderService;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldSaveOrderAndRedirectHome() throws Exception {
        BillingOrder saved = new BillingOrder();
        saved.setCustomerName("Ada");
        saved.setItemName("Support");
        saved.setQuantity(2);
        saved.setUnitPrice(50.0);
        saved.setTotalAmount(100.0);
        saved.setStatus("PAID");

        when(orderService.createOrder(any(BillingOrder.class))).thenReturn(saved);
        when(orderService.getAllOrders()).thenReturn(List.of(saved));

        mockMvc.perform(post("/orders")
                .param("customerName", "Ada")
                .param("itemName", "Support")
                .param("quantity", "2")
                .param("unitPrice", "50"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeExists("successMessage"));

        verify(orderService).createOrder(any(BillingOrder.class));
    }
}
