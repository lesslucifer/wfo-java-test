package com.bezkoder.spring.mssql.svc;

import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TutorialSvc {
    List<TutorialWithAvg> findByTitle(String title);
}
