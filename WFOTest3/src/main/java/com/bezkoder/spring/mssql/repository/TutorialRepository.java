package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  @Query(value="SELECT T.id, T.title, T.description, T.published, TR.average_score as average_score"
          + "   FROM tutorials T"
          + "   JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
          + "              FROM tutorial_rankings TR "
          + "              GROUP BY TR.tutorial_id) AS TR "
          + "   ON T.id = TR.tutorial_id"
          + "   WHERE (:title IS NULL OR T.title = :title)",
          nativeQuery=true)
  List<TutorialWithAvg> findByTitle(String title);

  @Query(value="SELECT T.id, T.title, T.description, T.published, TR.average_score as average_score"
          + "   FROM tutorials T"
          + "   JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
          + "              FROM tutorial_rankings TR "
          + "              GROUP BY TR.tutorial_id) AS TR "
          + "   ON T.id = TR.tutorial_id"
          + "   WHERE (:title IS NULL OR T.title = :title)",
          nativeQuery=true)
  Page<TutorialWithAvg> findByTitle(String title, Pageable pageable);
}