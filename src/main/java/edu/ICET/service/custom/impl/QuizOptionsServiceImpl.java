package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.QuizOptions;
import edu.ICET.entity.QuizOptionsEntity;
import edu.ICET.repository.QuizOptionsDao;
import edu.ICET.service.custom.QuizOptionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizOptionsServiceImpl implements QuizOptionsService {
    private final QuizOptionsDao quizOptionsDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(QuizOptions quizOptions) {
        QuizOptionsEntity entity = objectMapper.convertValue(quizOptions, QuizOptionsEntity.class);
        quizOptionsDao.save(entity);
        return quizOptionsDao.existsById(quizOptions.getOption_id());
    }

    @Override
    public QuizOptions search(Long id) {
        return objectMapper.convertValue(quizOptionsDao.findById(id), QuizOptions.class);
    }

    @Override
    public boolean update(QuizOptions quizOptions) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<QuizOptions> getAll() {
        List<QuizOptions> quizOptionsList = new ArrayList<>();
        quizOptionsDao.findAll().forEach(quizOptionsEntity -> {
            quizOptionsList.add(objectMapper.convertValue(quizOptionsEntity, QuizOptions.class));
        });
        return quizOptionsList;
    }

    @Override
    public boolean saveAll(List<QuizOptions> quizOptionsList) {
        boolean bool = false;
        for (QuizOptions options : quizOptionsList) {
            if (save(options)) {
                bool = true;
            } else {
                return false;
            }
        }
        return bool;
    }

    @Override
    public List<QuizOptions> searchAllByQuestionId(Long id) {
        List<QuizOptions> quizOptionsList = new ArrayList<>();
        quizOptionsDao.findByQuestionId(id).forEach(quizOptionsEntity -> {
            quizOptionsList.add(objectMapper.convertValue(quizOptionsEntity, QuizOptions.class));
        });
        return quizOptionsList;
    }
}
