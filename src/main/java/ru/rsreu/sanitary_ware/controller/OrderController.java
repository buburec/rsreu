package ru.rsreu.sanitary_ware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.sanitary_ware.dto.ItemDTO;
import ru.rsreu.sanitary_ware.dto.OrderDTO;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/submitOrder")
    public String showOrderForm(Model model) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setItem(new ItemDTO());
        model.addAttribute("orderDTO", new OrderDTO());
        return "order_form";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@Valid @ModelAttribute("orderDTO") OrderDTO orderDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order_form";
        }

        model.addAttribute("message", "отумлцоту");
        return "order_success";
    }

}

