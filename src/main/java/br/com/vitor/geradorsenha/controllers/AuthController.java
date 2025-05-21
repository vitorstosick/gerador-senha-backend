package br.com.vitor.geradorsenha.controllers;

import br.com.vitor.geradorsenha.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public String handle(Authentication authentication, HttpServletResponse response) {

        return authService.authenticate(authentication);
    }
}
