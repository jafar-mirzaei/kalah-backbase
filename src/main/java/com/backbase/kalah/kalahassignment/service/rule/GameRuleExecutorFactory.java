package com.backbase.kalah.kalahassignment.service.rule;

public class GameRuleExecutorFactory {
  public static GameRuleExecutor generateGame() {
    return new SowStoneGameRuleExecutor(
        new ChangeTurnGameRuleExecutor(
            new LastMovementInOwnEmptyPitGameRuleExecutor(
                new CheckFinishGameRuleExecutor(null))));
  }
}