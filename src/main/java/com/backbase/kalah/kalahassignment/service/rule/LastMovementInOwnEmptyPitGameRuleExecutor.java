package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Last movement in own empty pit then all stone in pit and opposite stone to own Kalah
 */
public class LastMovementInOwnEmptyPitGameRuleExecutor extends AbstractGameRuleExecutor {

  public LastMovementInOwnEmptyPitGameRuleExecutor(
      final GameRuleExecutor nexGameRuleExecutor) {super(nexGameRuleExecutor);}

  @Override public void executeRule(
      final UserRequestSessionData userRequestSessionData) {
    super.executeRule(userRequestSessionData);
    // Should Move all stone to own pit and opposite pit to own kalah if
    //  ( last stone in own pit AND after movement there is one stone in it)
    if (
        getUserRequestSessionData().getCurrentPlayer()
                               .isMyPit((getUserRequestSessionData().getLastUpdatedStatusIndex()))
        &&
        !currentUserGameEntity().getPlayer()
                                .isMyKalahPit(getUserRequestSessionData().getLastUpdatedStatusIndex())
        &&
        lastChangedStatusModel().getStoneCount() == 1) {

      final GameStatusModel currentUserKalahPitStatusModel =
          currentUserGameStatuses().get(getUserRequestSessionData().getCurrentPlayer().kalahPitIndex());

      final GameStatusModel lastChangedOppositStatusModel =
          currentUserGameStatuses().get(14 - 2 - getUserRequestSessionData().getLastUpdatedStatusIndex());
      //REMOVE FROM LAST index and the opposite index and ADD to Kalah pit
      currentUserKalahPitStatusModel.setStoneCount(currentUserKalahPitStatusModel.getStoneCount() +
                                                   lastChangedStatusModel().getStoneCount() +
                                                   lastChangedOppositStatusModel.getStoneCount());

      lastChangedStatusModel().setStoneCount(0);
      lastChangedOppositStatusModel.setStoneCount(0);
    }
    callNext();
  }
}
