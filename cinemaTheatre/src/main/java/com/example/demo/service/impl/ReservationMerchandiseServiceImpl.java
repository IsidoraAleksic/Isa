package com.example.demo.service.impl;

import com.example.demo.dto.ReservationMerchandiseDTO;
import com.example.demo.model.ReservationMerchandise;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.ReservationMerchandiseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationMerchandiseServiceImpl implements ReservationMerchandiseService {

    private ReservationMerchandiseRepository reservationMerchandiseRepository;
    private UserRepository userRepository;
    private MerchandiseRepository merchandiseRepository;

    private static final String SUCCESSFULLY_CREATED ="Successfully created merch reservation.";

    private static final String UNSUCCESSFULLY_CREATED = "Unsuccessfully created merch reservation.";

    @Autowired
    ReservationMerchandiseServiceImpl(ReservationMerchandiseRepository reservationMerchandiseRepository, UserRepository userRepository, MerchandiseRepository merchandiseRepository) {
        this.reservationMerchandiseRepository = reservationMerchandiseRepository;
        this.userRepository = userRepository;
        this.merchandiseRepository = merchandiseRepository;
    }

    public ReservationMerchandise getById(Long id_reservationMerch) {
        return reservationMerchandiseRepository.getById(id_reservationMerch);
    }

    public List<ReservationMerchandise> findUserReservations(Long user_id) {
        return reservationMerchandiseRepository.findByUserId(user_id);
    }

    public String createReservationMerchandise(ReservationMerchandiseDTO reservationMerchandiseDTO) {
        ReservationMerchandise reservationMerchandise = new ReservationMerchandise();
        reservationMerchandise.setUser(userRepository.getById(reservationMerchandiseDTO.getUserId()));
        reservationMerchandise.setMerch(merchandiseRepository.getById(reservationMerchandiseDTO.getMerchId()));
        Long mercId = reservationMerchandiseDTO.getMerchId();
            if(reservationMerchandiseRepository.findByMerchId(mercId)!=null){
                return UNSUCCESSFULLY_CREATED;
            }else{
                reservationMerchandiseRepository.save(reservationMerchandise);
                return SUCCESSFULLY_CREATED;
            }
    }

}


