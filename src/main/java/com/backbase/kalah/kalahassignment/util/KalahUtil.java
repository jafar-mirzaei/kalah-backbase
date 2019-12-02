package com.backbase.kalah.kalahassignment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KalahUtil {
  private static String SERVER_URL;

  public static String getGameUrl(final Long gameId) {
    return SERVER_URL + "/games/" + gameId;
  }

  @Value("${server.url:http://localhost:${server.port}}")
  public void setServerUrl(final String serverUrl) {
    SERVER_URL = serverUrl;
  }
}
