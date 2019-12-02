package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.List;
import java.util.function.Supplier;

public class SowStoneGameRuleExecutor extends AbstractGameRuleExecutor {

  public SowStoneGameRuleExecutor(
      final GameRuleExecutor nexGameRuleExecutor) {super(nexGameRuleExecutor);}

  @Override public void executeRule(
      final UserRequestSessionData userRequestSessionData) {
    super.executeRule(userRequestSessionData);
    final List<GameStatusModel> gameStatusModels = getUserRequestSessionData().getGameEntity()
                                                                              .getGameStatusModels();
    final GameStatusModel first;

    first = gameStatusModels
        .stream()
        .filter(gameStatus -> getUserRequestSessionData().getMovementRequestedPitId() ==
                              gameStatus.getPitId())
        .findFirst()
        .orElseThrow((Supplier<RuntimeException>) () -> new InvalidPitIdException(
            "pitId:" + getUserRequestSessionData().getMovementRequestedPitId()));
    int addToIndex = gameStatusModels.indexOf(first);
    while (first.getStoneCount() > 0) {
      addToIndex = (addToIndex + 1) % gameStatusModels.size();
      gameStatusModels.get(addToIndex).setStoneCount(gameStatusModels.get(addToIndex).getStoneCount() + 1);
      first.setStoneCount(first.getStoneCount() - 1);
    }
    getUserRequestSessionData().setLastUpdatedStatusIndex(addToIndex);
    callNext();
  }
}
