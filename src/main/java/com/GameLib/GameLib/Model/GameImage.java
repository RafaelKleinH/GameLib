package com.GameLib.GameLib.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "GameImages")
public class GameImage {
    @Id
    private Integer gameAppId;
    private String filename;
    private String contentType;
    private String gridFsFileId;
    private Long fileSize;
    private String imageType;
    private LocalDateTime uploadDate;

    public GameImage() {
        this.uploadDate = LocalDateTime.now();
    }

    public GameImage(Integer gameAppId, String filename, String contentType, String gridFsFileId, Long fileSize, String imageType) {
        this();
        this.gameAppId = gameAppId;
        this.filename = filename;
        this.contentType = contentType;
        this.gridFsFileId = gridFsFileId;
        this.fileSize = fileSize;
        this.imageType = imageType;
    }

    public Integer getGameAppId() {
        return gameAppId;
    }

    public void setGameAppId(Integer gameAppId) {
        this.gameAppId = gameAppId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getGridFsFileId() {
        return gridFsFileId;
    }

    public void setGridFsFileId(String gridFsFileId) {
        this.gridFsFileId = gridFsFileId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
