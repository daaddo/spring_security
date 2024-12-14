package it.cascella.security.config;

import it.cascella.security.auth.CustomBasicAuthenticationEntryPoint;
import it.cascella.security.handlers.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfiguration {
/*
"/cards","/contact","/error","/register"
*/

    @Profile("!https")
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cc -> cc.disable());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account","balance").authenticated()//necessitano del form login NON NECESSITANO DEL /
                .requestMatchers("/cards","/contact","/error","/register","/login","/denied","/timeout").permitAll()//non necessitano di autenticazione
        );
        http.httpBasic(httpCustomizer ->{
            httpCustomizer.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint());
        });
        http.exceptionHandling(eHC ->eHC.accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied"));
        http.formLogin(withDefaults());
        //http.formLogin(flc -> flc.disable()); //this will disable the form login
        http.sessionManagement(sm ->sm.invalidSessionUrl("/timeout"));
        http.httpBasic(withDefaults());
        //http.httpBasic(hbc -> hbc.disable()); //this will disable the http basic authentication
        return http.build();
    }
    @Profile("https")
    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

        http.requiresChannel((requiresChannel) -> requiresChannel.anyRequest().requiresSecure());
        http.csrf(cc -> cc.disable());
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account","balance").authenticated()//necessitano del form login NON NECESSITANO DEL /
                .requestMatchers("/cards","/contact","/error","/register").permitAll()//non necessitano di autenticazione
        );
        http.formLogin(withDefaults());
        //http.formLogin(flc -> flc.disable()); //this will disable the form login

        http.httpBasic(withDefaults());
        //http.httpBasic(hbc -> hbc.disable()); //this will disable the http basic authentication
        return http.build();
    }

    /*@Profile("database")
    @Bean
    public UserDetailsService getDbUserDetailsService(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }*/


    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        String idForEncode = "bcrypt";
        Map<String,PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("our", new OurPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode,encoders);
    }

}
