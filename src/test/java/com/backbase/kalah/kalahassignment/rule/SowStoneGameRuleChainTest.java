package com.backbase.kalah.kalahassignment.rule;

import com.backbase.kalah.kalahassignment.controller.dto.UserRequestSessionData;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.Player;
import com.backbase.kalah.kalahassignment.service.GameCreationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest
class SowStoneGameRuleChainTest {

  @Autowired
  private GameCreationService gameCreationService;
  private Map<String, String> response;
  private GameEntity gameEntity;

  @BeforeEach
  void setUp() {
    response = new HashMap<>();
    final List<String> strings = Arrays.asList(
        "6",
        "6",
        "6",
        "0",
        "7",
        "7",
        "1",
        "7",
        "7",
        "7",
        "6",
        "6",
        "6",
        "0");
    for (int i = 0; i < strings.size(); i++) {
      response.put(String.valueOf(i + 1), strings.get(i));
    }
    gameEntity = gameCreationService.generateDefaultGameEntity();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void applyRule() {
    UserRequestSessionData userRequestSessionData = applyMovement(new SowStoneGameRuleChain(null),4);
    userRequestSessionData.getFinalGameStatus().getStatus()
                          .forEach((key, value) -> {
                            Assertions.assertEquals(value, response.get(key));
                          });
  }

  private UserRequestSessionData applyMovement(final GameRuleChain gameRuleChain,final int pitId) {
    UserRequestSessionData userRequestSessionData = new UserRequestSessionData(gameEntity, pitId, Player.FIRST);

    gameRuleChain.applyRule(userRequestSessionData);
    return userRequestSessionData;
  }
}