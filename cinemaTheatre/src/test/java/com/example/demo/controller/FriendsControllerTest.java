package com.example.demo.controller;

import com.example.demo.TestUtil;
import com.example.demo.constants.UserConstants;
import com.example.demo.model.Friends;
import com.example.demo.model.User;
import com.example.demo.model.UserTier;
import com.example.demo.model.UserType;
import com.example.demo.repository.FriendsRepository;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.List;

import static com.example.demo.constants.UserConstants.*;
import static com.example.demo.constants.UserConstants.DB_EMAIL;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final String BASE_URL = "/friends";
    private static final String LISTALL = "/allName";
    private static final String ADDFRIEND = "/addFriend";
    private static final String LOGOUT = "/logout";

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void listFriends() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get(BASE_URL + "/allFriends")).andExpect(status().isOk());
    }

    @Test
    public void isFriendsNotLoggedIn() throws Exception {
        User friend = userRepository.findByEmail("admin@gmail.com");
        mockMvc.perform(get(BASE_URL + "/isFriends/" + friend.getId())).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @Transactional
    @Rollback(true)
    public void addFriend() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get(BASE_URL + "/addFriend/" + 1L))
                .andExpect(status().isOk());

    }

    @Test
    public void getFriendRequests() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get(BASE_URL + "/getFriendRequests"))
                .andExpect(status().isOk());
    }

    @Test
    public void acceptFriendRequestNoRequests() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get(BASE_URL + "/acceptFriendRequest/" + 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void declineRequestNothingToDelete() throws Exception {
        User user = new User();
        user.setEmail("isamejl811@gmail.com");
        user.setPassword("jefimija");
        String json = TestUtil.json(user);
        mockMvc.perform(post("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        user.setRole(UserType.GUEST);
        mockMvc.perform(get(BASE_URL + "/declineRequest/" + 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void inviteFriend() {
    }
}