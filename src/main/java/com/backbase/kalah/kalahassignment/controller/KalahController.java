package com.backbase.kalah.kalahassignment.controller;

import com.backbase.kalah.kalahassignment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KalahController {

  @Autowired
  private GameService gameService;
  @PostMapping(path = "/games")
  public GamesResponse games(){
return gameService.getGame();
  }


}
