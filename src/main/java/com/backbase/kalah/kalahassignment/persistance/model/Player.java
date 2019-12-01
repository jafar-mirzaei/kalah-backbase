package com.backbase.kalah.kalahassignment.persistance.model;

public enum Player {
  FIRST, SECOND;

  private final int FIRST_KALAH_PIT_INDEX = 6;
  private final int SECOND_KALAH_PIT_INDEX = 13;

  public boolean isMyPit(final int pitNumber) {
    if (this == FIRST) {
      return pitNumber < 7;
    }
    return pitNumber > FIRST_KALAH_PIT_INDEX;
  }

  public boolean isMyKalahPit(final int pitNumber) {
    if (this == FIRST) {
      return pitNumber == FIRST_KALAH_PIT_INDEX;
    }
    return pitNumber == SECOND_KALAH_PIT_INDEX;
  }

  public Player getOpponent() {
    return this == FIRST ? SECOND : FIRST;
  }

  public int kalahPitIndex() {
    return this == FIRST ? FIRST_KALAH_PIT_INDEX : SECOND_KALAH_PIT_INDEX;
  }
}
