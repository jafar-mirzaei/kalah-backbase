package com.backbase.kalah.kalahassignment.controller;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class KalahController {

  @Autowired
  private GameService gameService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GamesResponse games() {
    return gameService.getGame();
  }

  @PutMapping("/{gameId}/pits/{pitId}")
  public GameStatus makeMove(@PathVariable Long gameId,
                             @PathVariable Integer pitId) throws Throwable {

    return gameService.move(gameId, pitId);
  }

}
