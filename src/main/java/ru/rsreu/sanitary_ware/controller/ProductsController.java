package ru.rsreu.sanitary_ware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.sanitary_ware.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {
    @GetMapping("/")
    public String getProducts(Model model) {
        List<ItemDTO> itemDTOList = new ArrayList<>() {{
            add(new ItemDTO("Item1", 100.0, 100));
            add(new ItemDTO("Item2", 200.0, 200));
            add(new ItemDTO("Item3", 300.0, 300));
        }};
        model.addAttribute("itemDTOList", itemDTOList);
        return "product";
    }
}

