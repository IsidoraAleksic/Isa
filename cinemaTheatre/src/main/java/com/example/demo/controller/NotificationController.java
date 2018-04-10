package com.example.demo.controller;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @RequestMapping("{id}")
    public Notification getNotificationById(@PathVariable("id") Long id){
        return notificationService.getById(id);
    }

    @RequestMapping("/userId/{id}")
    public List<Notification> getNotificationsByRecieverId(@PathVariable("id") Long id){
        return notificationService.getByReceiver(id);
    }

    @PostMapping
    public ResponseEntity createNotification(@RequestBody NotificationDTO notificationDTO) {
        String result = notificationService.create(notificationDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
