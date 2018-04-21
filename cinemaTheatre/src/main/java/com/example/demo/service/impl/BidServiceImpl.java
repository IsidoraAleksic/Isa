package com.example.demo.service.impl;

import com.example.demo.dto.BidDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.AdRepository;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    private static String SUCCESS_CREATED_BID = "Successfully created bid";
    private static String ERRORR_CREATE_BID = "Unsuccessful create bid";

    private static String SUCCESS_UPDATED_BID = "Successfully updated bid";
    private static String ERRORR_UPDATE_BID = "Unsuccessful update bid";

    private static String SUCCESS_DELETED_BID = "Successfully deleted bid";
    private static String BID_DOESNT_EXISTS = "Bid can't be deleted because it doesn't exist.";

    public List<Bid> getAll() {
        return bidRepository.findAll();
    }

    public Bid getById(Long id_bid) {
        return bidRepository.getById(id_bid);
    }

    public List<Bid> getByIdAd(Long id_ad) {
        Ad result = adRepository.getById(id_ad);
        return result.getBid();
    }

    public List<Bid> getByGuest(Long id_guestBid) {
        return bidRepository.getByIdGuestBid(id_guestBid);
    }

    public String create(BidDTO bidDTO) {

        Ad ad = adRepository.getById(bidDTO.getAdId());
        if(ad.getAdBidStatus() != AdBidStatus.ACCEPTED){
                return ERRORR_CREATE_BID;
        }

        if (bidDTO == null) {
            return ERRORR_CREATE_BID;
            //return null;
        }

        Bid bid = bidDTO.createBid();

        bid.setAd(adRepository.getById(bidDTO.getAdId()));
        bid.setIdGuestBid(bidDTO.getIdGuestBid());

        bidRepository.save(bid);

        return SUCCESS_CREATED_BID;
    }

    @Transactional
    public String update(Long bidId, BidDTO bidDTO) {

        Bid bid = bidRepository.getById(bidId);
        if (!bid.getIdGuestBid().equals(bidDTO.getIdGuestBid())) {
            return ERRORR_UPDATE_BID;
        } else if (!bid.getAd().getId().equals(bidDTO.getAdId())) {
            return ERRORR_UPDATE_BID;
        }
        bid.setPriceBid(bidDTO.getPriceBid());

        Long idAd=bid.getAd().getId();
        Ad ad = adRepository.getById(idAd);
        List<Bid> bids = ad.getBid();//al the bids for the ad
        for(Bid bidd:bids){
            if(bidd.getAdBidStatus() == AdBidStatus.ACCEPTED){
                return ERRORR_UPDATE_BID;
            }
        }
            bidRepository.save(bid);
            return SUCCESS_UPDATED_BID;
        }


    public String delete(Long idBid) {
        if (bidRepository.getById(idBid) == null) {
            return BID_DOESNT_EXISTS;
        }
        bidRepository.deleteById(idBid);
        return SUCCESS_DELETED_BID;
    }

    @Transactional
    public List<NotificationDTO> updateBidStatus(Long id, AdBidStatus status) {
        Bid bid = bidRepository.getById(id);
        if (bid == null) {
            return null;
        }

        if (AdBidStatus.ACCEPTED.equals(status)) {
            bid.setAdBidStatus(status);
            bidRepository.save(bid);
            List<Bid> bids = bid.getAd().getBid();
            for (Bid bidTemp : bids) {
                if (bid.getId().equals(bidTemp.getId()))
                    continue;
                bidTemp.setAdBidStatus(AdBidStatus.REJECTED);
                bidRepository.save(bidTemp);
            }

            return createNotifications(bids);
        }
        return null;

    }

    private List<NotificationDTO> createNotifications(List<Bid> bids) {
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Bid bid : bids) {
            User user = userRepository.getById(bid.getIdGuestBid());

            notificationDTOS.add(new NotificationDTO(user.getId(), "Bid Notification", "Bid with id: " + bid.getId() + " is " + bid.getAdBidStatus().name()));
        }
        return notificationDTOS;
    }

    public List<Bid> getAllBidsForUsersAds(Long userId) {
        List<Ad> ads = adRepository.getByUserId(userId);
        List<Bid> bids = new ArrayList<>();
        for (Ad ad : ads) {
            if (ad.getBid() != null) {
                bids.addAll(ad.getBid());
            }
        }
        return bids;
    }

}
