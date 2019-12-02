package com.backbase.kalah.kalahassignment.service;

import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModel;
import com.backbase.kalah.kalahassignment.persistance.model.GameStatusModelBuilder;
import com.backbase.kalah.kalahassignment.persistance.model.Player;
import com.backbase.kalah.kalahassignment.persistance.repository.GameRepository;
import com.backbase.kalah.kalahassignment.util.KalahUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameCreationService {

  @Value("${stone.count:6}")
  private int stoneCount;

  private final GameRepository gameRepository;

  @Autowired
  public GameCreationService(final GameRepository gameRepository) {this.gameRepository = gameRepository;}


  public GamesResponse create() {
    GameEntity gameEntity = new GameEntity();
    gameEntity.setFinished(false);
    gameEntity.setPlayer(Player.FIRST);
    gameEntity.setGameStatusModels(generateDefaultGameStatus(gameEntity));
    gameRepository.save(gameEntity);
    return new GamesResponse(String.valueOf(gameEntity.getGameId()), KalahUtil.getGameUrl(gameEntity.getGameId()));
  }

  private List<GameStatusModel> generateDefaultGameStatus(final GameEntity gameEntity) {
    List<GameStatusModel> gameStatusModels = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      gameStatusModels.add(GameStatusModelBuilder.aGameStatus()
                                                 .withGameEntity(gameEntity)
                                                 .withStoneCount(stoneCount)
                                                 .withKalah(((i + 1) % 7) == 0)
                                                 .withPitId(i)
                                                 .build());
    }
    return gameStatusModels;
  }
}
