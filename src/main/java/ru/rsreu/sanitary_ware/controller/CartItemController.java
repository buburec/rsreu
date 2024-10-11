package ru.rsreu.sanitary_ware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.sanitary_ware.service.CartItemService;

@Controller
@RequestMapping("/cart_item")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

//    @GetMapping("/add{id}")
//    public String pushItem(Model model, @PathVariable Long id) {
//        cartItemService.pushItem(id);
//    }
}

