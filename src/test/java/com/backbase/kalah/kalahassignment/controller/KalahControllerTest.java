package com.backbase.kalah.kalahassignment.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KalahControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void games() throws MalformedURLException {
    ResponseEntity<GamesResponse> response = restTemplate.postForEntity(
        new URL("http://localhost:" + port + "/games").toString(),"{}", GamesResponse.class);
    assertEquals("1", response.getBody().getId());
    assertEquals("http://localhost:0/games/1", response.getBody().getUrl());
  }

  @Test
  void makeMove() {
  }
}