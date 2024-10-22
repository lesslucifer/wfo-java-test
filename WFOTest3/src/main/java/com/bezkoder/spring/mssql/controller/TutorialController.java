package com.bezkoder.spring.mssql.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.bezkoder.spring.mssql.dto.TutorialRankingResponse;
import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.dto.TutorialTestResponse;
import com.bezkoder.spring.mssql.model.Tutorial;
import com.bezkoder.spring.mssql.model.TutorialRanking;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

			List<TutorialWithAvg> list = tutorialRepository.findByTitle(title);
            List<TutorialWithAvg> tutorial1 = list.subList(0,1000);
			List<TutorialWithAvg> tutorial2 = list.subList(1001, 2000);
			List<TutorialWithAvg> tutorial3 = list.subList(2001, 3000);
			List<TutorialWithAvg> tutorial4 = list.subList(4001, list.size());

			Map<Long, Set<TutorialRankingResponse>> rankingMap1 = new HashMap<>();
			Map<Long, Set<TutorialRankingResponse>> rankingMap2 = new HashMap<>();
			Map<Long, Set<TutorialRankingResponse>> rankingMap3 = new HashMap<>();
			Map<Long, Set<TutorialRankingResponse>> rankingMap4 = new HashMap<>();



			Runnable ranking1Run = () -> {
				for (TutorialWithAvg tutorial : tutorial1) {

						rankingMap1.put(tutorial.getId(), tutorial.getTutorialRankings().stream().map(TutorialRankingResponse::from).collect(Collectors.toSet()));


				}
			};

			Runnable ranking2Run = () -> {
				for (TutorialWithAvg tutorial : tutorial2) {

					rankingMap2.put(tutorial.getId(), tutorial.getTutorialRankings().stream().map(TutorialRankingResponse::from).collect(Collectors.toSet()));


				}
			};
			Runnable ranking3Run = () -> {
				for (TutorialWithAvg tutorial : tutorial3) {

					rankingMap3.put(tutorial.getId(), tutorial.getTutorialRankings().stream().map(TutorialRankingResponse::from).collect(Collectors.toSet()));


				}
			};
			Runnable ranking4Run = () -> {
				for (TutorialWithAvg tutorial : tutorial4) {

					rankingMap4.put(tutorial.getId(), tutorial.getTutorialRankings().stream().map(TutorialRankingResponse::from).collect(Collectors.toSet()));


				}
			};
			Thread ranking1Thread = new Thread(ranking1Run);
			ranking1Thread.start();
			Thread ranking2Thread = new Thread(ranking2Run);
			ranking2Thread.start();




			ranking1Thread.join();
			ranking2Thread.join();


			if (list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<TutorialResponse> response = list.parallelStream()
					.map(TutorialResponse::from)
					.collect(Collectors.toList());

			for (TutorialResponse response1 : response) {
				if (rankingMap1.get(response1.getId()) != null) {
					response1.setTutorialRankings(rankingMap1.get(response1.getId()));
				} else if (rankingMap2.get(response1.getId()) != null) {
					response1.setTutorialRankings(rankingMap2.get(response1.getId()));
				}
			}

			return new ResponseEntity<>(response, HttpStatus.OK);

			//			return new ResponseEntity<>(tutorials.stream()
//					.map(TutorialTestResponse::from)
//					.collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
