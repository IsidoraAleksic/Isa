package com.example.demo.service;

import com.example.demo.dto.BidDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Bid;
import com.example.demo.model.AdBidStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    List<Bid> getAll();

    Bid getById(Long id_bid);

    List<Bid> getByIdAd(Long id_ad);

    List<Bid> getByGuest(Long id_guestBid);

    String create(BidDTO bidDTO);

    String update(Long bidId, BidDTO bidDTO);

    String delete(Long idBid);

    List<NotificationDTO> updateBidStatus(Long id, AdBidStatus status);

    List<Bid> getAllBidsForUsersAds(Long userId);
}
