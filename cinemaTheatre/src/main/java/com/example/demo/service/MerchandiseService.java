package com.example.demo.service;
import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.model.Merchandise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchandiseService {
    public List<Merchandise> getAll();

    public Merchandise getById(Long id_tr);

    public Merchandise create(MerchandiseDTO merchandiseDTO);

    public String update(Long merchandiseId, MerchandiseDTO merchandiseDTO);

    public String delete(Long merchandiseId);
}
