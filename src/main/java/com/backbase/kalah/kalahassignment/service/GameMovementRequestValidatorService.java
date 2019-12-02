package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.exception.EmptyPitIdSelectedException;
import com.backbase.kalah.kalahassignment.exception.GameFinishedException;
import com.backbase.kalah.kalahassignment.exception.InvalidGameIdException;
import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameMovementRequestValidatorService {
  private final GameRepository gameRepository;

  @Autowired
  public GameMovementRequestValidatorService(final GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public UserRequestSessionData validateAndInitializeMoveRequest(final Long gameId, final int pitId) {
    final Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
    if (!gameEntity.isPresent()) {
      throw new InvalidGameIdException("GameId:" + gameId+" not found.");
    }
    if (gameEntity.get().isFinished()) {
      throw new GameFinishedException("GameId:" + gameId +" is Finished");
    }
    final Optional<GameStatusModel> selectedPit = gameEntity.get()
                                                            .getGameStatusModels()
                                                            .stream()
                                                            .filter(gameStatusModel -> gameStatusModel.getPitId() ==
                                                                                       pitId)
                                                            .findFirst();
    if (!selectedPit.isPresent()) {
      throw new InvalidPitIdException("PitId:" + pitId);
    } else if (selectedPit.get().getKalah()) {
      throw new InvalidPitIdException("PitId:" + pitId + " is KALAH!!!");
    } else if (selectedPit.get().getStoneCount() < 1) {
      throw new EmptyPitIdSelectedException("PitId:" + pitId);
    }
    if (!gameEntity.get().getPlayer().isMyPit(pitId)) {
      throw new InvalidPitIdException("Opponent PitId:" + pitId);
    }
    return new UserRequestSessionData(gameEntity.get(), pitId, gameEntity.get().getPlayer());
  }
}
