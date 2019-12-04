package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;

/**
 * If the last Movement in Own Kalah pit then player get another turn
 */
public class ChangeTurnGameRuleChain extends AbstractGameRuleChain {

  public ChangeTurnGameRuleChain(
      final GameRuleChain nexGameRuleChain) {super(nexGameRuleChain);}

  @Override public void applyRule(
      final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
    // Should Change turn if last pit is not player own Kalah pit
    if (!isLastUpdatedPitTheKalahOfCurrentUser()) {
      userRequestSessionData.getGameEntity().setPlayer(userRequestSessionData.getGameEntity()
                                                                             .getPlayer().getOpponent());
    }
    callNext();
  }
}
