package ru.rsreu.sanitary_ware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rsreu.sanitary_ware.dto.CartDto;
import ru.rsreu.sanitary_ware.service.CartService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public String initCart(Model model, @RequestParam Long id) {
        CartDto cartDto = cartService.initCart(id);
        model.addAttribute("cart", cartDto);
        return "cart";
    }
}

