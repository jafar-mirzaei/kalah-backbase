package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModelBuilder;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import com.backbase.kalah.kalahassignment.util.KalahUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameCreationService {

  @Value("${stone.cout:6}")
  private int stoneCount;

  @Autowired
  private GameRepository gameRepository;
  @Autowired
  private KalahUtil kalahUtil;

  public GamesResponse create() {
    final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    GameEntity gameEntity = new GameEntity();
    gameEntity.setTurnSessionId(sessionId);
    gameEntity.setFinished(false);
    //    gameRepository.save(gameEntity);
    gameEntity.setGameStatusModels(generateDefaultGameStatus(gameEntity));
    gameRepository.save(gameEntity);
    return new GamesResponse(String.valueOf(gameEntity.getGameId()), kalahUtil.getGameUrl(gameEntity.getGameId()));
  }

  private List<GameStatusModel> generateDefaultGameStatus(final GameEntity gameEntity) {
    List<GameStatusModel> gameStatusModels = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      final GameStatusModel gameStatusModel = new GameStatusModel();
      gameStatusModels.add(GameStatusModelBuilder.aGameStatus()
                                                 .withGameEntity(gameEntity)
                                                 .withStoneCount(stoneCount)
                                                 .withKalah((i % 7) == 0)
                                                 .withPitId(i)
                                                 .build());
    }
    return gameStatusModels;
  }
}
