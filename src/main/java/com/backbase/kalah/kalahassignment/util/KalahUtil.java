package com.backbase.kalah.kalahassignment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KalahUtil {
  @Value("${server.url}")
  private String serverUrl;

  public String getGameUrl(final Long gameId) {
    return serverUrl + "/games/" + gameId;
  }
}
