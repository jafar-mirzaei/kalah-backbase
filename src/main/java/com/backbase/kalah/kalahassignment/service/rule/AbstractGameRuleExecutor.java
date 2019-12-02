package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.List;

public abstract class AbstractGameRuleExecutor implements GameRuleExecutor {
  private final GameRuleExecutor nexGameRuleExecutor;
  private UserRequestSessionData userRequestSessionData;

  protected AbstractGameRuleExecutor(
      final GameRuleExecutor nexGameRuleExecutor) {
    this.nexGameRuleExecutor = nexGameRuleExecutor;
  }

  @Override public void executeRule(final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
  }

  protected GameEntity currentUserGameEntity() {
    return getUserRequestSessionData().getGameEntity();
  }

  protected UserRequestSessionData getUserRequestSessionData() {
    return this.userRequestSessionData;
  }

  protected List<GameStatusModel> currentUserGameStatuses() {
    return getUserRequestSessionData().getGameEntity().getGameStatusModels();
  }

  protected GameStatusModel lastChangedStatusModel() {
    return getUserRequestSessionData().getGameEntity()
                                      .getGameStatusModels()
                                      .get(getUserRequestSessionData().getLastUpdatedStatusIndex());
  }

  public GameRuleExecutor getNextGaleRule() {
    return this.nexGameRuleExecutor;
  }

  protected void callNext() {
    if (getNextGaleRule() != null) {
      getNextGaleRule().executeRule(getUserRequestSessionData());
    }
  }
}
