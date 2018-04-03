package com.example.demo.controller;

import com.example.demo.model.Ad;
import com.example.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}