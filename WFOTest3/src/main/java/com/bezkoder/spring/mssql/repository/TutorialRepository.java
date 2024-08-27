package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  @Query(value="SELECT T.*, TR.average_score"
          + "   FROM tutorials T"
          + "   LEFT JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
          + "              FROM tutorial_rankings TR "
          + "              GROUP BY TR.tutorial_id) AS TR "
          + "   ON T.id = TR.tutorial_id",
          nativeQuery=true)
  List<TutorialWithAvg> findAllTutorial();

  @Query(value="SELECT T.*, TR.average_score"
          + "   FROM tutorials T"
          + "   LEFT JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
          + "              FROM tutorial_rankings TR "
          + "              GROUP BY TR.tutorial_id) AS TR "
          + "   ON T.id = TR.tutorial_id"
          + "   WHERE T.title = :title",
          nativeQuery=true)
  List<TutorialWithAvg> findByTitle(String title);
}