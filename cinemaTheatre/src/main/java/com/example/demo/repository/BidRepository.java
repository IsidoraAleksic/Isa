package com.example.demo.repository;

import com.example.demo.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Long> {
    public Bid getById(Long id_bid);
    public List<Bid> getByIdGuestBid(Long id_guestBid);
}
