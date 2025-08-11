package com.GameLib.GameLib.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SteamGameResponse {
    @JsonProperty("appid")
    private Integer appid;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("img_icon_url")
    private String imgIconUrl;
    
    @JsonProperty("playtime_forever")
    private Integer playtimeForever;
    
    @JsonProperty("playtime_windows_forever")
    private Integer playtimeWindowsForever;
    
    @JsonProperty("playtime_mac_forever")
    private Integer playtimeMacForever;
    
    @JsonProperty("playtime_linux_forever")
    private Integer playtimeLinuxForever;
    
    @JsonProperty("playtime_deck_forever")
    private Integer playtimeDeckForever;
    
    @JsonProperty("rtime_last_played")
    private Integer rtimeLastPlayed;

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgIconUrl() {
        return imgIconUrl;
    }

    public void setImgIconUrl(String imgIconUrl) {
        this.imgIconUrl = imgIconUrl;
    }

    public Integer getPlaytimeForever() {
        return playtimeForever;
    }

    public void setPlaytimeForever(Integer playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

    public Integer getPlaytimeWindowsForever() {
        return playtimeWindowsForever;
    }

    public void setPlaytimeWindowsForever(Integer playtimeWindowsForever) {
        this.playtimeWindowsForever = playtimeWindowsForever;
    }

    public Integer getPlaytimeMacForever() {
        return playtimeMacForever;
    }

    public void setPlaytimeMacForever(Integer playtimeMacForever) {
        this.playtimeMacForever = playtimeMacForever;
    }

    public Integer getPlaytimeLinuxForever() {
        return playtimeLinuxForever;
    }

    public void setPlaytimeLinuxForever(Integer playtimeLinuxForever) {
        this.playtimeLinuxForever = playtimeLinuxForever;
    }

    public Integer getPlaytimeDeckForever() {
        return playtimeDeckForever;
    }

    public void setPlaytimeDeckForever(Integer playtimeDeckForever) {
        this.playtimeDeckForever = playtimeDeckForever;
    }

    public Integer getRtimeLastPlayed() {
        return rtimeLastPlayed;
    }

    public void setRtimeLastPlayed(Integer rtimeLastPlayed) {
        this.rtimeLastPlayed = rtimeLastPlayed;
    }
}