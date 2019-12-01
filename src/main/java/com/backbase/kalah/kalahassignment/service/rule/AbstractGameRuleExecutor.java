package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractGameRuleExecutor implements GameRuleExecutor {
  @Autowired protected UserRequestSessionData userRequestSessionData;

  protected GameEntity currentUserGameEntity() {
    return userRequestSessionData.getGameEntity();
  }

  protected List<GameStatusModel> currentUserGameStatuses() {
    return userRequestSessionData.getGameEntity().getGameStatusModels();
  }

  protected GameStatusModel lastChangedStatusModel() {
    return userRequestSessionData.getGameEntity()
                                 .getGameStatusModels()
                                 .get(userRequestSessionData.getLastUpdatedStatusIndex());
  }
}
