package com.example.demo.controller;

import com.example.demo.dto.BidDTO;
import com.example.demo.dto.MerchandiseDTO;
import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.MerchandiseService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    MerchandiseService merchandiseService;
    //uvek se autowire-uje interfejs.SOLID princip neki
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    StorageService storageService;

    @RequestMapping("/allMerchandise")
    public List<Merchandise> getAll(){
        return merchandiseService.getAll();
    }

    @RequestMapping("{id}")
    public Merchandise getById(@PathVariable("id") Long id_tr){
        return merchandiseService.getById(id_tr);
    }

    @PreAuthorize("hasAuthority('ADMINFZ')")
    @PostMapping
    public Long createMerchandise(@RequestBody MerchandiseDTO merchandiseDTO) {
        merchandiseDTO.setUserId(authenticationService.getLoggedInUser().getId());
        Merchandise result = merchandiseService.create(merchandiseDTO);
        return result.getId();
    }
    //@PostMapping(value = "/addImage")
    /*public void createMerchandiseImage(@RequestParam("file") InputStream file){
        System.out.println("Works!");
        *//*if (file.isEmpty()) {
            try {

                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(this.getClass().getResource("/static/images").getFile() + file.getOriginalFilename());
                Files.write(path, bytes);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }*//*
    }*///SMILJANINO

    @PostMapping("{id}/image/")
    public ResponseEntity uploadFile(@PathVariable("id") long id,@RequestBody MerchandiseDTO merchandiseDTO, @RequestParam("file") MultipartFile file){
        try{
            String imageUrl = storageService.store(file);
            merchandiseDTO.setImageMerchandise(imageUrl);
            String result = merchandiseService.update(id, merchandiseDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ADMINFZ')")
    @PostMapping("{id}")
    public ResponseEntity updateMerchandise(@PathVariable("id") Long id_merchandise, @RequestBody MerchandiseDTO merchandiseDTO) {
        String result = merchandiseService.update(id_merchandise,merchandiseDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMINFZ')")
    @DeleteMapping("{id}")
    public ResponseEntity deleteMerchandise(@PathVariable("id") Long id_merchandise) {
        String result = merchandiseService.delete(id_merchandise);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}