package com.bezkoder.spring.mssql.dto;

import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialRanking;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorialResponse {
  private long id;
  private String title;
  private String description;
  private boolean published;
  private Set<TutorialRankingResponse> tutorialRankings = new HashSet<>();

  public static TutorialResponse from(Tutorial tutorial) {
    return TutorialResponse.builder()
                           .id(tutorial.getId())
                           .title(tutorial.getTitle())
                           .description(tutorial.getDescription())
                           .tutorialRankings(tutorial.getTutorialRankings()
                                                     .stream()
                                                     .map(TutorialRankingResponse::from)
                                                     .collect(Collectors.toSet()))
                           .build();
  }
}