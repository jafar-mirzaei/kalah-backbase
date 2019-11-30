package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.GameStatus;
import com.backbase.kalah.kalahassignment.controller.GamesResponse;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import com.backbase.kalah.kalahassignment.service.rule.GameRuleExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
  private final GameRepository gameRepository;
  private final GameMovmentService gameMovmentService;
  private final List<GameRuleExecuter> gameRuleExecuterList;

  @Autowired
  public GameService(
      final GameRepository gameRepository,
      final List<GameRuleExecuter> gameRuleExecuterList) {
    this.gameRepository=gameRepository;
    this.gameRuleExecuterList = gameRuleExecuterList;
  }

  public GamesResponse getGame() {


  }

  public GameStatus move(final Long gameId, final int pitId) {
    final Optional<GameEntity> byId = gameRepository.findById(gameId);
    if(byId.isPresent()){

    }

  }
}
