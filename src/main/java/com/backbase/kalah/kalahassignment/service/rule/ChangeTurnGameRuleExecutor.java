package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;

/**
 * Last Movement in Own Kalah another turn
 */
public class ChangeTurnGameRuleExecutor extends AbstractGameRuleExecutor {

  public ChangeTurnGameRuleExecutor(
      final GameRuleExecutor nexGameRuleExecutor) {super(nexGameRuleExecutor);}

  @Override public void executeRule(
      final UserRequestSessionData userRequestSessionData) {
    super.executeRule(userRequestSessionData);
    // Should Change turn if last pit is not player own Kalah pit
    if (!getUserRequestSessionData().getGameEntity()
                                    .getPlayer()
                                    .isMyKalahPit((getUserRequestSessionData().getLastUpdatedStatusIndex()))) {
      getUserRequestSessionData().getGameEntity().setPlayer(getUserRequestSessionData().getGameEntity()
                                                                                       .getPlayer().getOpponent());
    }
    callNext();
  }
}
