package com.GameLib.GameLib.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "Games")
public class GameModel {
    // Steam defaults
    @Id
    private Integer appid;
    private String name;
    private String img_icon_url;
    private Integer playtime_forever;
    private Integer playtime_windows_forever;
    private Integer playtime_mac_forever;
    private Integer playtime_linux_forever;
    private Integer playtime_deck_forever;
    private Integer rtime_last_played;

    // Created
    private String description;
    private BigDecimal averageRating;
    private BigDecimal userRating;

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

    public String getImg_icon_url() {
        return img_icon_url;
    }

    public void setImg_icon_url(String img_icon_url) {
        this.img_icon_url = img_icon_url;
    }

    public Integer getPlaytime_forever() {
        return playtime_forever;
    }

    public void setPlaytime_forever(Integer playtime_forever) {
        this.playtime_forever = playtime_forever;
    }

    public Integer getPlaytime_windows_forever() {
        return playtime_windows_forever;
    }

    public void setPlaytime_windows_forever(Integer playtime_windows_forever) {
        this.playtime_windows_forever = playtime_windows_forever;
    }

    public Integer getPlaytime_mac_forever() {
        return playtime_mac_forever;
    }

    public void setPlaytime_mac_forever(Integer playtime_mac_forever) {
        this.playtime_mac_forever = playtime_mac_forever;
    }

    public Integer getPlaytime_linux_forever() {
        return playtime_linux_forever;
    }

    public void setPlaytime_linux_forever(Integer playtime_linux_forever) {
        this.playtime_linux_forever = playtime_linux_forever;
    }

    public Integer getPlaytime_deck_forever() {
        return playtime_deck_forever;
    }

    public void setPlaytime_deck_forever(Integer playtime_deck_forever) {
        this.playtime_deck_forever = playtime_deck_forever;
    }

    public Integer getRtime_last_played() {
        return rtime_last_played;
    }

    public void setRtime_last_played(Integer rtime_last_played) {
        this.rtime_last_played = rtime_last_played;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public BigDecimal getUserRating() {
        return userRating;
    }

    public void setUserRating(BigDecimal userRating) {
        this.userRating = userRating;
    }
}