package com.example.myportal.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
@Controller
public class    HomeController {

    @GetMapping("/index")
    public String index(Model model) {
        log.info("index --------------------------------------");

        model.addAttribute("data", "Hello, Spring from IntelliJ! :)");
        model.addAttribute("name", "Jay");
        return "index";
    }

    @GetMapping(value = "/")
    public String home(Locale locale, Model model) {
        log.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        log.info("date - " + formattedDate);

        model.addAttribute("serverTime", formattedDate );

        return "home";
    }

}
