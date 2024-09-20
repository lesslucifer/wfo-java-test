package com.bezkoder.spring.mssql.service;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialRanking;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.repository.TutorialRankingRepository;
import com.bezkoder.spring.mssql.repository.TutorialRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    TutorialRankingRepository tutorialRankingRepository;

    @SneakyThrows
    public List<TutorialWithAvg> getAllTutorials(String title) {

        List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitleAverageScorce(title));

        if (CollectionUtils.isEmpty(tutorials)) {
            return Collections.emptyList();
        }

        return tutorials;

    }
}
