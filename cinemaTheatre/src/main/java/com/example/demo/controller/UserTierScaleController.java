package com.example.demo.controller;

import com.example.demo.dto.UserTierScaleDTO;
import com.example.demo.model.UserTier;
import com.example.demo.model.UserTierScale;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTierScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value="/userTierScale")
public class UserTierScaleController {

    @Autowired
    UserTierScaleService userTierScaleService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/setScale")
    public ResponseEntity setUserTier(@RequestBody UserTierScaleDTO userTierScaleDTO){
        String result = userTierScaleService.updateUserTierScale(userTierScaleDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/{points}")
    public UserTier getUserTierByPoints(@PathParam("points") int points){
        return userTierScaleService.getUserTierByPoints(points);
    }

    @RequestMapping("/updateTiersOfUsers")
    public ResponseEntity updateTiersOfUsers(){
        userTierScaleService.updateUsers();
        return new ResponseEntity<>("Successfully updated tiers of users!",HttpStatus.OK);
    }
}
