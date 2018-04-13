package com.example.demo.service;
import com.example.demo.model.Merchandise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchandiseService {
    public List<Merchandise> getAll();

    public Merchandise getById(Long id_tr);
}
