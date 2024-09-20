package com.bezkoder.spring.mssql.repository;

import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialRanking;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TutorialRankingRepository extends JpaRepository<TutorialRanking, Long> {

    Optional<List<TutorialRanking>> findAllByTutorialId(long tutorialId);
}

