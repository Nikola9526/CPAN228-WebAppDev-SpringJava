package com.humber.Week8_JPA_SpringSecurity_Regis.controllers;

import com.humber.Week8_JPA_SpringSecurity_Regis.models.myUser;
import com.humber.Week8_JPA_SpringSecurity_Regis.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController implements org.springframework.boot.web.servlet.error.ErrorController {

    //injecting User Service in to here //constructor injection
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

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

    //adding registration endpoint //the form view page
    @GetMapping("/registers")
    public String register(Model model, @RequestParam (required = false) String message) {
        //send empty object
        model.addAttribute("user", new myUser());
        model.addAttribute("message", message); //shows success or error message
        model.addAttribute("restaurantName", name); //shows name of site
        return "auth/register"; //the view (html)
    }

    // post endpoint to action of clicking register
    @PostMapping("/registers")
    public String registerPost(@ModelAttribute myUser user) {
        // saving in db // save the user
        int saveUserCode = userService.saveUser(user);
        // 0 for already exsits , 1 for new user stored
        if (saveUserCode == 0) {
            return  "redirect:/registers?message=Username Already Exists!";
        } else /* code == 1*/ {
            return "redirect:/login?message=Registration Done Successfully!";
        }
    }
}
