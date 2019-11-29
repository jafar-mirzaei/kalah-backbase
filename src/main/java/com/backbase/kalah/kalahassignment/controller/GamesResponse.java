package com.backbase.kalah.kalahassignment.controller;

public class GamesResponse {
  private final String id;
  private final String url;

  public GamesResponse(final String id, final String url) {
    this.id = id;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }
}
