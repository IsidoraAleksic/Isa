package com.example.demo.service;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    public Notification getById(Long id);
    public List<Notification> getByReceiver(Long receiver);//jer sam napisala many to one pa treba lista da se vrati
    public String create(NotificationDTO notificationDTO);
}
