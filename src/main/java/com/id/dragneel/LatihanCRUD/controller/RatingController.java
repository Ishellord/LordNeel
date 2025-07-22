package com.id.dragneel.LatihanCRUD.controller;

import com.id.dragneel.LatihanCRUD.model.Rating;
import com.id.dragneel.LatihanCRUD.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RatingController {
    
    @Autowired
    private RatingRepo ratingRepo;

    @GetMapping("/getAllRating")
    public ResponseEntity<List<Rating>> getAllRating(){
        try {
            List<Rating> ratingList = ratingRepo.findAll();

            if (ratingList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ratingList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRatingById/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id){
        Optional<Rating> ratingData = ratingRepo.findById(id);

        if (ratingData.isPresent()) {
            return new ResponseEntity<>(ratingData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating){
        return ratingRepo.save(rating);
    }

    @PostMapping("/updateRatingById/{id}")
    public ResponseEntity<Rating> updateGRatingById(@PathVariable Long id, @RequestBody Rating newRating){
        Optional<Rating> oldRating = ratingRepo.findById(id);

        if (oldRating.isPresent()) {
            Rating updateRating = oldRating.get();
            updateRating.setRating(newRating.getRating());

            Rating result = ratingRepo.save(updateRating);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteRatingById/{id}")
    public ResponseEntity<HttpStatus> deleteRatingById(@PathVariable Long id){
        ratingRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
