package com.backbase.kalah.kalahassignment.controller.dto;

import java.util.Map;

public final class GameStatusBuilder {
  private String id;
  private String url;
  private Map<String, String> status;

  private GameStatusBuilder() {}

  public static GameStatusBuilder aGameStatus() { return new GameStatusBuilder(); }

  public GameStatusBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public GameStatusBuilder withUrl(String url) {
    this.url = url;
    return this;
  }

  public GameStatusBuilder withStatus(Map<String, String> status) {
    this.status = status;
    return this;
  }

  public GameStatus build() {
    GameStatus gameStatus = new GameStatus();
    gameStatus.setId(id);
    gameStatus.setUrl(url);
    gameStatus.setStatus(status);
    return gameStatus;
  }
}
