package com.bezkoder.spring.mssql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutorial_rankings")
public class TutorialRanking {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "tutorial_id")
  private long tutorialId;

  @Column(name = "scrore")
  private long scrore;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch=FetchType.LAZY)
  @LazyToOne(LazyToOneOption.NO_PROXY)
  @LazyGroup("tutorial")
  @JoinColumn(name="tutorial_id", referencedColumnName="id", insertable=false, updatable=false)
  private Tutorial tutorial;

  public TutorialRanking(long tutorialId, long scrore, String description) {
    this.tutorialId = tutorialId;
    this.scrore = scrore;
    this.description = description;
  }
}
