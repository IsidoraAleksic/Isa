package com.example.demo.controller;

import com.example.demo.dto.AdDTO;
import com.example.demo.model.Ad;
import com.example.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdController {

    AdService adService;

    @Autowired //uvek se autowire-uje interfejs.SOLID princip neki
    public AdController(AdService adService){
        this.adService = adService;
    }

    @RequestMapping("/allAds")
    public List<Ad> getAll(){
        return adService.getAll();
    }

    @RequestMapping("{id}")
    public Ad getById(@PathVariable("id") Long id_ad){
        return adService.getById(id_ad);
    }

    @RequestMapping("/userId/{id}")
    public List<Ad> getAllUserAds(@PathVariable("id") Long id_user) {
        return adService.getAllUserAds(id_user);
    }

    @PostMapping
    public ResponseEntity createAd(@RequestBody AdDTO adDTO) {
         String result = adService.create(adDTO);
         return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity updateAd(@PathVariable("id") Long id_ad, @RequestBody AdDTO adDTO) {
        String result = adService.update(id_ad, adDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAd(@PathVariable("id") Long id_ad) {
        String result = adService.delete(id_ad);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}