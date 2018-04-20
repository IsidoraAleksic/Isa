package com.example.demo.controller;

import com.example.demo.dto.AdDTO;
import com.example.demo.dto.NotificationDTO;
import com.example.demo.model.Ad;
import com.example.demo.model.AdBidStatus;
import com.example.demo.service.AdService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping("/allAds")
    public List<Ad> getAll(){
        return adService.getAll();
    }


    @RequestMapping("{id}")
    public Ad getById(@PathVariable("id") Long id_ad){
        return adService.getById(id_ad);
    }

    @RequestMapping("/userId")
    public List<Ad> getAllUserAds() {
        return adService.getAllUserAds(authenticationService.getLoggedInUser().getId());
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @PostMapping
    public Long createAd(@RequestBody AdDTO adDTO) {
        adDTO.setUserId(authenticationService.getLoggedInUser().getId());
        Long adId = adService.create(adDTO);
        List<NotificationDTO> notifications = adService.getNotifications(adId);
        if (notifications == null) {
            return adId;
            //return new ResponseEntity<>("Unsuccessfully created ad", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (NotificationDTO notificationDTO : notifications) {
            notificationService.create(notificationDTO);
        }
        return adId;
        //return new ResponseEntity<>("Successfully created ad", HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('GUEST')")
    @PostMapping("{id}")
    public ResponseEntity updateAd(@PathVariable("id") Long id_ad, @RequestBody AdDTO adDTO) {
        String result = adService.update(id_ad, adDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @DeleteMapping("{id}")
    public ResponseEntity deleteAd(@PathVariable("id") Long id_ad) {
        String result = adService.delete(id_ad);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{id}/status/{status}")
    public ResponseEntity updateBidStatus(@PathVariable("id") Long id, @PathVariable("status") AdBidStatus status) {
        NotificationDTO notification = adService.updateAdStatus(id, status);
        if (notification == null) {
            return new ResponseEntity<>("Unsuccessfully updated ad's status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        notificationService.create(notification);
        return new ResponseEntity<>("Successfully updated ad's status", HttpStatus.OK);
    }

  /*  @PostMapping("Upload/{imageName}")
    public String uploadImage(@PathVariable("imageName") String imageName, InputStream fileInputStream) throws Exception {
        String uploadFileLocation = "\\static\\images\\"+imageName+".png";

        OutputStream out = new FileOutputStream(new File(uploadFileLocation));
        int read = 0;
        byte[] bytes = new byte[1024];

        out = new FileOutputStream(new File(uploadFileLocation));
        while ((read = fileInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();

        return uploadFileLocation;
    }*/
}