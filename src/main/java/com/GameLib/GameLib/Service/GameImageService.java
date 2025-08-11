package com.GameLib.GameLib.Service;

import com.GameLib.GameLib.Model.GameImage;
import com.GameLib.GameLib.Repository.GameImageRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GameImageService {

    @Autowired
    private GameImageRepository gameImageRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations gridFsOperations;

    private final RestTemplate restTemplate = new RestTemplate();

    public GameImage storeImage(Integer gameAppId, MultipartFile file, String imageType) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        
        InputStream inputStream = file.getInputStream();
        
        String gridFsFileId = gridFsTemplate.store(inputStream, filename, contentType).toString();
        
        GameImage gameImage = new GameImage(
            gameAppId, 
            filename, 
            contentType, 
            gridFsFileId, 
            file.getSize(), 
            imageType
        );
        
        return gameImageRepository.save(gameImage);
    }

    public GameImage getImageByGameAppId(Integer gameAppId) {
        List<GameImage> images = gameImageRepository.findByGameAppId(gameAppId);
        
        if (!images.isEmpty()) {
            return images.get(0);
        }
        
        return fetchAndStoreSteamImage(gameAppId);
    }

    public InputStream getImageStream(String gridFsFileId) {
        try {
            GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(gridFsFileId)));
            if (gridFSFile != null) {
                return gridFsOperations.getResource(gridFSFile).getInputStream();
            }
        } catch (IOException e) {
            // Handle error gracefully
        }
        return null;
    }

    private GameImage fetchAndStoreSteamImage(Integer gameAppId) {
        try {
            String steamImageUrl = "https://steamcdn-a.akamaihd.net/steam/apps/" + gameAppId + "/hero_capsule.jpg";
            
            byte[] imageBytes = restTemplate.getForObject(steamImageUrl, byte[].class);
            
            if (imageBytes != null && imageBytes.length > 0) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                String filename = "hero_capsule_" + gameAppId + ".jpg";
                String contentType = "image/jpeg";
                
                String gridFsFileId = gridFsTemplate.store(inputStream, filename, contentType).toString();
                
                GameImage gameImage = new GameImage(
                    gameAppId,
                    filename,
                    contentType,
                    gridFsFileId,
                    (long) imageBytes.length,
                    "hero_capsule"
                );
                
                return gameImageRepository.save(gameImage);
            }
        } catch (Exception e) {
            // Log error or handle gracefully - Steam image not available
        }
        
        return null;
    }

    public List<GameImage> getAllImages() {
        return gameImageRepository.findAll();
    }
}