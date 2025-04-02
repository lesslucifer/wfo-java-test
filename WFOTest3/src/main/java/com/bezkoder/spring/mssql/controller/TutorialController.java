package com.bezkoder.spring.mssql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.Paginator;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialService tutorialService;

	@GetMapping("/v2/tutorials")
	public ResponseEntity<Object> getPaginatedTutorials(
			@RequestParam(required = false) String title,
			Pageable pageable
	) {


		try {
			Page<TutorialWithAvg> page = tutorialService.findByTitle(title, pageable);
			List<TutorialWithAvg> tutorials = page.getContent();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<TutorialResponse> content = tutorials.stream()
					.map(TutorialResponse::from)
					.collect(Collectors.toList());
			return new ResponseEntity<>(new Paginator(pageable.getPageNumber(), page.getSize(), page.getTotalElements(), content), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
		try {

            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialService.findByTitle(title));

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
}
