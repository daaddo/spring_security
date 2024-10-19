package it.cascella.security.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class OurPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return true;
    }

}
