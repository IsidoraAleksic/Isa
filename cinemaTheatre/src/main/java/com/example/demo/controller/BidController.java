package com.example.demo.controller;

import com.example.demo.dto.BidDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Bid;
import com.example.demo.model.AdBidStatus;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.BidService;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {
    //uvek se autowire-uje interfejs.SOLID princip neki
    @Autowired
    BidService bidService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    AuthenticationService authenticationService;

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping("/allBids")
    public List<Bid> getAll() {
        return bidService.getAll();
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping("/allBidsForUsersAds")
    public List<Bid> getAllBidsForUsersAds() {
        return bidService.getAllBidsForUsersAds(authenticationService.getLoggedInUser().getId());
    }

    @RequestMapping("{id}")
    public Bid getById(@PathVariable("id") Long id_tr) {
        return bidService.getById(id_tr);
    }

    @RequestMapping("/adId/{idAd}")
    public List<Bid> getByAd(@PathVariable("idAd") Long id_ad) {
        return bidService.getByIdAd(id_ad);
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping("/guestId")
    public List<Bid> getByGuest() {
        return bidService.getByGuest(authenticationService.getLoggedInUser().getId());
    }

    @Transactional
    @PreAuthorize("hasAuthority('GUEST')")
    @PostMapping
    public ResponseEntity createBid(@RequestBody BidDTO bidDTO) {
        bidDTO.setIdGuestBid(authenticationService.getLoggedInUser().getId());
        String result = bidService.create(bidDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @PostMapping("{id}")
    public ResponseEntity updateBid(@PathVariable("id") Long id_bid, @RequestBody BidDTO bidDTO) {
        String result = bidService.update(id_bid, bidDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBid(@PathVariable("id") Long id_bid) {
        String result = bidService.delete(id_bid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{id}/status/{status}")
    public ResponseEntity updateBidStatus(@PathVariable("id") Long id, @PathVariable("status") AdBidStatus status) {
        List<NotificationDTO> notifications = bidService.updateBidStatus(id, status);
        if (notifications == null) {
            return new ResponseEntity<>("Unsuccessfully updated bid's status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (NotificationDTO notificationDTO : notifications) {
            notificationService.create(notificationDTO);
        }

        return new ResponseEntity<>("Successfully updated bid's status", HttpStatus.OK);
    }


}
