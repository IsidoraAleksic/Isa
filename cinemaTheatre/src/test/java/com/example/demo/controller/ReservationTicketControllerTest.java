package com.example.demo.controller;

import com.example.demo.TestUtil;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTicketControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCinemaTheaterOk() throws Exception {
        mockMvc.perform(get("/reserve/getCinemaTheater/"+ 1L)).andExpect(status().isOk());
    }




    @Test
    public void acceptInvitationNotFound() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get("reserve/acceptInvitation")).andExpect(status().isNotFound());
    }

    @Test
    public void declineInvitationNotFound() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get("reserve/declineInvitation")).andExpect(status().isNotFound());
    }

    @Test
    public void getTicketsNotFound() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get("reserve/tickets")).andExpect(status().isNotFound());
    }
}