package com.example.demo.dto;

import com.example.demo.model.Notification;

public class NotificationDTO {

    private Long receiverId;

    private String topic;

    private String message;

    public NotificationDTO() {

    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification createNotification() {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTopic(topic);
        return notification;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "receiverId=" + receiverId +
                ", topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public NotificationDTO(Long receiverId, String topic, String message) {
        this.receiverId = receiverId;
        this.topic = topic;
        this.message = message;
    }
}
