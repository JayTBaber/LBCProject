package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.service.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) { this.gameRepository = gameRepository; }

    public Game findById(String id) {
        Game gameFromBackend = gameRepository
                .findById(id)
                .map(game -> new Game(game.getGameId(), game.getGameTitle(), game.getGenre(),
                        game.getWeightOfGame(), game.getConditionOfGame(), game.getMaturityLevel(),
                        game.getNumberOfPlayers(), game.getPlaytimeInMinutes()))
                .orElse(null);
        return gameFromBackend;
    }

    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        gameRepository
                .findAll()
                .forEach(game -> games.add(new Game(game.getGameId(), game.getGameTitle(), game.getGenre(), game.getWeightOfGame(),
                        game.getConditionOfGame(), game.getMaturityLevel(), game.getNumberOfPlayers(), game.getPlaytimeInMinutes())));
        return games;
    }

    public Game addNewGame(Game game) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setGameId(game.getGameId());
        gameRecord.setGameTitle(game.getGameTitle());
        gameRecord.setGenre(game.getGenre());
        gameRecord.setWeightOfGame(game.getWeightOfGame());
        gameRecord.setConditionOfGame(game.getConditionOfGame());
        gameRecord.setMaturityLevel(game.getMaturityLevel());
        gameRecord.setNumberOfPlayers(game.getNumberOfPlayers());
        gameRecord.setPlaytimeInMinutes(game.getPlaytimeInMinutes());
        gameRepository.save(gameRecord);
        return game;
    }
}
