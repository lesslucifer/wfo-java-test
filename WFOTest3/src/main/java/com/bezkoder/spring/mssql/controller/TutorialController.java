package com.bezkoder.spring.mssql.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
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

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
		try {

			Instant start = Instant.now();
            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			Instant end = Instant.now();
			long duration = java.time.Duration.between(start, end).toMillis();
			System.out.println("Execution time for finding tutorials: " + duration + " milliseconds");

			Instant mapStart = Instant.now();

			List<TutorialResponse> tutorialResponses = tutorials.stream()
					.map(TutorialResponse::from)
					.collect(Collectors.toList());
//			List<TutorialResponse> tutorialResponses = tutorials.parallelStream()
//					.map(TutorialResponse::from)
//					.collect(Collectors.toList());

			// Log the time taken for mapping and collecting
			Instant mapEnd = Instant.now();
			long mapDuration = java.time.Duration.between(mapStart, mapEnd).toMillis();
			System.out.println("Execution time for mapping and collecting tutorial responses: " + mapDuration + " milliseconds");

			// Return the response
			return new ResponseEntity<>(tutorialResponses, HttpStatus.OK);

//			return new ResponseEntity<>(tutorials.stream()
//												 .map(TutorialResponse::from)
//												 .collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
