package com.bezkoder.spring.mssql.repository;

import com.bezkoder.spring.mssql.model.TutorialRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TutorialRankingRepository extends JpaRepository<TutorialRanking, Long> {
}
