package com.example.demo.service.impl;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    UserRepository userRepository;
    private static String SUCCESS_CREATED_NOTIFICATION = "Successfully created notification";
    private static String ERROR = "Unsuccessfully created notification";

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification getById(Long id) {
        return notificationRepository.getById(id);
    }

    public List<Notification> getByReceiver(Long receiver) {
        User user = userRepository.getById(receiver);
        return user.getNotifications();
    }

    public String create(NotificationDTO notificationDTO) {
        User user = userRepository.getById(notificationDTO.getReceiverId());
        if (user == null) {
            return ERROR;
        }
        Notification notification = notificationDTO.createNotification();
        notification.setReceiver(user);
        notificationRepository.save(notification);
        return SUCCESS_CREATED_NOTIFICATION;
    }
}
