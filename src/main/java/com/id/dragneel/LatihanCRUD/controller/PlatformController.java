package com.id.dragneel.LatihanCRUD.controller;

import com.id.dragneel.LatihanCRUD.model.Platform;
import com.id.dragneel.LatihanCRUD.repo.PlatformRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlatformController {
    
    @Autowired
    private PlatformRepo platformRepo;

    @GetMapping("/getAllPlatform")
    public ResponseEntity<List<Platform>> getAllPlatform(){
        try {
            List<Platform> platformList = platformRepo.findAll();

            if (platformList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(platformList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPlatformById/{id}")
    public ResponseEntity<Platform> getPlatformById(@PathVariable Long id){
        Optional<Platform> platformData = platformRepo.findById(id);

        if (platformData.isPresent()) {
            return new ResponseEntity<>(platformData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addPlatform")
    public Platform addPlatform(@RequestBody Platform platform){
        return platformRepo.save(platform);
    }

    @PostMapping("/updatePlatformById/{id}")
    public ResponseEntity<Platform> updateGPlatformById(@PathVariable Long id, @RequestBody Platform newPlatform){
        Optional<Platform> oldPlatform = platformRepo.findById(id);

        if (oldPlatform.isPresent()) {
            Platform updatePlatform = oldPlatform.get();
            updatePlatform.setName(newPlatform.getName());

            Platform result = platformRepo.save(updatePlatform);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePlatformById/{id}")
    public ResponseEntity<HttpStatus> deletePlatformById(@PathVariable Long id){
        platformRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
