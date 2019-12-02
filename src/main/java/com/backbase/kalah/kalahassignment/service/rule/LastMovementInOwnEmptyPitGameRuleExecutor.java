package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Last movement in own empty pit then all stone in pit and opposite stone to own Kalah
 */
@Service
@Order(value = 3)
public class LastMovementInOwnEmptyPitGameRuleExecutor extends AbstractGameRuleExecutor {

  @Override public void executeRule() {
    // Should Move all stone to own pit and opposite pit to own kalah if
    //  ( last stone in own pit AND after movement there is one stone in it)
    if (
        currentUserGameEntity().getPlayer()
                               .isMyPit((userRequestSessionData.getLastUpdatedStatusIndex()))
        &&
        !currentUserGameEntity().getPlayer()
                                .isMyKalahPit(userRequestSessionData.getLastUpdatedStatusIndex())
        &&
        lastChangedStatusModel().getStoneCount() == 1) {

      final GameStatusModel currentUserKalahPitStatusModel =
          currentUserGameStatuses().get(userRequestSessionData.getCurrentPlayer().kalahPitIndex());

      final GameStatusModel lastChangedOppositStatusModel =
          currentUserGameStatuses().get(14 - 2 - userRequestSessionData.getLastUpdatedStatusIndex());
      //REMOVE FROM LAST index and the opposite index and ADD to Kalah pit
      currentUserKalahPitStatusModel.setStoneCount(currentUserKalahPitStatusModel.getStoneCount() +
                                                   lastChangedStatusModel().getStoneCount() +
                                                   lastChangedOppositStatusModel.getStoneCount());

      lastChangedStatusModel().setStoneCount(0);
      lastChangedOppositStatusModel.setStoneCount(0);
    }
  }
}
