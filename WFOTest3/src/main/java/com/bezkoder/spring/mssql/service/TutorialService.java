package com.bezkoder.spring.mssql.service;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "tutorials", key = "#id")
    public List<TutorialWithAvg> fetchByTitle(String title) {
        List<TutorialWithAvg> redisTutorials = (List<TutorialWithAvg>) redisTemplate.opsForValue().get("tutorials");
        if (redisTutorials == null) {
            System.out.println("redisTutorials is empty");
            System.out.println("Calling to DB");
            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));
            return tutorials;
        }
        System.out.println("RedisResponse "+redisTutorials );
        return redisTutorials;
    }

    @Cacheable(value = "tutorialsfindbyId", key = "#id")
    public Tutorial findById(Long id) {
        Optional<Tutorial> existingTutorial = tutorialRepository.findById(id);
        if (existingTutorial.isPresent()) {
            return existingTutorial.get();
        }
        return null;
    }

    @CachePut(value = "tutorialsfindbyId", key = "#id")
    public TutorialWithAvg updateTutorial(TutorialWithAvg tutorialWithAvg) {
            tutorialRepository.save(tutorialWithAvg);
            return tutorialWithAvg;
    }

}
