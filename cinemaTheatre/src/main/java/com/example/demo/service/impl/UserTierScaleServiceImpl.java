package com.example.demo.service.impl;

import com.example.demo.dto.UserTierScaleDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserTier;
import com.example.demo.model.UserTierScale;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserTierScaleRepository;
import com.example.demo.service.UserTierScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTierScaleServiceImpl implements UserTierScaleService {
    @Autowired
    UserTierScaleRepository userTierScaleRepository;
    @Autowired
    UserRepository userRepository;

    private static String SUCCESSFUL_UPDATE = "Successfully updated user tier scale!";
    private static String UNSUCCESSFUL_UPDATE = "Unsuccessfully updated user tier scale!";

    @Override
    public String updateUserTierScale(UserTierScaleDTO userTierScaleDTO) {

        UserTierScale userTierScaleBronze = userTierScaleRepository.getByUserTier(UserTier.BRONZE);
        userTierScaleBronze.setPoints(userTierScaleDTO.getMinBronze());

        UserTierScale userTierScaleSilver = userTierScaleRepository.getByUserTier(UserTier.SILVER);
        userTierScaleSilver.setPoints(userTierScaleDTO.getMinSilver());

        UserTierScale userTierScaleGolden = userTierScaleRepository.getByUserTier(UserTier.GOLDEN);
        userTierScaleGolden.setPoints(userTierScaleDTO.getMinGolden());

        userTierScaleRepository.save(userTierScaleBronze);
        userTierScaleRepository.save(userTierScaleSilver);
        userTierScaleRepository.save(userTierScaleGolden);
        updateUsers();
        return SUCCESSFUL_UPDATE;
    }

    @Override
    public UserTier getUserTierByPoints(int points) {
        List<UserTierScale> userTierScales = userTierScaleRepository.findAll();
        int minBronze = 0;
        int minSilver = 0;
        int minGold = 0;
        for (UserTierScale userTierScale : userTierScales) {
            if (userTierScale.getUserTier() == UserTier.BRONZE) {
                minBronze = userTierScale.getPoints();
            } else if (userTierScale.getUserTier() == UserTier.SILVER) {
                minSilver = userTierScale.getPoints();
            } else {
                minGold = userTierScale.getPoints();
            }
        }

        return getUserTierByRange(minBronze, minSilver, minGold, points);
    }

    private UserTier getUserTierByRange(int minBronze, int minSilver, int minGold, int points) {
        if (points >= minBronze && points < minSilver) {
            return UserTier.BRONZE;
        } else if (points >= minSilver && points < minGold) {
            return UserTier.SILVER;
        } else if (points >= minGold) {
            return UserTier.GOLDEN;
        } else {
            return UserTier.BRONZE;
        }
    }

    public void updateUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setUserTier(getUserTierByPoints(user.getPoints()));
            userRepository.save(user);
        }

    }
}
