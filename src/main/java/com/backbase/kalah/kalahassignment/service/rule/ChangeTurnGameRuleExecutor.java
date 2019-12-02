package com.backbase.kalah.kalahassignment.service.rule;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Last Movement in Own Kalah another turn
 */
@Service
@Order(value = 2)
public class ChangeTurnGameRuleExecutor extends AbstractGameRuleExecutor {

  @Override public void executeRule() {
    // Should Change turn if last pit is not player own Kalah pit
    if (!userRequestSessionData.getGameEntity()
                               .getPlayer()
                               .isMyKalahPit((userRequestSessionData.getLastUpdatedStatusIndex()))) {
      userRequestSessionData.getGameEntity().setPlayer(userRequestSessionData.getGameEntity()
                                                                             .getPlayer().getOpponent());
    }
  }
}
