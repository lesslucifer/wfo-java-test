package com.bezkoder.spring.mssql.svc;

import com.bezkoder.spring.mssql.dto.TutorialResponse;
import com.bezkoder.spring.mssql.model.TutorialWithAvg;
import com.bezkoder.spring.mssql.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialSvcImpl implements TutorialSvc {

    @Autowired
    private TutorialRepository repository;

    @Override
    public List<TutorialWithAvg> findByTitle(String title) {
        List<Object[]> tutorials = repository.findByTitle(title);
        return tutorials.stream().map(o -> new TutorialWithAvg((long) o[0], (Double) o[1]))
                .collect(Collectors.toList());
    }
}
