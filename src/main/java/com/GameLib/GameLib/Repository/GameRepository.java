package com.GameLib.GameLib.Repository;

import com.GameLib.GameLib.Model.GameModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<GameModel, Integer> {
    
    Optional<GameModel> findByAppid(Integer appid);


    @Query("{ 'rtime_last_played': { $gt: ?0 } }")
    List<GameModel> findRecentGames(Integer minTime, Pageable pageable);

}

