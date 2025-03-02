package com.luscaer.Scrum_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageViewController {
    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("activePage", "home");
        return "home-page";
    }
}
