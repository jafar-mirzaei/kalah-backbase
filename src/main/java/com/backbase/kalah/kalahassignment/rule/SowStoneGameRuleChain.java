package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.List;
import java.util.function.Supplier;

public class SowStoneGameRuleChain extends AbstractGameRuleChain {

  public SowStoneGameRuleChain(
      final GameRuleChain nexGameRuleChain) {super(nexGameRuleChain);}

  @Override public void applyRule(
      final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
    final List<GameStatusModel> gameStatusModels = userRequestSessionData.getGameEntity()
                                                                         .getGameStatusModels();
    final GameStatusModel selectedPit = gameStatusModels
        .stream()
        .filter(gameStatus -> userRequestSessionData.getMovementRequestedPitId() ==
                              gameStatus.getPitId())
        .findFirst()
        .orElseThrow((Supplier<RuntimeException>) () -> new InvalidPitIdException(
            "pitId:" + userRequestSessionData.getMovementRequestedPitId()));

    final int[] addStoneToPit = new int[]{userRequestSessionData.getMovementRequestedPitId()};
    int selectedPitStoneCount = selectedPit.getStoneCount();
    selectedPit.setStoneCount(0);
    while (selectedPitStoneCount > 0) {
      addStoneToPit[0] = (addStoneToPit[0] % gameStatusModels.size()) + 1;
      gameStatusModels.parallelStream()
                      .filter(gameStatusModel -> gameStatusModel.getPitId() == addStoneToPit[0])
                      .findFirst()
                      .ifPresent(gameStatusModel -> {
                        gameStatusModel.setStoneCount(gameStatusModel.getStoneCount() + 1);
                        userRequestSessionData.setLastUpdatedStatusIndex(gameStatusModel.getPitId());
                      });
      selectedPitStoneCount--;
    }
    callNext();
  }
}
