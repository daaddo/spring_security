package it.cascella.security.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;


public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Gino", "Ginelli");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("{");
        writer.println("  \"timestamp\": \"" + java.time.LocalDateTime.now() + "\",");
        writer.println("  \"status\": " + HttpStatus.UNAUTHORIZED.value() + ",");
        writer.println("  \"error\": \"" + HttpStatus.UNAUTHORIZED.getReasonPhrase() + "\",");
        writer.println("  \"message\": \"Non autorizzato\",");
        writer.println("  \"internal-code\": \""+authException.getMessage()+"\",");
        writer.println("  \"path\": \"" + request.getRequestURI() + "\"");
        writer.println("}");

    }
}
