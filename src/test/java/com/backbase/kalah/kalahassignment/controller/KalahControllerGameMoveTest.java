package com.backbase.kalah.kalahassignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KalahControllerGameMoveTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Value("classpath:test-data.json")
  private Resource resourceFile;
  @Autowired
  private ObjectMapper objectMapper;
  private String gameId;
  private MoveTestData moveTestData;

  @BeforeEach
  void setUp() throws IOException {
    ResponseEntity<GamesResponse> response = restTemplate.postForEntity(
        new URL("http://localhost:" + port + "/games").toString(), "{}", GamesResponse.class);
    gameId = response.getBody().getId();
    assertEquals("1", gameId);
    assertEquals("http://localhost:0/games/1", response.getBody().getUrl());
    moveTestData = objectMapper.readValue(resourceFile.getFile(), MoveTestData.class);
  }

  @Test
  void makeMove() throws MalformedURLException {
    for (int index = 0; index < moveTestData.getMove().size(); index++) {

      ResponseEntity<GameStatus> response = callMove(moveTestData.getMove().get(index));
      assertNotNull(response.getBody());
      for (int i = 0; i < 14; i++) {
        if (!
            response.getBody().getStatus().get(String.valueOf(i)).equals(
                moveTestData.getResult().get(index).get(i).toString())) {
          System.out.println("index:"+index + " and i:"+i );
          System.out.println("response.getBody().getStatus()"+response.getBody().getStatus().toString());
          System.out.println("moveTestData.getResult().get(index)"+moveTestData.getResult().get(index).toString());
          System.out.println("response.getBody().getStatus().get(String.valueOf(i))"+response.getBody().getStatus().get(String.valueOf(i)));
          System.out.println("moveTestData.getResult().get(index).get(i).toString()"+moveTestData.getResult().get(index).get(i).toString());
        }
      }
    }
  }

  private ResponseEntity<GameStatus> callMove(final int pitId) throws MalformedURLException {
    final String url = String.format("http://localhost:%d/games/%s/pits/%d", port, gameId, pitId);
    System.out.println(url);
    return restTemplate.exchange(
        new URL(url).toString(),
        HttpMethod.PUT, HttpEntity.EMPTY, GameStatus.class);
  }
}