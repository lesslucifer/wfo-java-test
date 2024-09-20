package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  @Query(value="SELECT T.id, T.title, T.description, T.published, R.average_score as average_score"
          + "   FROM tutorials T"
          + "   LEFT JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
          + "              FROM tutorial_rankings TR "
          + "               WHERE TR.score IS NOT NULL "
          + "              GROUP BY TR.tutorial_id) AS R "
          + "   ON T.id = R.tutorial_id"
          + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",
          nativeQuery=true)
  List<TutorialWithAvg> findByTitleAverageScorce(String title);
}