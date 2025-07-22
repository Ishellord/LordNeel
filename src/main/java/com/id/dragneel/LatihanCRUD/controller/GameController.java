package com.id.dragneel.LatihanCRUD.controller;

import com.id.dragneel.LatihanCRUD.model.Game;
import com.id.dragneel.LatihanCRUD.model.Platform;
import com.id.dragneel.LatihanCRUD.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    private GameRepo gameRepo;

    @GetMapping("/getAllGames")
    public ResponseEntity<List<Game>> getAllGames(){
        try{
            List<Game> gameList = gameRepo.findAll();

            if(gameList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gameList,HttpStatus.OK);

        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getGameById/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id){
        Optional<Game> gameData = gameRepo.findById(id);

        if(gameData.isPresent()){
            return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addGame")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        try {
            Game savedGame = gameRepo.save(game);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateGameById/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id, @RequestBody Game newGameData){
        Optional<Game> oldGameData = gameRepo.findById(id);

        if(oldGameData.isPresent()){
            Game updateGameData = oldGameData.get();
            updateGameData.setTitle(newGameData.getTitle());
            updateGameData.setReleaseYear(newGameData.getReleaseYear());
            updateGameData.setDeveloper(newGameData.getDeveloper());
            updateGameData.setPlatform(newGameData.getPlatform());
            updateGameData.setRating(newGameData.getRating());

            Game gameObj = gameRepo.save(updateGameData);
            return new ResponseEntity<>(gameObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteGameById/{id}")
    public ResponseEntity<HttpStatus> deleteGameById(@PathVariable long id){
        gameRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
