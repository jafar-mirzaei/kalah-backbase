package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import com.backbase.kalah.kalahassignment.rule.GameRuleExecutorFactory;
import com.backbase.kalah.kalahassignment.service.validator.GameMovementRequestValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GameAdapterService {
  private final GameMovementRequestValidatorService gameMovementRequestValidatorService;
  private final GameCreationService gameCreationService;
  private final GameRepository gameRepository;


  @Autowired
  public GameAdapterService(
      final GameMovementRequestValidatorService gameMovementRequestValidatorService,
      final GameCreationService gameCreationService,
      final GameRepository gameRepository) {
    this.gameMovementRequestValidatorService = gameMovementRequestValidatorService;
    this.gameCreationService = gameCreationService;
    this.gameRepository = gameRepository;
  }

  public GamesResponse createGame() {
    return gameCreationService.create();

  }

  @Transactional
  public GameStatus move(final Long gameId, final Integer pitId) {
    final UserRequestSessionData userRequestSessionData =
        gameMovementRequestValidatorService.validateAndInitializeMoveRequest(gameId, pitId);
    GameRuleExecutorFactory.generateGameRuleChain().applyRule(userRequestSessionData);
    gameRepository.save(userRequestSessionData.getGameEntity());
    return userRequestSessionData.getFinalGameStatus();
  }
}
