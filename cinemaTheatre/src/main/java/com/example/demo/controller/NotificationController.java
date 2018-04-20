package com.example.demo.controller;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Notification;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping("{id}")
    public Notification getNotificationById(@PathVariable("id") Long id){
        return notificationService.getById(id);
    }

    @RequestMapping("/userId")
    public List<Notification> getNotificationsByRecieverId(){
        return notificationService.getByReceiver(authenticationService.getLoggedInUser().getId());
    }

    @PostMapping
    public ResponseEntity createNotification(@RequestBody NotificationDTO notificationDTO) {
        String result = notificationService.create(notificationDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
