package com.backbase.kalah.kalahassignment.service.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public final class CheckFinishGameRuleExecutor extends AbstractGameRuleExecutor {


  public CheckFinishGameRuleExecutor(
      final GameRuleExecutor nexGameRuleExecutor) {super(nexGameRuleExecutor);}

  @Override public void executeRule(
      final UserRequestSessionData userRequestSessionData) {
    super.executeRule(userRequestSessionData);
    final Optional<GameStatusModel> findNonEmptyPit = currentUserGameStatuses().stream()
                                                                               .filter(gameStatusModel -> getUserRequestSessionData()
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
                               .filter(gameStatusModel -> getUserRequestSessionData().getCurrentPlayer().getOpponent()
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
                                        currentUserKalahPit.getStoneCount() ? getUserRequestSessionData().getCurrentPlayer() : getUserRequestSessionData()
          .getCurrentPlayer()
          .getOpponent());
    }
    callNext();
  }

}
