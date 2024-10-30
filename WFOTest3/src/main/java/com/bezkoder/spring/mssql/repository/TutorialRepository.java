package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
//  @Query(value="SELECT T.*, TR.average_score"
//          + "   FROM tutorials T"
//          + "   LEFT JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
//          + "              FROM tutorial_rankings TR "
//          + "              GROUP BY TR.tutorial_id) AS TR "
//          + "   ON T.id = TR.tutorial_id"
//          + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",
//          nativeQuery=true)
//  List<TutorialWithAvg> findByTitle(String title);


  @Query(value = "SELECT T.id, T.title, T.description, T.published, TR.score " +
          "FROM tutorials T " +
          "LEFT JOIN tutorial_rankings TR ON T.id = TR.tutorial_id " +
          "WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",
          nativeQuery = true)
  List<TutorialWithAvg> findByTitle(@Param("title") String title);

  @Query(value = "SELECT T.id, T.title, T.description, T.published, TR.score " +
          "FROM tutorials T " +
          "LEFT JOIN tutorial_rankings TR ON T.id = TR.tutorial_id ",
          nativeQuery = true)
  List<Tutorial> findBy();
}