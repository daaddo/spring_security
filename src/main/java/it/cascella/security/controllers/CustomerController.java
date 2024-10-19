package it.cascella.security.controllers;

import it.cascella.security.entities.Customer;
import it.cascella.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody Customer customer){
        System.out.println("Registrando...");
        try{
            String hashedPasw = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashedPasw);
            Customer save = customerRepository.save(customer);
            if (save.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).body("user succesfully created");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user NOT created");
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/register")
    public String contact() {
        return "Register";
    }

}
