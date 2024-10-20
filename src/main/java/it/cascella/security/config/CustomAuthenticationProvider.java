package it.cascella.security.config;

import it.cascella.security.service.CustomerUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Profile("database")
@AllArgsConstructor
@NoArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private CustomerUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Value("${app.under-maintenance}")
    private boolean underMainteinance;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (underMainteinance){
            throw new BadCredentialsException("Under maintanence");
        }
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        int age = userDetailsService.getCustomerAgeByEmail(username);
        boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
        if (matches){
            if (age < 18) {
                throw new BadCredentialsException("user not old enough");
            }
            
            return new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities()
            );
        }
        else {
            authentication.setAuthenticated(false);
            throw new BadCredentialsException("password invalid");

        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
