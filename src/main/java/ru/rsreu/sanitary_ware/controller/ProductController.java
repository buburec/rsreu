package ru.rsreu.sanitary_ware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
//    @GetMapping("")
//    public String getProducts(Model model) {
//        List<ItemDTO> itemDTOList = new ArrayList<>() {{
//            add(new ItemDTO(0L, "Item1", 100.0, 100));
//            add(new ItemDTO(1L, "Item2", 200.0, 200));
//            add(new ItemDTO(2L, "Item3", 300.0, 300));
//        }};
//        model.addAttribute("itemDTOList", itemDTOList);
//        return "product";
//    }
}

