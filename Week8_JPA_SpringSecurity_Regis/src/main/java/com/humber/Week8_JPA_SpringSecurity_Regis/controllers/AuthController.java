package com.humber.Week8_JPA_SpringSecurity_Regis.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Value("${restaurant.name}")
    private String name;
//  passing Name
// custom error, handling error view, if non admin user tries to do admin functions
    @GetMapping("/error")
    public String handleError() {
        return "auth/error"; // default looks for templates folder but its in subfolder
    }

    //custom login form
    @GetMapping("/login")
    public String login(Model model, @RequestParam (required = false) String message) {
        // message to say your logged out
        model.addAttribute("message", message);
        model.addAttribute("restaurantName", name);
        return "auth/login";
    }
    //custom logout
    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication){ // inside para req, res, authication
        // logout logic
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        //return "redirect:/restaurant/home?message=You have been Logged Out Successfully!";
        return "redirect:/login?message=You have been Logged Out Successfully!";
    }
}
