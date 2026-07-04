package com.example.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.billing.model.BillingOrder;
import com.example.billing.repository.OrderRepository;
import com.example.billing.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldCalculateTotalAndPersistOrder() {
        BillingOrder order = new BillingOrder();
        order.setCustomerName("Ava");
        order.setItemName("Premium Support");
        order.setQuantity(3);
        order.setUnitPrice(120.0);

        when(orderRepository.save(any(BillingOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BillingOrder saved = orderService.createOrder(order);

        assertEquals(360.0, saved.getTotalAmount());
        assertEquals("PAID", saved.getStatus());
    }
}
