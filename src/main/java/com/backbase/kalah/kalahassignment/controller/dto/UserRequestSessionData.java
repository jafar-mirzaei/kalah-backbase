package com.backbase.kalah.kalahassignment.controller.dto;

import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import com.backbase.kalah.kalahassignment.persistance.model.Player;
import com.backbase.kalah.kalahassignment.util.KalahUtil;

import java.util.stream.Collectors;


public class UserRequestSessionData {
  private final GameEntity gameEntity;
  private final int movementRequestedPitId;
  private final Player currentPlayer;
  private int lastUpdatedStatusIndex;

  public UserRequestSessionData(
      final GameEntity gameEntity,
      final int movementRequestedPitId,
      final Player currentPlayer) {
    this.gameEntity = gameEntity;
    this.movementRequestedPitId = movementRequestedPitId;
    this.currentPlayer = currentPlayer;
  }


  public GameEntity getGameEntity() {
    return gameEntity;
  }

  public int getMovementRequestedPitId() {
    return movementRequestedPitId;
  }

  public GameStatus getFinalGameStatus() {
    return GameStatusBuilder.aGameStatus()
                            .withId(String.valueOf(getGameEntity().getGameId()))
                            .withUrl(KalahUtil.getGameUrl(gameEntity.getGameId()))
                            .withStatus(gameEntity.getGameStatusModels().stream().collect(Collectors.toMap(
                                (GameStatusModel gameStatusModel1) -> String.valueOf(gameStatusModel1.getPitId()),
                                gameStatusModel -> String.valueOf(gameStatusModel
                                                                      .getStoneCount())))).build();
  }

  public int getLastUpdatedStatusIndex() {
    return lastUpdatedStatusIndex;
  }

  public void setLastUpdatedStatusIndex(final int lastUpdatedStatusIndex) {
    this.lastUpdatedStatusIndex = lastUpdatedStatusIndex;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }
}