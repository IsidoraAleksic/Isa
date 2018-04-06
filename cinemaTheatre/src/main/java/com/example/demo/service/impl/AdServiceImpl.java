package com.example.demo.service.impl;

import com.example.demo.dto.AdDTO;
import com.example.demo.model.Ad;
import com.example.demo.model.Bid;
import com.example.demo.repository.AdRepository;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    AdRepository adRepository;
    UserRepository userRepository;
    MerchandiseRepository merchandiseRepository;
    BidRepository bidRepository;

    private static String SUCCESS_CREATED_AD = "Successfully created ad";
    private static String AD_ALREADY_EXISTS = "An Ad already exists for this merchandise";
    private static String ERRORR_CREATE_AD = "Unsuccessful create ad";

    private static String SUCCESS_DELETED_AD = "Successfully deleted ads with bids";
    private static String AD_DOESNT_EXISTS = "Ad can't be deleted because it doesn't exist.";

    private static String SUCCESS_UPDATED_AD = "Successfully updated ad";
    private static String ERRORR_UPDATE_AD = "Unsuccessful update ad";


    @Autowired
    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, MerchandiseRepository merchandiseRepository, BidRepository bidRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.merchandiseRepository = merchandiseRepository;
        this.bidRepository = bidRepository;
    }

    public List<Ad> getAll() {
        return adRepository.findAll();
    }

    public Ad getById(Long id_ad) {
        return adRepository.getById(id_ad);
    }

    public List<Ad> getAllUserAds(Long userId) {
        return adRepository.getByUserId(userId);
    }

    public String create(AdDTO adDTO) {
        if (adDTO == null) {
            return ERRORR_CREATE_AD;
        }
        if (adRepository.getByMerchandiseId(adDTO.getMerchandiseId()) != null) {
            return AD_ALREADY_EXISTS;
        }

        Ad ad = adDTO.createAd();
        ad.setUser(userRepository.getById(adDTO.getUserId()));//ovo radim jer necu da mi se u JSon objekat ispise ceo merchandise i ceo user. adDTO ce imati one stvari koje cu ispisati,
        ad.setMerchandise(merchandiseRepository.getById(adDTO.getMerchandiseId()));
        adRepository.save(ad);
        return SUCCESS_CREATED_AD;
    }

    public String update(Long adId, AdDTO adDTO) {
        Ad ad = adRepository.getById(adId);
        if (!ad.getUser().getId().equals(adDTO.getUserId())) {
            return ERRORR_UPDATE_AD;
        } else if (!ad.getMerchandise().getId().equals(adDTO.getMerchandiseId())) {
            return ERRORR_UPDATE_AD;
        }
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
}
