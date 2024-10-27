package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietProgress;
import edu.ICET.dto.Login;
import edu.ICET.entity.DietProgressEntity;
import edu.ICET.entity.LoginEntity;
import edu.ICET.repository.DietProgressDao;
import edu.ICET.service.custom.DietProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietProgressServiceImpl implements DietProgressService {

    private final DietProgressDao dietProgressDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(DietProgress dietProgress) {
        dietProgressDao.save(objectMapper.convertValue(dietProgress, DietProgressEntity.class));
        return false;
    }

    @Override
    public DietProgress search(Long id) {
        return objectMapper.convertValue(dietProgressDao.findById(id), DietProgress.class);
    }

    @Override
    public boolean update(DietProgress dietProgress) {
        dietProgressDao.save(objectMapper.convertValue(dietProgress, DietProgressEntity.class));
        return false;
    }

    @Override
    public boolean delete(Long id) {
        dietProgressDao.deleteById(id);
        return false;
    }

    @Override
    public List<DietProgress> getAll() {
        List<DietProgress> dietProgressList = new ArrayList<>();
        dietProgressDao.findAll().forEach(dietProgress -> {
            dietProgressList.add(objectMapper.convertValue(dietProgress, DietProgress.class));
        });
        return dietProgressList;
    }
}
