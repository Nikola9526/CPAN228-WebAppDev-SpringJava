package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.controllers;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.myUser;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AuthController  implements org.springframework.boot.web.servlet.error.ErrorController {
    // constructor injection
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }


   // passing store name
    @Value("${store.name}")
    private String name;

    // custom error
    @GetMapping("/error")
    public String handleError(){
        return "auth/error";
    }

    // custom login page
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message ){
        model.addAttribute("message", message);
        model.addAttribute("name", name);
        return "auth/login";
    }

    // custom logout
    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication){
        // logout logic
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login?message=You have been Logged Out Successfully!";
    }
    //custom register form // adding endpoint to register // shows form
    @GetMapping("/registering")
    public String register(Model model,
                           @RequestParam(required = false) String message){
        // send empty object // to add user info
        model.addAttribute("user", new myUser());
        model.addAttribute("message", message);
        model.addAttribute("name", name);

        return "auth/register"; //the view
    }
    @PostMapping("/registering")
    public String registerPost (@ModelAttribute myUser user) {
        // saving enter user info into DB
        int saveUserCode = userService.saveUser(user);

        // 0 =exsits , 1= new
        if (saveUserCode ==0) {
            return "redirect:/register?message=Username Already Exists!";
        } //else code=1
        return "redirect:/login?message= Registered Successfully!";
    }

}
