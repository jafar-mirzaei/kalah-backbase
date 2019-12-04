package com.backbase.kalah.kalahassignment.service.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.exception.InvalidGameIdException;
import com.backbase.kalah.kalahassignment.exception.InvalidPitIdException;
import com.backbase.kalah.kalahassignment.service.GameCreationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameMovementRequestValidatorServiceTest {


  @Autowired
  private GameCreationService gameCreationService;
  @Autowired
  private GameMovementRequestValidatorService gameMovementRequestValidatorService;
  private GamesResponse gamesResponse;

  @BeforeEach
  void setUp() {
    gamesResponse = gameCreationService.create();

  }

  @Test
  void validateAndInitializeMoveRequest() {
    assertThrows(
        InvalidPitIdException.class,
        () -> gameMovementRequestValidatorService.validateAndInitializeMoveRequest(Long.valueOf(gamesResponse.getId()), 14));
    assertThrows(
        InvalidPitIdException.class,
        () -> gameMovementRequestValidatorService.validateAndInitializeMoveRequest(Long.valueOf(gamesResponse.getId()), 7));
    assertThrows(
        InvalidPitIdException.class,
        () -> gameMovementRequestValidatorService.validateAndInitializeMoveRequest(Long.valueOf(gamesResponse.getId()), 15));
    assertThrows(
        InvalidPitIdException.class,
        () -> gameMovementRequestValidatorService.validateAndInitializeMoveRequest(Long.valueOf(gamesResponse.getId()), 10));
    assertThrows(
        InvalidGameIdException.class,
        () -> gameMovementRequestValidatorService.validateAndInitializeMoveRequest(2L, 5));
  }
}