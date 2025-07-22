package com.id.dragneel.LatihanCRUD.controller;

import com.id.dragneel.LatihanCRUD.model.Developer;
import com.id.dragneel.LatihanCRUD.repo.DeveloperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeveloperController {

    @Autowired
    private DeveloperRepo developerRepo;

    @GetMapping("/getAllDev")
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        try {
            List<Developer> developerList = developerRepo.findAll();

            if (developerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(developerList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDevById/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id){
        Optional<Developer> developerData = developerRepo.findById(id);

        if (developerData.isPresent()) {
            return new ResponseEntity<>(developerData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addDev")
    public Developer addDeveloper(@RequestBody Developer developer){
        return developerRepo.save(developer);
    }

    @PostMapping("/updateDevById/{id}")
    public ResponseEntity<Developer> updateGDeveloperById(@PathVariable Long id, @RequestBody Developer newDeveloper){
        Optional<Developer> oldDeveloper = developerRepo.findById(id);

        if (oldDeveloper.isPresent()) {
            Developer updateDeveloper = oldDeveloper.get();
            updateDeveloper.setName(newDeveloper.getName());

            Developer result = developerRepo.save(updateDeveloper);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDevById/{id}")
    public ResponseEntity<HttpStatus> deleteDeveloperById(@PathVariable Long id){
        developerRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
