package com.example.myportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/index")
    public String index(Model model) {
        logger.info("index --------------------------------------");

        model.addAttribute("data", "Hello, Spring from IntelliJ! :)");
        model.addAttribute("name", "Jay");
        return "index";
    }

    @GetMapping(value = "/")
    public String home(Locale locale, Model model) {

        return "home";
    }

}
