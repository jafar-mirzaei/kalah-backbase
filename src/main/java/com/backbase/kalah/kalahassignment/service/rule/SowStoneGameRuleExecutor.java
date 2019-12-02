package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class SowStoneGameRuleExecutor extends AbstractGameRuleExecutor {

  @Override public void executeRule() {
    final List<GameStatusModel> gameStatusModels = userRequestSessionData.getGameEntity()
                                                                         .getGameStatusModels();
    final GameStatusModel first;

    first = gameStatusModels
        .stream()
        .filter(gameStatus -> userRequestSessionData.getPitId() ==
                              gameStatus.getPitId())
        .findFirst()
        .orElseThrow((Supplier<RuntimeException>) () -> new InvalidPitIdException(
            "pitId:" + userRequestSessionData.getPitId()));
    int addToIndex = gameStatusModels.indexOf(first);
    while (first.getStoneCount() > 0) {
      addToIndex = (addToIndex + 1) % gameStatusModels.size();
      gameStatusModels.get(addToIndex).setStoneCount(gameStatusModels.get(addToIndex).getStoneCount() + 1);
      first.setStoneCount(first.getStoneCount() - 1);
    }
    userRequestSessionData.setLastUpdatedStatusIndex(addToIndex);
  }
}
