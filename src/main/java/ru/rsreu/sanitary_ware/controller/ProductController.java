package ru.rsreu.sanitary_ware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.sanitary_ware.dto.ProductDto;
import ru.rsreu.sanitary_ware.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public String getProducts(Model model) {
        List<ProductDto> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDto productDto) {
        productService.addProduct(productDto);
        return "redirect:/products/list";
    }

}

