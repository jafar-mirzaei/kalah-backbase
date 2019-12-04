package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;

import java.util.concurrent.atomic.AtomicReference;

public final class DetectWinnerGameRuleChain extends AbstractGameRuleChain {


  public DetectWinnerGameRuleChain(
      final GameRuleChain nexGameRuleChain) {super(nexGameRuleChain);}

  @Override public void applyRule(
      final UserRequestSessionData userRequestSessionData) {
    this.userRequestSessionData = userRequestSessionData;
    if (currentUserGameEntity().isFinished()) {
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
          currentUserGameStatuses().get(currentUserGameEntity().getPlayer().getOpponent().kalahPitId());
      final GameStatusModel currentUserKalahPit =
          currentUserGameStatuses().get(currentUserGameEntity().getPlayer().getOpponent().kalahPitId());
      opponentKalahPit.setStoneCount(opponentKalahPit.getStoneCount() + sumOfRemindStone.get());
      currentUserGameEntity().setWinner(opponentKalahPit.getStoneCount() <
                                        currentUserKalahPit.getStoneCount() ? userRequestSessionData.getCurrentPlayer() :
                                            userRequestSessionData.getCurrentPlayer()
                                                                  .getOpponent());
    }
    callNext();
  }

}
