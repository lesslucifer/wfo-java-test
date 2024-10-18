package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  // cast avg score from bigInteger to double
  @Query(value="SELECT T.id, COALESCE(AVG(TR.score), 0) as average_score"
          + " FROM tutorials T"
          + " LEFT JOIN tutorial_rankings TR ON T.id = TR.tutorial_id "
          + " WHERE (:title IS NULL OR T.title = :title)"
          + " GROUP BY T.id",
          nativeQuery=true)
  List<Object[]> findByTitle(String title);
}