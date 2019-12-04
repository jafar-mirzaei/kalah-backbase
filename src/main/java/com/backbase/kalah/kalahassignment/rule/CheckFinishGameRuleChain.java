package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.Optional;

public final class CheckFinishGameRuleChain extends AbstractGameRuleChain {


  public CheckFinishGameRuleChain(
      final GameRuleChain nexGameRuleChain) {super(nexGameRuleChain);}

  @Override public void applyRule(
      final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
    final Optional<GameStatusModel> findNonEmptyPit = currentUserGameStatuses().stream()
                                                                               .filter(gameStatusModel -> userRequestSessionData
                                                                                   .getCurrentPlayer()
                                                                                   .isMyPit(
                                                                                       gameStatusModel
                                                                                           .getPitId()))
                                                                               .filter(gameStatusModel -> !gameStatusModel
                                                                                   .getKalah())
                                                                               .filter(gameStatusModel ->
                                                                                           gameStatusModel.getStoneCount() !=
                                                                                           0)
                                                                               .findFirst();
    //TODO Should we finished the game when one of Kalah's pit contains more than 36 (6*6) stone?
    if (!findNonEmptyPit.isPresent()) {
      currentUserGameEntity().setFinished(true);
    }
    callNext();
  }

}
