package com.investmentsportal.portal.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Tag(name = "Home")
public class HomeController {
    @GetMapping
    public String findHome(){
        return "Welcome to portfolio management";
    }
}
