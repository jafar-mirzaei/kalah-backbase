package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.exception.GameFinishedException;
import com.backbase.kalah.kalahassignment.exception.InvalidGameIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import com.backbase.kalah.kalahassignment.service.rule.GameRuleExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
  private final GameRepository gameRepository;
  private final GameCreationService gameCreationService;
  private final List<GameRuleExecutor> gameRuleExecutorList;
  private final UserRequestSessionData userRequestSessionData;

  @Autowired
  public GameService(
      final GameRepository gameRepository,
      final GameCreationService gameCreationService,
      final List<GameRuleExecutor> gameRuleExecutorList,
      final UserRequestSessionData userRequestSessionData) {
    this.gameRepository = gameRepository;
    this.gameCreationService = gameCreationService;
    this.gameRuleExecutorList = gameRuleExecutorList;
    this.userRequestSessionData = userRequestSessionData;
  }

  public GamesResponse getGame() {
    return gameCreationService.create();

  }

  public GameStatus move(final Long gameId, final Integer pitId) throws Throwable {
    validateAndInitializeMoveRequest(gameId, pitId);
    for (GameRuleExecutor gameRuleExecutor : gameRuleExecutorList) {
      gameRuleExecutor.executeRule();
    }
    gameRepository.save(userRequestSessionData.getGameEntity());
    return userRequestSessionData.getFinalGameStatus();
  }

  private void validateAndInitializeMoveRequest(final Long gameId, final int pitId) throws InvalidGameIdException,
                                                                                           GameFinishedException {
    final Optional<GameEntity> byId = gameRepository.findById(gameId);
    if (!byId.isPresent()) {
      throw new InvalidGameIdException("GameId:" + gameId);
    }
    if (byId.get().isFinished()) {
      throw new GameFinishedException("Gameid:" + gameId);
    }
    userRequestSessionData.setGameEntity(byId.get());
    userRequestSessionData.setPitId(pitId);
    //Before change turn need to save current player in request session
    userRequestSessionData.setCurrentPlayer(userRequestSessionData.getGameEntity().getPlayer());
  }
}
