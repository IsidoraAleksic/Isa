package com.example.demo.service;

import com.example.demo.dto.BidDTO;
import com.example.demo.model.Bid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    public List<Bid> getAll();

    public Bid getById(Long id_bid);

    public List<Bid> getByIdAd(Long id_ad);

    public List<Bid> getByGuest(Long id_guestBid);

    public String create(BidDTO bidDTO);

    public String update(Long bidId, BidDTO bidDTO);

    public String delete(Long idBid);
}
