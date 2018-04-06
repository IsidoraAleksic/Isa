package com.example.demo.service;

import com.example.demo.dto.AdDTO;
import com.example.demo.model.Ad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdService {

    public List<Ad> getAll();

    public Ad getById(Long id);

    public List<Ad> getAllUserAds(Long userId);

    public String create(AdDTO adDTO);

    public String update(Long adId, AdDTO adDTO);

    public String delete(Long idAd);
}
