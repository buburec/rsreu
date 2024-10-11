package ru.rsreu.sanitary_ware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("name", "Sanitary Ware");
        return "index";
    }

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:home";
    }
}
