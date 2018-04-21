package com.example.demo.controller;

import com.example.demo.model.Ad;
import com.example.demo.service.AdService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.NotificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/advert";

    private static final long EXISTING_AD_ID = 1;

    @Autowired
    private AdService adService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    @WithMockUser
    public void getAllAdsShouldReturnOk() throws  Exception{
        //LoginUtil.login(mockMvc, Role.MANAGER);

        mockMvc.perform(get(BASE_URL + "/restaurant/" + EXISTING_AD_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }
}
