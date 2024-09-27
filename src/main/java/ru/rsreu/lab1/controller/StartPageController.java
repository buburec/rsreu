package ru.rsreu.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("name", "магазин сантехники");
        return "home";
    }

    @GetMapping("/")
    public String redirect() {
        return "home";
    }
}
