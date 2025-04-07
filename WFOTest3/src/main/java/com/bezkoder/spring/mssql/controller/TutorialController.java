package com.bezkoder.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.mssql.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	TutorialService tutorialService;

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
		try {


//            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));
			List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialService.fetchByTitle(title));
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials.stream()
												 .map(TutorialResponse::from)
												 .collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<TutorialWithAvg> fetchById(@PathVariable Long id, @RequestBody TutorialWithAvg tutorialWithAvg ) {
		try {
			Tutorial tutorials = tutorialService.findById(id);
			if (tutorials==null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			tutorialService.updateTutorial(tutorialWithAvg);
			return new ResponseEntity<>(tutorialWithAvg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
