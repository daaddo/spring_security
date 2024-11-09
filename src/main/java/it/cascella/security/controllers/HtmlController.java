package it.cascella.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/denied")
    public String denied(){
        return "denied";
    }
    @GetMapping("/timeout")
    public String timeout(){
        return "timeout";
    }
}
