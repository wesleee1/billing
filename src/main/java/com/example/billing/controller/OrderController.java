package com.example.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.billing.model.BillingOrder;
import com.example.billing.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("order", new BillingOrder());
        model.addAttribute("orders", orderService.getAllOrders());
        return "index";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute BillingOrder order, RedirectAttributes redirectAttributes) {
        try {
            orderService.createOrder(order);
            redirectAttributes.addFlashAttribute("successMessage", "Order saved successfully.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unable to save order: " + ex.getMessage());
        }
        return "redirect:/";
    }
}
