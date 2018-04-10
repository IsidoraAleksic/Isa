package com.example.demo.service.impl;
import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.model.Ad;
import com.example.demo.model.Bid;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.AdRepository;
import com.example.demo.repository.BidRepository;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    MerchandiseRepository merchandiseRepository;
    AdRepository adRepository;
    BidRepository bidRepository;
    UserRepository userRepository;

    private static String SUCCESS_CREATED_MERCH = "Successfully created merch";
    private static String ERRORR_CREATE_MERCH = "Unsuccessful create merch";

    private static String SUCCESS_DELETED_MERCH = "Successfully deleted merches with ads/bids";
    private static String MERCH_DOESNT_EXISTS = "Merch can't be deleted because it doesn't exist.";

    private static String SUCCESS_UPDATED_MERCH = "Successfully updated merch";
    private static String ERRORR_UPDATE_MERCH = "Unsuccessful update merch";

    @Autowired
    public MerchandiseServiceImpl(MerchandiseRepository merchandiseRepository,AdRepository adRepository,BidRepository bidRepository, UserRepository userRepository){
        this.merchandiseRepository = merchandiseRepository;
        this.adRepository = adRepository;
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
    }
    public List<Merchandise> getAll(){return merchandiseRepository.findAll();}

    public Merchandise getById(Long id_tr){return merchandiseRepository.getById(id_tr);}

    public String create(MerchandiseDTO merchandiseDTO){
        if (merchandiseDTO == null) {
            return ERRORR_CREATE_MERCH;
        }

        Merchandise merchandise = merchandiseDTO.createMerchandise();
        merchandise.setUser(userRepository.getById(merchandiseDTO.getUserId()));
        merchandiseRepository.save(merchandise);
        return SUCCESS_CREATED_MERCH;

    }

    public String update(Long merchandiseId, MerchandiseDTO merchandiseDTO) {
        Merchandise merchandise = merchandiseRepository.getById(merchandiseId);

        if (!merchandise.getUser().getId().equals(merchandiseDTO.getUserId())) {
            return ERRORR_UPDATE_MERCH;
        }
        merchandise.setPriceMerchandise(merchandiseDTO.getPriceMerchandise());
        merchandise.setImageMerchandise(merchandiseDTO.getImageMerchandise());
        merchandise.setNameMerchandise(merchandiseDTO.getNameMerchandise());
        merchandise.setDescription(merchandiseDTO.getDescription());

        merchandiseRepository.save(merchandise);
        return SUCCESS_UPDATED_MERCH;
    }

    public String delete(Long merchandiseId){
        if (merchandiseRepository.getById(merchandiseId) == null) {
            return MERCH_DOESNT_EXISTS;
        }

        List<Ad> ads = adRepository.getByMerchandiseId(merchandiseId);
        for (Ad ad : ads) {
            List<Bid> bids = adRepository.getById(ad.getId()).getBid();
            for (Bid bid : bids) {
                bidRepository.deleteById(bid.getId());
            }
            adRepository.deleteById(ad.getId());
        }

        merchandiseRepository.deleteById(merchandiseId);
        return SUCCESS_DELETED_MERCH;

    }
}
