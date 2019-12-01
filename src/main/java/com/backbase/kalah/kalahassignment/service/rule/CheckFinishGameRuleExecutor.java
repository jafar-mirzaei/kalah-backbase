package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class CheckFinishGameRuleExecutor extends AbstractGameRuleExecutor {


  @Override public void executeRule() throws Throwable {
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
    if (!findNonEmptyPit.isPresent()) {
      final AtomicReference<Integer> sumOfRemindStone = new AtomicReference<>(0);
      currentUserGameStatuses().stream()
                               .filter(gameStatusModel -> userRequestSessionData.getCurrentPlayer().getOpponent()
                                                                                .isMyPit(
                                                                                    gameStatusModel
                                                                                        .getPitId()))
                               .filter(gameStatusModel -> !gameStatusModel.getKalah())
                               .forEach(gameStatusModel -> {
                                 sumOfRemindStone.updateAndGet(v -> v + gameStatusModel.getStoneCount());
                                 gameStatusModel.setStoneCount(0);
                               });
      final GameStatusModel opponentKalahPit =
          currentUserGameStatuses().get(currentUserGameEntity().getPlayer().getOpponent().kalahPitIndex());
      final GameStatusModel currentUserKalahPit =
          currentUserGameStatuses().get(currentUserGameEntity().getPlayer().getOpponent().kalahPitIndex());
      opponentKalahPit.setStoneCount(opponentKalahPit.getStoneCount() + sumOfRemindStone.get());
      currentUserGameEntity().setFinished(true);
      currentUserGameEntity().setWinner(opponentKalahPit.getStoneCount() <
                                        currentUserKalahPit.getStoneCount() ? userRequestSessionData.getCurrentPlayer() : userRequestSessionData
          .getCurrentPlayer()
          .getOpponent());
    }
  }

}
