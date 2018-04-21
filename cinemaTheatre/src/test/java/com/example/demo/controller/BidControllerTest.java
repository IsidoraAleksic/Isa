package com.example.demo.controller;

import com.example.demo.TestUtil;
import com.example.demo.dto.AdDTO;
import com.example.demo.model.AdBidStatus;
import com.example.demo.model.Bid;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.AdService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class BidControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/bid";

    private static final long EXISTING_BID_ID = 1;

    @Autowired
    private AdService adService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    @WithMockUser
    public void getAllAdsShouldReturnOk() throws  Exception{

        mockMvc.perform(get(BASE_URL + "/allBids/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    @WithMockUser
    public void createWithValidDataShouldReturnError() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.ADMIN);
        mockMvc.perform(get("bid/")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void updateWithValidDataShouldReturnError() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.ADMIN);
        mockMvc.perform(get("bid/")).andExpect(status().isNotFound());
    }
}
