package com.backbase.kalah.kalahassignment.controller;

import com.backbase.kalah.kalahassignment.controller.dto.GameStatus;
import com.backbase.kalah.kalahassignment.controller.dto.GamesResponse;
import com.backbase.kalah.kalahassignment.service.GameAdapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.*;

@Api(tags = "Game Controller")
@RestController
@RequestMapping("/games")
public class KalahController {
    private final GameAdapterService gameAdapterService;

    @Autowired
    public KalahController(GameAdapterService gameAdapterService) {
        this.gameAdapterService = gameAdapterService;
    }

    @ApiOperation(value = "Create new Game")
    @ApiResponses(value = {@ApiResponse(code = SC_CREATED, message = "Game Id and Game URL"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GamesResponse games() {
        return gameAdapterService.createGame();
    }

    @ApiOperation(value = "Move a pit")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "Return game status after the game is created."),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    @PutMapping("/{gameId}/pits/{pitId}")
    public GameStatus makeMove(@PathVariable Long gameId,
                               @PathVariable Integer pitId) throws Throwable {

        return gameAdapterService.move(gameId, pitId);
    }

}
