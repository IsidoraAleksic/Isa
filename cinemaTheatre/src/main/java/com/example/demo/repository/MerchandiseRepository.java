package com.example.demo.repository;

import com.example.demo.model.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {
    public Merchandise getById(Long id_tr);
}
