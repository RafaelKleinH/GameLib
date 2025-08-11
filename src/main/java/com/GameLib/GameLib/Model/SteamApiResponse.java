package com.GameLib.GameLib.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SteamApiResponse {
    @JsonProperty("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public static class Response {
        @JsonProperty("game_count")
        private Integer gameCount;
        
        @JsonProperty("games")
        private List<SteamGameResponse> games;

        public Integer getGameCount() {
            return gameCount;
        }

        public void setGameCount(Integer gameCount) {
            this.gameCount = gameCount;
        }

        public List<SteamGameResponse> getGames() {
            return games;
        }

        public void setGames(List<SteamGameResponse> games) {
            this.games = games;
        }
    }
}