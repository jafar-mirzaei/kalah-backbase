package com.backbase.kalah.kalahassignment.controller.dto;

import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import com.backbase.kalah.kalahassignment.util.KalahUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.stream.Collectors;

@RequestScope
@Component
public class UserRequestSessionData {
  @Autowired
  private KalahUtil kalahUtil;
  private GameEntity gameEntity;
  private int pitId;


  public GameEntity getGameEntity() {
    return gameEntity;
  }

  public void setGameEntity(final GameEntity gameEntity) {
    this.gameEntity = gameEntity;
  }

  public int getPitId() {
    return pitId;
  }

  public void setPitId(final int pitId) {
    this.pitId = pitId;
  }

  public GameStatus getFinalGameStatus() {
    return GameStatusBuilder.aGameStatus()
                            .withId(String.valueOf(getGameEntity().getGameId()))
                            .withUrl(kalahUtil.getGameUrl(gameEntity.getGameId()))
                            .withStatus(gameEntity.getGameStatusModels().stream().collect(Collectors.toMap(
                                (GameStatusModel gameStatusModel1) -> String.valueOf(gameStatusModel1.getPitId()),
                                gameStatusModel -> String.valueOf(gameStatusModel
                                                                      .getStoneCount())))).build();
  }
}
