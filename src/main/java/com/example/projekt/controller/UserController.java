package com.example.projekt.controller;

import com.example.projekt.model.User;
import com.example.projekt.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginPage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/dashboard";  // Redirect to dashboard if user is already logged in
        }
        return "login";  // Show the login page if not logged in
    }

    @GetMapping("/register")
    public String registerPage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/dashboard";  // Redirect to dashboard if user is already logged in
        }
        return "register";  // Show the register page if not logged in
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";  // The main page after login
    }
}

