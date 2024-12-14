package it.cascella.security.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("Utente", "Bannato");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("{");
        writer.println("  \"timestamp\": \"" + java.time.LocalDateTime.now() + "\",");
        writer.println("  \"status\": " + HttpStatus.FORBIDDEN.value() + ",");
        writer.println("  \"error\": \"" + HttpStatus.FORBIDDEN.getReasonPhrase() + "\",");
        writer.println("  \"message\": \"Non autorizzato\",");
        writer.println("  \"internal-code\": \""+accessDeniedException.getMessage()+"\",");
        writer.println("  \"path\": \"" + request.getRequestURI() + "\"");
        writer.println("}");
    }
}
