package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * Last Movement in Own Kalah another turn
 */
@Service
@Order(value = 2)
public class ChangeTurnGameRuleExecutor extends AbstractGameRuleExecutor {

  @Override public void executeRule() throws Throwable {
    // Should Change turn if last pit is not player own Kalah pit
    if (!userRequestSessionData.getGameEntity()
                               .getPlayer()
                               .isMyKalahPit((userRequestSessionData.getLastUpdatedStatusIndex()))) {
      userRequestSessionData.getGameEntity().setPlayer(userRequestSessionData.getGameEntity()
                                                                             .getPlayer().getOpponent());
    }
  }
}
