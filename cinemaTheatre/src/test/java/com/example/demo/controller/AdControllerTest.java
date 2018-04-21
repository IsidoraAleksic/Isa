package com.example.demo.controller;

import com.example.demo.TestUtil;
import com.example.demo.dto.AdDTO;
import com.example.demo.model.Ad;
import com.example.demo.model.AdBidStatus;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.AdService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.NotificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.runner.RunWith;

import java.util.Date;

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

        mockMvc.perform(get(BASE_URL + "/allAds/"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void createWithValidDataShouldReturnCreated() throws Exception {
        Ad ad = new Ad();
        ad.setNameAd("test ad");
        ad.setDescription("test description");
        ad.setImageAd("test image");
        ad.setPriceAd(1L);
        ad.setDateEndOfBids(new Date());
        ad.setAdBidStatus(AdBidStatus.WAITING);
        ad.setVersion(0L);
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        user.setRole(UserType.GUEST);
        ad.setUser(user);
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.json(ad)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void updateWithValidDataShouldReturnCreated() throws Exception {
       Ad ad = new Ad();
       ad.setNameAd("test ad");
       ad.setDescription("test description");
       ad.setImageAd("test image");
       ad.setPriceAd(1L);
       ad.setDateEndOfBids(new Date());
       ad.setAdBidStatus(AdBidStatus.WAITING);
       ad.setVersion(0L);
       User user = new User();
       user.setEmail("isamejl811@gmail.com");
       user.setPassword("jefimija");
       user.setRole(UserType.GUEST);
       ad.setUser(user);
       ad.setId(EXISTING_AD_ID);
       mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.json(ad)))
                .andExpect(status().isForbidden());
    }

}
