package com.backbase.kalah.kalahassignment.exception;

import java.util.Date;

public class ErrorDto {
  private final Date timestamp;
  private final String errorMessage;

  public ErrorDto(final Date timestamp, final String errorMessage) {
    this.timestamp = timestamp;
    this.errorMessage = errorMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
