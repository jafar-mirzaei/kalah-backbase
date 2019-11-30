package com.backbase.kalah.kalahassignment.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GAME_STATUS")
public class GameStatusModel extends BaseAuditEntity {
  private Long id;

  private GameEntity gameEntity;
  private Integer pitId;
  private Integer stoneCount;
  private Boolean kalah;



  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "GAME_ID", nullable = false)
  @JsonBackReference
  public GameEntity getGameEntity() {
    return gameEntity;
  }

  public void setGameEntity(final GameEntity gameEntity) {
    this.gameEntity = gameEntity;
  }

  @Column
  public Integer getPitId() {
    return pitId;
  }

  public void setPitId(final Integer pitId) {
    this.pitId = pitId;
  }

  @Column
  public Integer getStoneCount() {
    return stoneCount;
  }

  public void setStoneCount(final Integer stoneCount) {
    this.stoneCount = stoneCount;
  }

  @Column
  public Boolean getKalah() {
    return kalah;
  }

  public void setKalah(final Boolean kalah) {
    this.kalah = kalah;
  }
}
