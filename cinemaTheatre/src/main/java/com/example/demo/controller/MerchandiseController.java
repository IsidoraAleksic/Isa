package com.example.demo.controller;

import com.example.demo.model.Merchandise;
import com.example.demo.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    MerchandiseService merchandiseService;
    @Autowired //uvek se autowire-uje interfejs.SOLID princip neki
    public MerchandiseController(MerchandiseService merchandiseService){
        this.merchandiseService = merchandiseService;
    }

    @RequestMapping("/allMerchandise")
    public List<Merchandise> getAll(){
        return merchandiseService.getAll();
    }

    @RequestMapping("{id}")
    public Merchandise getById(@PathVariable("id") Long id_tr){
        return merchandiseService.getById(id_tr);
    }

}
