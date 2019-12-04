package com.backbase.kalah.kalahassignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
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

      final Integer movementPitId = moveTestData.getMove().get(index);
      ResponseEntity<GameStatus> response = callMove(movementPitId);
      assertNotNull(response.getBody());
      System.out.println(" response:" + response.getBody()
                                                .getStatus()
                                                .entrySet()
                                                .stream()
                                                .sorted(Comparator.comparing(o -> Integer.valueOf(o.getKey())))
                                                .flatMap(stringStringEntry -> Stream.of(
                                                    stringStringEntry.getKey() + "=" +
                                                    stringStringEntry.getValue())).collect(Collectors.toList())
                                                .toString());
      final List<Integer> responseShouldEqual = moveTestData.getResult().get(index);
      Map<Integer, Integer> collect = new HashMap<>();
      for (int i = 0; i < responseShouldEqual.size(); i++) {
        collect.put(i + 1, responseShouldEqual.get(i));
      }
      System.out.println("should be:" + collect);
      for (int i = 1; i <= 14; i++) {
        final int finalI = i;
        final Optional<Map.Entry<String, String>> finded = response.getBody()
                                                                   .getStatus()
                                                                   .entrySet()
                                                                   .parallelStream()
                                                                   .filter(stringStringEntry ->
                                                                               stringStringEntry.getKey()
                                                                                                .equals(String.valueOf(
                                                                                                    finalI)))
                                                                   .findFirst();

        assertTrue(finded.isPresent());
        assertEquals(finded.get().getValue(), String.valueOf(responseShouldEqual.get(i - 1)));
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