package com.bezkoder.spring.mssql.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialRanking;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.mssql.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	TutorialRepository tutorialRepository;

//	@GetMapping("/tutorials")
//	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
//		try {
//
//            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(tutorials.stream()
//												 .map(TutorialResponse::from)
//												 .collect(Collectors.toList()), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = tutorialRepository.findBy();
//			List<TutorialWithAvg> tutorials = tutorialRepository.findByTitle(title);

			List<TutorialResponse> response = tutorials.stream()
					.map(tutorial -> {
						double averageScore = tutorial.getTutorialRankings().stream()
								.mapToDouble(TutorialRanking::getScore)
								.average()
								.orElse(0.0);

						// Tạo đối tượng TutorialResponse
						return TutorialResponse.builder()
								.id(tutorial.getId())
								.title(tutorial.getTitle())
								.description(tutorial.getDescription())
								.published(tutorial.isPublished())
								.averageScore(averageScore) // Gán averageScore
								.build();
					})
					.collect(Collectors.toList());

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
