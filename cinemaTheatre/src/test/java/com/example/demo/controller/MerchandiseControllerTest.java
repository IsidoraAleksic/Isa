package com.example.demo.controller;

import com.example.demo.service.AdService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.MerchandiseService;
import com.example.demo.service.NotificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MerchandiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/merchandise";

    private static final long EXISTING_MERCH_ID = 1;

    @Autowired
    MerchandiseService merchandiseService;
    //uvek se autowire-uje interfejs.SOLID princip neki
    @Autowired
    AuthenticationService authenticationService;

    @Test
    @WithMockUser
    public void getAllMerchsShouldReturnOk() throws  Exception{

        mockMvc.perform(get(BASE_URL + "/allMerchandise/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }
}
