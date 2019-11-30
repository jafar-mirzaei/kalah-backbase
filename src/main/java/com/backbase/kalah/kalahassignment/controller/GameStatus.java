package com.backbase.kalah.kalahassignment.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

/**
 * @author Jafar Mirzaei (mirzaie@caspco.ir)
 * @version 1.0 2019.1130
 * @since 1.0
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "status"
})
public final class GameStatus {
  @JsonProperty("id")
  private String id;
  @JsonProperty("url")
  private String url;
  @JsonProperty("status")
  private Map<String, String> status;

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(final String id) {
    this.id = id;
  }

  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(final String url) {
    this.url = url;
  }

  @JsonProperty("status")
  public Map<String, String> getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(final Map<String, String> status) {
    this.status = status;
  }
}

