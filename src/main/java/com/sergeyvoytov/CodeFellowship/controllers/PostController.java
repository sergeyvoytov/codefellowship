package com.sergeyvoytov.CodeFellowship.controllers;

import com.sergeyvoytov.CodeFellowship.models.ApplicationUser;
import com.sergeyvoytov.CodeFellowship.models.ApplicationUserRepository;
import com.sergeyvoytov.CodeFellowship.models.Post;
import com.sergeyvoytov.CodeFellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;


    @PostMapping("/postDetails")
    public RedirectView makeAPost(String body, String timestamp, Principal p) {
        ApplicationUser whoPosted = applicationUserRepository.findByUsername(p.getName());
        // save a post
        long id =whoPosted.id;
        Post posted = new Post(whoPosted, body);
        postRepository.save(posted);
        return new RedirectView("/users/" + id);
    }

}

