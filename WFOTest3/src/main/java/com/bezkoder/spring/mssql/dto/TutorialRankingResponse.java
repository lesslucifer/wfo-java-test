package com.bezkoder.spring.mssql.dto;

import com.bezkoder.spring.mssql.model.TutorialRanking;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialRankingResponse {
  private long id;
  private long tutorialId;
  private long score;
  private String description;

  public static TutorialRankingResponse from(TutorialRanking tutorialRanking) {
    return TutorialRankingResponse.builder()
                                  .id(tutorialRanking.getId())
                                  .score(tutorialRanking.getScore())
                                  .description(tutorialRanking.getDescription())
                                  .build();
  }
}
