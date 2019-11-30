package com.backbase.kalah.kalahassignment.persistance.model;

public final class GameStatusModelBuilder {
  private GameEntity gameEntity;
  private Integer pitId;
  private Integer stoneCount;
  private Boolean kalah;

  private GameStatusModelBuilder() {}

  public static GameStatusModelBuilder aGameStatus() { return new GameStatusModelBuilder(); }

  public GameStatusModelBuilder withGameEntity(GameEntity gameEntity) {
    this.gameEntity = gameEntity;
    return this;
  }

  public GameStatusModelBuilder withPitId(Integer pitId) {
    this.pitId = pitId;
    return this;
  }

  public GameStatusModelBuilder withStoneCount(Integer stoneCount) {
    this.stoneCount = stoneCount;
    return this;
  }

  public GameStatusModelBuilder withKalah(Boolean kalah) {
    this.kalah = kalah;
    return this;
  }

  public GameStatusModel build() {
    GameStatusModel gameStatusModel = new GameStatusModel();
    gameStatusModel.setGameEntity(gameEntity);
    gameStatusModel.setPitId(pitId);
    gameStatusModel.setStoneCount(stoneCount);
    gameStatusModel.setKalah(kalah);
    return gameStatusModel;
  }
}
