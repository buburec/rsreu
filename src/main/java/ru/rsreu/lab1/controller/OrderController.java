package ru.rsreu.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.lab1.dto.OrderDTO;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/orderForm")
    public String showOrderForm(Model model) {
        model.addAttribute("orderDTO", new OrderDTO());
        return "order_form";
    }

    @PostMapping("/submit_order")
    public String submitOrder(@Valid @ModelAttribute("orderDTO") OrderDTO orderDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order_form";
        }

        model.addAttribute("message", "Заказ успешно оформлен");
        return "order_success";
    }

}

