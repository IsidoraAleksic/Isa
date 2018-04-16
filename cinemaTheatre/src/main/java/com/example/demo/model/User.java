package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

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
    @Length(min=3, message = "Password must have at least 3 characters")
    @NotEmpty(message = "Please enter your password")
    private String password;



    @Column(unique = true, nullable = false)
    @Email(message = "Enter email")
    @NotEmpty(message = "Please provide a valid email")
    private String email;

    @NotEmpty(message = "Please enter the city")
    private String city;

    @NotEmpty(message = "Please enter your phone number")
    private String phone;

    @Column(name="enabled")
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserType role;

    @Enumerated(EnumType.STRING)
    private UserTier userTier;

    private String confirmationToken;

//    @OneToMany(mappedBy="user")
//    private List<User> friends;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Notification> notifications;


    @OneToMany
    @JsonIgnore
    private List<Reservation> reservedTickets;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ReservationMerchandise> reservationMerchandise;

    public User(){
        this.enabled = false;
    }

    public List<Reservation> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(List<Reservation> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }

    public List<ReservationMerchandise> getReservationMerchandise() {
        return reservationMerchandise;
    }

    public void setReservationMerchandise(List<ReservationMerchandise> reservationMerchandise) {
        this.reservationMerchandise = reservationMerchandise;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public UserTier getUserTier() {
        return userTier;
    }

    public void setUserTier(UserTier userTier) {
        this.userTier = userTier;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }
}
