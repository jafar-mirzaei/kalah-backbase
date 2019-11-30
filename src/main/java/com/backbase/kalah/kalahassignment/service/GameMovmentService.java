package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatus;

import java.util.Optional;

/**
 * @author Jafar Mirzaei (mirzaie@caspco.ir)
 * @version 1.0 2019.1130
 * @since 1.0
 */
public final class GameMovmentService {
  public GameEntity move(final GameEntity gameEntity, final int pitId) throws InvalidPitIdException {
    final Optional<GameStatus> pitIdStatus =
        gameEntity.getGameStatuses().stream().filter(gameStatus -> gameStatus.getId() == pitId).findFirst();
    if (!pitIdStatus.isPresent() || pitIdStatus.get().getKalah()) {
      throw new InvalidPitIdException("Invalid pitId: " + pitId + ((pitIdStatus.isPresent() && pitIdStatus.get()
                                                                                                          .getKalah()) ? " (is KALAH) " : ""));
    }
    gameEntity.

  }
}

