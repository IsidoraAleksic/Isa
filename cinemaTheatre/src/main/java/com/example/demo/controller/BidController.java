package com.example.demo.controller;

import com.example.demo.dto.BidDTO;
import com.example.demo.model.Bid;
import com.example.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return bidService.getByIdAd(id_ad);
    }

    @RequestMapping("/guestId/{idGuest}")
    public List<Bid> getByGuest(@PathVariable("idGuest") Long id_guestBid){
        return bidService.getByGuest(id_guestBid);
    }

    @PostMapping
    public ResponseEntity createBid(@RequestBody BidDTO bidDTO) {
        String result = bidService.create(bidDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity updateBid(@PathVariable("id") Long id_bid, @RequestBody BidDTO bidDTO) {
        String result = bidService.update(id_bid, bidDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteAd(@PathVariable("id") Long id_bid) {
        String result = bidService.delete(id_bid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
