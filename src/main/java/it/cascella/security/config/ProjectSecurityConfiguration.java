package it.cascella.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder;

@Configuration
public class ProjectSecurityConfiguration {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account","balance").authenticated()//necessitano del form login NON NECESSITANO DEL /
                .requestMatchers("/cards","/contact","/error").permitAll()//non necessitano di autenticazione
        );
        http.formLogin(withDefaults());
        //http.formLogin(flc -> flc.disable()); //this will disable the form login

        http.httpBasic(withDefaults());
        //http.httpBasic(hbc -> hbc.disable()); //this will disable the http basic authentication
        return http.build();
    }

    @Profile("default")
    @Bean
    public UserDetailsService getUserDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password("{bcrypt}$2a$12$7NGgXefpV2x3CGMfJRTZO.CNGMbjNTmsbKFLrr20YewvpTda9XqCK")// password
                .authorities("read")
                .build();
        UserDetails admin = withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Profile("dev")
    @Bean
    public UserDetailsService getDevUserDetailsService() {
        UserDetails user = withDefaultPasswordEncoder()
                .username("dev")
                .password("dev")// password
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
