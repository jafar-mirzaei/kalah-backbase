package com.backbase.kalah.kalahassignment.persistance.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GAME")
public class GameEntity extends BaseAuditEntity {
  private Long gameId;
  private List<GameStatus> gameStatuses;
  private String turnSessionId;

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getGameId() {
    return gameId;
  }

  public void setGameId(final Long gameId) {
    this.gameId = gameId;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonManagedReference
  public List<GameStatus> getGameStatuses() {
    return gameStatuses;
  }

  public void setGameStatuses(final List<GameStatus> gameStatuses) {
    this.gameStatuses = gameStatuses;
  }

  @Column(length = 250)
  public String getTurnSessionId() {
    return turnSessionId;
  }

  public void setTurnSessionId(final String turnSessionId) {
    this.turnSessionId = turnSessionId;
  }
}
