package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

/**
 * Last movement in own empty pit then all stone in pit and opposite stone to own Kalah
 */
public class LastMovementInOwnEmptyPitGameRuleChain extends AbstractGameRuleChain {

  public LastMovementInOwnEmptyPitGameRuleChain(
      final GameRuleChain nexGameRuleChain) {super(nexGameRuleChain);}

  @Override public void applyRule(
      final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
    // Should Move all stone to own pit and opposite pit to own kalah if
    //  ( last stone in own pit AND after movement there is one stone in it)
    if (userRequestSessionData.getCurrentPlayer()
                                   .isMyPit((userRequestSessionData.getLastUpdatedStatusIndex()))
        &&
        !isLastUpdatedPitTheKalahOfCurrentUser()
        &&
        lastChangedStatusModel().getStoneCount() == 1) {

      final GameStatusModel currentUserKalahPitStatusModel =
          currentUserGameStatuses().parallelStream()
                                   .filter(gameStatusModel -> gameStatusModel.getPitId() ==
                                                              userRequestSessionData.getCurrentPlayer()
                                                                                         .kalahPitId())
                                   .findFirst()
                                   .get();

      final GameStatusModel lastChangedOppositStatusModel =
          currentUserGameStatuses()
              .parallelStream()
              .filter(gameStatusModel -> gameStatusModel.getPitId() ==
                                         (14 - userRequestSessionData.getLastUpdatedStatusIndex()))
              .findFirst()
              .get();
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
