package com.GameLib.GameLib.Controller;

import com.GameLib.GameLib.Model.GameImage;
import com.GameLib.GameLib.Service.GameImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import static java.lang.System.Logger.Level.DEBUG;

@RestController
@RequestMapping("/api/images")
public class GameImageController {

    @Autowired
    private GameImageService gameImageService;

    @GetMapping("/game/{gameAppId}")
    public ResponseEntity<byte[]> getImageByGame(@PathVariable Integer gameAppId) {
        try {
            GameImage gameImage = gameImageService.getImageByGameAppId(gameAppId);
            
            if (gameImage == null) {
                return ResponseEntity.notFound().build();
            }
            
            InputStream imageStream = gameImageService.getImageStream(gameImage.getGridFsFileId());
            
            if (imageStream == null) {
                return ResponseEntity.notFound().build();
            }
            
            byte[] imageBytes = imageStream.readAllBytes();
            imageStream.close();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(gameImage.getContentType()));
            headers.setContentLength(imageBytes.length);
            
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}