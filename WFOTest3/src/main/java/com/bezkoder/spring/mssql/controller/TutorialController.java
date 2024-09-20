package com.bezkoder.spring.mssql.controller;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
        try {

            List<TutorialWithAvg> tutorials = tutorialService.getAllTutorials(title);

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
