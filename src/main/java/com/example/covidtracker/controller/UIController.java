package com.example.covidtracker.controller;

import com.example.covidtracker.services.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

        @Autowired
        TrackerService trackerService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStats",trackerService.getFinalstats());
        return "home";
    }
}
