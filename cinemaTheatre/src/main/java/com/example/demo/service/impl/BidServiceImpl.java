package com.example.demo.service.impl;

import com.example.demo.model.Ad;
import com.example.demo.model.Bid;

import java.util.List;

import com.example.demo.repository.AdRepository;
import com.example.demo.repository.BidRepository;
import com.example.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService{

    BidRepository bidRepository;
    AdRepository adRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, AdRepository adRepository){
        this.bidRepository = bidRepository;
        this.adRepository = adRepository;
    }
    public List<Bid> getAll(){return bidRepository.findAll();}

    public Bid getById(Long id_bid){return bidRepository.getById(id_bid);}

    public List<Bid> getByAd(Long id_ad){
        Ad result = adRepository.getById(id_ad);
        return result.getBid();
    }

    public List<Bid> getByGuest(Long id_guestBid) { return bidRepository.getByIdGuestBid(id_guestBid);}
}
