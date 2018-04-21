package com.example.demo.controller;

import com.example.demo.TestUtil;
import com.example.demo.constants.UserConstants;
import com.example.demo.model.User;
import com.example.demo.model.UserTier;
import com.example.demo.model.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demo.constants.UserConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;



@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class AuthenticationControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    private static final String BASE_URL = "/authenticate";
    private static final String REGISTER = "/register";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";



    @Test
    public void authenticateUserLoginValid() throws Exception {
        User user = new User(DB_ID, DB_FIRST_NAME,DB_LAST_NAME,10,DB_PASSWORD,DB_EMAIL,"Novi Sad","021",true, UserType.GUEST, UserTier.BRONZE);

        String json = TestUtil.json(user);
        mockMvc.perform(post(BASE_URL + LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticateUserLoginInValid() throws Exception {
        User user = new User();
        user.setEmail(DB_EMAIL_INVALID);
        user.setPassword(DB_PASSWORD);
        String json = TestUtil.json(user);
        mockMvc.perform(post(BASE_URL + LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }


    @Test
    public void logoutInvalidOk() throws Exception {
        mockMvc.perform(get(BASE_URL + LOGOUT))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser
    public void logoutOk() throws Exception {
        mockMvc.perform(get(BASE_URL + LOGOUT))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUserOk() throws Exception {
        User user = new User(5L, DB_FIRST_NAME,DB_LAST_NAME,10,DB_PASSWORD,DB_EMAIL,"Novi Sad","021",true, UserType.GUEST, UserTier.BRONZE);
        user.setConfirmationToken("1111");
        user.setEnabled(true);

        String json = TestUtil.json(user);
        mockMvc.perform(post(BASE_URL + REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUserMissingEmail() throws Exception {
        User user = new User(5L, DB_FIRST_NAME,DB_LAST_NAME,10,DB_PASSWORD,"Novi Sad","021",true, UserType.GUEST, UserTier.BRONZE);
        user.setConfirmationToken("1111");
        user.setEnabled(true);

        String json = TestUtil.json(user);
        mockMvc.perform(post(BASE_URL + REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerUserOkNotConfirmed() throws Exception {
        User user = new User(5L, DB_FIRST_NAME,DB_LAST_NAME,10,DB_PASSWORD,DB_EMAIL,"Novi Sad","021",true, UserType.GUEST, UserTier.BRONZE);


        String json = TestUtil.json(user);
        mockMvc.perform(post(BASE_URL + REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }




}