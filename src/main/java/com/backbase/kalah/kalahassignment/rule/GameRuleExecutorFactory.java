package com.backbase.kalah.kalahassignment.rule;

public class GameRuleExecutorFactory {
  public static GameRuleChain generateGameRuleChain() {
    return new SowStoneGameRuleChain(
        new ChangeTurnGameRuleChain(
            new LastMovementInOwnEmptyPitGameRuleChain(
                new CheckFinishGameRuleChain(
                    new DetectWinnerGameRuleChain(null)
                ))));
  }
}