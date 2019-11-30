package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class MovementGameRuleExecutor extends AbstractGameRuleExecutor {

  @Override public void executeRule() throws Throwable {
    final List<GameStatusModel> gameStatusModels = userRequestSessionData.getGameEntity()
                                                                         .getGameStatusModels();
    final GameStatusModel first = gameStatusModels
        .stream()
        .filter(gameStatus -> userRequestSessionData.getPitId() ==
                              gameStatus.getPitId())
        .findFirst()
        .orElseThrow((Supplier<Throwable>) () -> new InvalidPitIdException(
            "pitId:" + userRequestSessionData.getPitId()));
    final int indexOf = gameStatusModels.indexOf(first);

    while (first.getStoneCount() > 0) {
      final int addToIndex = gameStatusModels.size() % (indexOf + 1);
      gameStatusModels.get(addToIndex).setStoneCount(gameStatusModels.get(addToIndex).getStoneCount() + 1);
      first.setStoneCount(first.getStoneCount() - 1);
    }
  }
}
