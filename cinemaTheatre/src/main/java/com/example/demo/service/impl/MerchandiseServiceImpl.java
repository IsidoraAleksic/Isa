package com.example.demo.service.impl;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    MerchandiseRepository merchandiseRepository;

    @Autowired
    public MerchandiseServiceImpl(MerchandiseRepository merchandiseRepository){
        this.merchandiseRepository = merchandiseRepository;
    }
    public List<Merchandise> getAll(){return merchandiseRepository.findAll();}

    public Merchandise getById(Long id_tr){return merchandiseRepository.getById(id_tr);}
}
