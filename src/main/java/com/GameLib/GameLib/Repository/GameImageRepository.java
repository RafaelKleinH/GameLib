package com.GameLib.GameLib.Repository;

import com.GameLib.GameLib.Model.GameImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameImageRepository extends MongoRepository<GameImage, String> {
    List<GameImage> findByGameAppId(Integer gameAppId);
    List<GameImage> findByGameAppIdAndImageType(Integer gameAppId, String imageType);
    Optional<GameImage> findByGridFsFileId(String gridFsFileId);
}