package com.sergeyvoytov.CodeFellowship.controllers;

import com.sergeyvoytov.CodeFellowship.models.ApplicationUser;
import com.sergeyvoytov.CodeFellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String dateOfBirth, String bio, String firstName, String lastName) {
        System.out.println("You are adding a user");
        // make the user AND salt and hash the password
        // this does the salting and hashing for you


        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), dateOfBirth, bio, firstName, lastName);


        // save the user to db
        applicationUserRepository.save(newUser);

        // send them back home
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @GetMapping("/profile")
    public String getHome(Principal p, Model m) {

        if (p != null) {
            m.addAttribute("username", p.getName());
        }

        return "profile";
    }


}