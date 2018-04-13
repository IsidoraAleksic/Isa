package com.example.demo.service.impl;

import com.example.demo.dto.AdDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.*;
import com.example.demo.repository.AdRepository;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BidRepository bidRepository;

    private static String SUCCESS_CREATED_AD = "Successfully created ad";
    private static String AD_ALREADY_EXISTS = "An Ad already exists for this merchandise";
    private static String ERRORR_CREATE_AD = "Unsuccessful create ad";

    private static String SUCCESS_DELETED_AD = "Successfully deleted ads with bids";
    private static String AD_DOESNT_EXISTS = "Ad can't be deleted because it doesn't exist.";

    private static String SUCCESS_UPDATED_AD = "Successfully updated ad";
    private static String ERRORR_UPDATE_AD = "Unsuccessful update ad";

    public List<Ad> getAll() {
        return adRepository.findAll();
    }

    public Ad getById(Long id_ad) {
        return adRepository.getById(id_ad);
    }

    public List<Ad> getAllUserAds(Long userId) {
        return adRepository.getByUserId(userId);
    }

    public List<NotificationDTO> create(AdDTO adDTO) {
        if (adDTO == null) {
            //return ERRORR_CREATE_AD;
            return null;
        }
//        if (adRepository.getByMerchandiseId(adDTO.getMerchandiseId()) != null) {
//            return null;
//            //return AD_ALREADY_EXISTS;
//        }

        Ad ad = adDTO.createAd();
        ad.setUser(userRepository.getById(adDTO.getUserId()));//ovo radim jer necu da mi se u JSon objekat ispise ceo merchandise i ceo user. adDTO ce imati one stvari koje cu ispisati,
        ad.setAdBidStatus(AdBidStatus.WAITING);
        Ad resultAd = adRepository.save(ad);
        return createNotificationsForAFZ(resultAd.getId());
        //return SUCCESS_CREATED_AD;
    }
    private List<NotificationDTO> createNotificationsForAFZ(Long idAd){
        List<User> users = userRepository.getUserByRole(UserType.ADMINFZ);
        List<NotificationDTO> notifications = new ArrayList<>();
        for(User user: users){
            notifications.add(new NotificationDTO(user.getId(), "Notification for admin fan zone", "Ad with id: " + idAd));
        }
        return notifications;
    }
    public String update(Long adId, AdDTO adDTO) {
        Ad ad = adRepository.getById(adId);
        if (!ad.getUser().getId().equals(adDTO.getUserId())) {
            return ERRORR_UPDATE_AD;
        }/*
        } else if (!ad.getMerchandise().getId().equals(adDTO.getMerchandiseId())) {
            return ERRORR_UPDATE_AD;
        }*/
        ad.setPriceAd(adDTO.getPriceAd());
        ad.setDateEndOfBids(adDTO.getDateEndOfBids());
        adRepository.save(ad);
        return SUCCESS_UPDATED_AD;
    }

    public String delete(Long idAd) {
        if (adRepository.getById(idAd) == null) {
            return AD_DOESNT_EXISTS;
        }

        List<Bid> bids = adRepository.getById(idAd).getBid();
        for (Bid bid : bids) {
            bidRepository.deleteById(bid.getId());
        }
        adRepository.deleteById(idAd);
        return SUCCESS_DELETED_AD;
    }

    public NotificationDTO updateAdStatus(Long id, AdBidStatus status){
        Ad ad = adRepository.getById(id);
        if (ad == null) {
            return null;
        }

        ad.setAdBidStatus(status);
        adRepository.save(ad);
        return new NotificationDTO(ad.getUser().getId(), "Ad Notification", "Ad with id: " + ad.getId()+ " is " + ad.getAdBidStatus().name());
    }


}
