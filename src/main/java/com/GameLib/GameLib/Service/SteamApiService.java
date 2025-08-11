package com.GameLib.GameLib.Service;

import com.GameLib.GameLib.Model.SteamApiResponse;
import com.GameLib.GameLib.Model.SteamGameResponse;
import com.GameLib.GameLib.Model.GameModel;
import com.GameLib.GameLib.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SteamApiService {
    
    private static final String STEAM_API_BASE_URL = "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/";
    
    @Value("${steam.api.key}")
    private String steamApiKey;
    
    @Autowired
    private GameRepository gameRepository;
    
    private final RestTemplate restTemplate;
    
    public SteamApiService() {
        this.restTemplate = new RestTemplate();
    }
    
    public List<GameModel> fetchAndSaveOwnedGames() {
        SteamApiResponse response = fetchOwnedGamesFromSteam();
        
        if (response == null || response.getResponse() == null || response.getResponse().getGames() == null) {
            throw new RuntimeException("Failed to fetch games from Steam API or no games found");
        }
        
        List<GameModel> gameModels = response.getResponse().getGames().stream()
                .map(this::convertToGameModelWithExistingData)
                .collect(Collectors.toList());
        
        return gameRepository.saveAll(gameModels);
    }
    
    private SteamApiResponse fetchOwnedGamesFromSteam() {
        String url = UriComponentsBuilder.fromHttpUrl(STEAM_API_BASE_URL)
                .queryParam("key", steamApiKey)
                .queryParam("steamid", "76561198374492833" ) //change to be dynamic
                .queryParam("format", "json")
                .queryParam("include_appinfo", "true")
                .queryParam("include_played_free_games", "true")
                .build()
                .toUriString();
        
        try {
            return restTemplate.getForObject(url, SteamApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error calling Steam API: " + e.getMessage(), e);
        }
    }
    
    private GameModel convertToGameModelWithExistingData(SteamGameResponse steamGame) {
        Optional<GameModel> existingGame = gameRepository.findByAppid(steamGame.getAppid());
        GameModel gameModel;
        
        if (existingGame.isPresent()) {
            gameModel = existingGame.get();
        } else {
            gameModel = new GameModel();
        }
        
        gameModel.setAppid(steamGame.getAppid());
        gameModel.setName(steamGame.getName());
        gameModel.setImg_icon_url(steamGame.getImgIconUrl());
        gameModel.setPlaytime_forever(steamGame.getPlaytimeForever());
        gameModel.setPlaytime_windows_forever(steamGame.getPlaytimeWindowsForever());
        gameModel.setPlaytime_mac_forever(steamGame.getPlaytimeMacForever());
        gameModel.setPlaytime_linux_forever(steamGame.getPlaytimeLinuxForever());
        gameModel.setPlaytime_deck_forever(steamGame.getPlaytimeDeckForever());
        gameModel.setRtime_last_played(steamGame.getRtimeLastPlayed());
        
        return gameModel;
    }
}