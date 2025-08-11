package com.GameLib.GameLib.Service;

import com.GameLib.GameLib.Model.GameModel;
import com.GameLib.GameLib.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameModel> getAllGames() {
        return gameRepository.findAll();
    };

    public Optional<GameModel> getGameByAppId(Integer appId) {
        return gameRepository.findByAppid(appId);
    };

    public GameModel insertGame(GameModel model) {
        return gameRepository.insert(model);
    }
}

