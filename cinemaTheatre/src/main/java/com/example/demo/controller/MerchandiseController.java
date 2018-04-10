package com.example.demo.controller;

import com.example.demo.dto.BidDTO;
import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.model.Merchandise;
import com.example.demo.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity createMerchandise(@RequestBody MerchandiseDTO merchandiseDTO) {
        String result = merchandiseService.create(merchandiseDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity updateMerchandise(@PathVariable("id") Long id_merchandise, @RequestBody MerchandiseDTO merchandiseDTO) {
        String result = merchandiseService.update(id_merchandise,merchandiseDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMerchandise(@PathVariable("id") Long id_merchandise) {
        String result = merchandiseService.delete(id_merchandise);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}