package com.bezkoder.spring.mssql.service;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.repository.TutorialRepository;
import com.bezkoder.spring.mssql.cache.SimpleCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {
    public static final int DEFAULT_TTL = 60;
    private TutorialRepository tutorialRepository;
    private SimpleCache simpleCache = new SimpleCache();

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public List<TutorialWithAvg> findByTitle(String title) {
        return this.findByTitle(title, DEFAULT_TTL);
    }

    public List<TutorialWithAvg> findByTitle(String title, int ttl) {
        List<TutorialWithAvg> cacheHit = simpleCache.get(title, List.class);
        if (cacheHit != null) {
            return cacheHit;
        }
        List<TutorialWithAvg> titles = tutorialRepository.findByTitle(title);
        simpleCache.put(title, titles, ttl);
        return titles;
    }

    public Page<TutorialWithAvg> findByTitle(String title, Pageable pageable) {
        Page<TutorialWithAvg> page = tutorialRepository.findByTitle(title, pageable);
        return page;
    }


}
