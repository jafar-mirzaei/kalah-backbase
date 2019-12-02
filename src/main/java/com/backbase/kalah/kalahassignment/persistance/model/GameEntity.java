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
  private List<GameStatusModel> gameStatusModels;
  private Player player;
  private Player winner;
  private boolean finished;

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
  public List<GameStatusModel> getGameStatusModels() {
    return gameStatusModels;
  }

  public void setGameStatusModels(final List<GameStatusModel> gameStatusModels) {
    this.gameStatusModels = gameStatusModels;
  }

  @Column
  public boolean isFinished() {
    return finished;
  }

  public void setFinished(final boolean finished) {
    this.finished = finished;
  }

  @Column
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(final Player player) {
    this.player = player;
  }

  @Column
  public Player getWinner() {
    return winner;
  }

  public void setWinner(final Player winner) {
    this.winner = winner;
  }
}
