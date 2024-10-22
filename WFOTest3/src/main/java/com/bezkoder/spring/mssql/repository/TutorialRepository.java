package com.bezkoder.spring.mssql.repository;

import java.util.List;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  @Query(value=
           "SELECT T.*, null"
                  + "   FROM tutorials T"
                  + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",
          nativeQuery=true)
  List<TutorialWithAvg> findByTitle(String title);



//  "SELECT T.*, TR.average_score"
//          + "   FROM tutorials T " +
//          "LEFT JOIN test_view TR " +
//          "ON T.id = TR.tutorial_id "
//          + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",


// "SELECT T.*, (SELECT AVG(TR.score) " +
//         "                      FROM tutorial_rankings TR " +
//         " WHERE TR.tutorial_id = T.id " +
//         "                  GROUP BY TR.tutorial_id) AS average_score"
//         + "   FROM tutorials T "
//         + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",


//  "WITH TempTR as ("
//          + "SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score " +
//          "                   FROM tutorial_rankings TR " +
//          "      GROUP BY TR.tutorial_id " +
//          ")"
//          + "SELECT T.*, TR.average_score"
//          + "   FROM tutorials T"
//          + "   LEFT JOIN TempTR TR"
//          + "   ON T.id = TR.tutorial_id"
//          + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",

//   + "SELECT T.*, TR.average_score"
//           + "   FROM tutorials T"
//           + "   LEFT JOIN (SELECT TR.tutorial_id as tutorial_id, AVG(TR.score) as average_score "
//           + "              FROM tutorial_rankings TR "
//           + "              GROUP BY TR.tutorial_id) AS TR "
//           + "   ON T.id = TR.tutorial_id"
//           + "   WHERE (COALESCE(:title, NULL) IS NULL OR T.title = :title)",
//  nativeQuery=true)
}