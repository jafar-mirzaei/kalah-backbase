package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.List;

public abstract class AbstractGameRuleChain implements GameRuleChain {
  private final GameRuleChain nexGameRuleChain;
  protected UserRequestSessionData userRequestSessionData;

  protected AbstractGameRuleChain(
      final GameRuleChain nexGameRuleChain) {
    this.nexGameRuleChain = nexGameRuleChain;
  }

  protected GameEntity currentUserGameEntity() {
    return userRequestSessionData.getGameEntity();
  }

  protected List<GameStatusModel> currentUserGameStatuses() {
    return userRequestSessionData.getGameEntity().getGameStatusModels();
  }

  protected GameStatusModel lastChangedStatusModel() {
    return userRequestSessionData.getGameEntity()
                                 .getGameStatusModels()
                                 .parallelStream()
                                 .filter(gameStatusModel -> gameStatusModel.getPitId() ==
                                                            userRequestSessionData.getLastUpdatedStatusIndex())
                                 .findFirst()
                                 .get();
  }

  public GameRuleChain getNextGaleRule() {
    return this.nexGameRuleChain;
  }

  protected void callNext() {
    if (getNextGaleRule() != null) {
      getNextGaleRule().applyRule(userRequestSessionData);
    }
  }

  protected boolean isLastUpdatedPitTheKalahOfCurrentUser() {
    return userRequestSessionData.getGameEntity()
                                 .getPlayer()
                                 .isMyKalahPit((userRequestSessionData.getLastUpdatedStatusIndex()));
  }
}
