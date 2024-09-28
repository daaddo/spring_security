package it.cascella.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BalanceController {

    @GetMapping("/balance")
    public String balance() {
        return "Balance";
    }
}
