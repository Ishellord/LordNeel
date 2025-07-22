package com.id.dragneel.LatihanCRUD.controller;

import com.id.dragneel.LatihanCRUD.model.Genre;
import com.id.dragneel.LatihanCRUD.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GenreController {

    @Autowired
    private GenreRepo genreRepo;

    @GetMapping("/getAllGenres")
    public ResponseEntity<List<Genre>> getAllGenres(){
        try {
            List<Genre> genreList = genreRepo.findAll();

            if (genreList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(genreList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getGenreById/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id){
        Optional<Genre> genreData = genreRepo.findById(id);

        if (genreData.isPresent()) {
            return new ResponseEntity<>(genreData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addGenre")
    public Genre addGenre(@RequestBody Genre genre){
        return genreRepo.save(genre);
    }

    @PostMapping("/updateGenreById/{id}")
    public ResponseEntity<Genre> updateGenreById(@PathVariable Long id, @RequestBody Genre newGenre){
        Optional<Genre> oldGenre = genreRepo.findById(id);

        if (oldGenre.isPresent()) {
            Genre updateGenre = oldGenre.get();
            updateGenre.setName(newGenre.getName());

            Genre result = genreRepo.save(updateGenre);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteGenreById/{id}")
    public ResponseEntity<HttpStatus> deleteGenreById(@PathVariable Long id){
        genreRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}