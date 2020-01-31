package com.sergeyvoytov.CodeFellowship.controllers;

import com.sergeyvoytov.CodeFellowship.models.ApplicationUser;
import com.sergeyvoytov.CodeFellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String dateOfBirth, String bio, String firstName, String lastName, Model m) {
        System.out.println("You are adding a user");
        boolean displayUsernameTaken = false;

        // look for username by username
        // check DB for username
        ApplicationUser uniqIfNull = this.applicationUserRepository.findByUsername(username);

        // if null crete new record

        if (uniqIfNull == null) {

            ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), dateOfBirth, bio, firstName, lastName);


            applicationUserRepository.save(newUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            displayUsernameTaken = true;
            m.addAttribute("displayUsernameTaken", displayUsernameTaken);
            return new RedirectView("/signup");

        }


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

            ApplicationUser applicationUser = this.applicationUserRepository.findByUsername(p.getName());

            m.addAttribute("username", p.getName());

            m.addAttribute("applicationUser", applicationUser);


            Set<ApplicationUser> loggedUsersFollower = applicationUser.peopleIfollow;
            m.addAttribute("loggedUsersFollower", loggedUsersFollower);
        }

        return "profile";
    }

    //Tuesday
    @GetMapping("/users/{id}")

    public String ShowUserDetails(@PathVariable long id, Principal p, Model m) {
        ApplicationUser theUser = applicationUserRepository.findById(id).get();
        // set the attribute on Model
//        m.addAttribute("usernameWeAreVisiting", theUser.getUsername());
//        m.addAttribute("userIdWeAreVisiting", theUser.id);
        m.addAttribute("userWeAreVisiting", theUser);
        m.addAttribute("principalTheAndroid", p.getName());
        return "userDetails";
    }


    @GetMapping("/users/all")
    public String showAllUsers(Principal p, Model m) {

        List<ApplicationUser> allUsers = applicationUserRepository.findAll();

        m.addAttribute("principalTheAndroid", p.getName());
        m.addAttribute("allUsers", allUsers);

        return "allusers";
    }


    @PostMapping("/follow")
    public RedirectView followUser(Principal p, Model m, String username) {

        ApplicationUser loggedInUser = this.applicationUserRepository.findByUsername(p.getName());
        ApplicationUser followingUser = this.applicationUserRepository.findByUsername(username);

        loggedInUser.startFollowing(followingUser);
        applicationUserRepository.save(loggedInUser);


        return new RedirectView("/profile");
    }


}