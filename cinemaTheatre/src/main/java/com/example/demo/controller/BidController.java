package com.example.demo.controller;

import com.example.demo.model.Bid;
import com.example.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/bid")
public class BidController {
     //uvek se autowire-uje interfejs.SOLID princip neki
    BidService bidService;

    @Autowired
    public BidController(BidService bidService){
        this.bidService = bidService;
    }
    @RequestMapping("/allBids")
    public List<Bid> getAll(){
        return bidService.getAll();
    }

    @RequestMapping("{id}")
    public Bid getById(@PathVariable("id") Long id_tr){
        return bidService.getById(id_tr);
    }

    @RequestMapping("/adId/{idAd}")
    public List<Bid> getByAd(@PathVariable("idAd") Long id_ad){
        return bidService.getByAd(id_ad);
    }

    @RequestMapping("/guestId/{idGuest}")
    public List<Bid> getByGuest(@PathVariable("idGuest") Long id_guestBid){
        return bidService.getByGuest(id_guestBid);
    }

}
