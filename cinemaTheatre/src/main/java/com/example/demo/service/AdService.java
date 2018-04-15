package com.example.demo.service;

import com.example.demo.dto.AdDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Ad;
import com.example.demo.model.AdBidStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdService {

    public List<Ad> getAll();

    public Ad getById(Long id);

    public List<Ad> getAllUserAds(Long userId);

    public List<NotificationDTO> create(AdDTO adDTO);

    public String update(Long adId, AdDTO adDTO);

    public String delete(Long idAd);

    public NotificationDTO updateAdStatus(Long id, AdBidStatus status);
}
