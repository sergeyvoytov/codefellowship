package com.sergeyvoytov.CodeFellowship;

import com.sergeyvoytov.CodeFellowship.controllers.ApplicationUserController;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CodeFellowshipApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationUserController applicationUserController;

    @Test
    void contextLoads() {
    }

    @Test
    public void shouldHaveAGoodHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>CodeaFellowship</h1>")))
                .andExpect(content().string(containsString("<p>Hi that is the web application with log in and storing capacities to sign up and to do nothing else</p>")));
    }


    @Test
    public void shouldHaveAGoodProfileForm() throws Exception {
        this.mockMvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().string(containsString("<form action=\"/postDetails\" method=\"POST\">")));
    }

    @Test
    public void shouldHaveAGoodErrorpage() throws Exception {
        this.mockMvc.perform(get("/error"))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(content().string(containsString("You have reached VA healhcare system, never come back!")));
    }

//    @Test
//    public void shouldHaveAprofile() throws Exception {
//        this.mockMvc.perform(get("/profile"))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }



}


