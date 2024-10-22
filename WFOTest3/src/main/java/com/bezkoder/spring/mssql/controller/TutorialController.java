package com.bezkoder.spring.mssql.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
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
            List<TutorialWithAvg> tutorials = new ArrayList<>(tutorialRepository.findByTitle(title));

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            long start_time = System.currentTimeMillis();
            List<TutorialResponse> result = customThreadPool.submit(() -> tutorials.parallelStream()
                    .map(TutorialResponse::from)
                    .collect(Collectors.toList())).get();
            long end_time = System.currentTimeMillis();
            System.out.println("Convert to ResponseDTO time: " + TimeUnit.MILLISECONDS.toSeconds((end_time - start_time)));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
