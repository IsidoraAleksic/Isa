package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import sun.security.util.Password;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Please enter your first name")
    private String firstName;

    @NotEmpty(message = "Please enter your last name")
    private String lastName;

    @Column(name="password_user", nullable = false)
    @Length(min=6, message = "Password must have at least 6 characters")
    @NotEmpty(message = "Please enter your password")
    private String password;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @Column(unique = true, nullable = false)
    @Email(message = "Enter email")
    @NotEmpty(message = "Please provide a valid email")
    private String email;

    @NotEmpty(message = "Please enter the city")
    private String city;

    @NotEmpty(message = "Please enter your phone number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType role;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Notification> notifications;

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }
}
