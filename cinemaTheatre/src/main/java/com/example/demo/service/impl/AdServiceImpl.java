package com.example.demo.service.impl;

import com.example.demo.model.Ad;
import com.example.demo.repository.AdRepository;
import com.example.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class AdServiceImpl implements AdService{

    AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository){
        this.adRepository = adRepository;
    }

    public List<Ad> getAll(){
        return adRepository.findAll();
    }

    public Ad getById(Long id_ad){
        return adRepository.getById(id_ad);
    }

    public List<Ad> getAllUserAds(Long userId){
        return adRepository.getByUserId(userId);
    }
}
