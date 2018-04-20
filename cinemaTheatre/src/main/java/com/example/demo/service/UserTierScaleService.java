package com.example.demo.service;

import com.example.demo.dto.UserTierScaleDTO;
import com.example.demo.model.UserTier;
import org.springframework.stereotype.Service;

@Service
public interface UserTierScaleService {
    String updateUserTierScale(UserTierScaleDTO userTierScaleDTO);
    UserTier getUserTierByPoints(int points);
    void updateUsers();
}
