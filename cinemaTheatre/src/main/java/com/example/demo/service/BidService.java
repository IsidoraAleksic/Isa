package com.example.demo.service;

import com.example.demo.model.Bid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    public List<Bid> getAll();

    public Bid getById(Long id_bid);

    public List<Bid> getByAd(Long id_ad);

    public List<Bid> getByGuest(Long id_guestBid);
}
