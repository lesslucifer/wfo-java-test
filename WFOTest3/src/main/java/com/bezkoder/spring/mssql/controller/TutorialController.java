package com.bezkoder.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.repository.TutorialRankingRepository;
import lombok.var;
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

	@Autowired
	TutorialRankingRepository tutorialRankingRepository;

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			var listTutorialRanking = tutorialRankingRepository.findAll();
			return new ResponseEntity<>(tutorials.stream()
												 .map(tutorialWithAvg ->
														 TutorialResponse
														 .from(tutorialWithAvg, listTutorialRanking.stream()
														 .filter(el -> el.getTutorialId() == tutorialWithAvg.getId())
																 .collect(Collectors.toList())))
												 .collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
