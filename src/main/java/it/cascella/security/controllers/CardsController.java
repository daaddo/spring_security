package it.cascella.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CardsController {

    @GetMapping("/cards")
    public String cards() {
        return "Cards";
    }

}
