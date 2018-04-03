package com.example.demo.repository;

import com.example.demo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad,Long> {

    public Ad getById(Long id_ad);

    public List<Ad> getByUserId(Long idUser);
}
