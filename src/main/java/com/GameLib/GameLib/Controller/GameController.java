package com.GameLib.GameLib.Controller;

import com.GameLib.GameLib.Model.GameModel;
import com.GameLib.GameLib.Model.SteamApiResponse;
import com.GameLib.GameLib.Service.GameService;
import com.GameLib.GameLib.Service.SteamApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private SteamApiService steamApiService;

    @GetMapping
    public List<GameModel> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Optional<GameModel> getUserById(@PathVariable Integer appIcon) {
        return gameService.getGameByAppId(appIcon);
    }

    @PostMapping
    public GameModel postGame(@RequestBody GameModel gameModel) {
        return gameService.insertGame(gameModel);
    }
    
    @GetMapping("/sync")
    public ResponseEntity<List<GameModel>> syncGamesFromSteam() {
        try {
            List<GameModel> syncedGames = steamApiService.fetchAndSaveOwnedGames();
            return ResponseEntity.ok(syncedGames);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}