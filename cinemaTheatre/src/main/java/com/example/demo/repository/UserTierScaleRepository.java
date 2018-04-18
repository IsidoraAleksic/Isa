package com.example.demo.repository;

import com.example.demo.model.Ad;
import com.example.demo.model.UserTier;
import com.example.demo.model.UserTierScale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTierScaleRepository extends JpaRepository<UserTierScale,Long> {

    UserTierScale getById(Long id);
    UserTierScale getByUserTier(UserTier userTier);
}
