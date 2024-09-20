package com.bezkoder.spring.mssql.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutorials")
public class TutorialWithAvg extends Tutorial {

  @Column(name = "average_score")
  private Double averageScore;
}