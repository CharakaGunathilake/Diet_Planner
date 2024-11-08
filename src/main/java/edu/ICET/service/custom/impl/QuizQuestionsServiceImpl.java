package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.QuizQuestions;
import edu.ICET.entity.QuizOptionsEntity;
import edu.ICET.entity.QuizQuestionsEntity;
import edu.ICET.repository.QuizQuestionsDao;
import edu.ICET.service.custom.QuizOptionsService;
import edu.ICET.service.custom.QuizQuestionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizQuestionsServiceImpl implements QuizQuestionsService {
    private final QuizQuestionsDao quizQuestionsDao;
    private final QuizOptionsService quizOptionsService;
    private final ObjectMapper objectMapper;
    @Override
    public boolean save(QuizQuestions quizQuestions) {
        if(quizOptionsService.saveAll(quizQuestions.getOptionSet())) {
            quizQuestionsDao.save(objectMapper.convertValue(quizQuestions, QuizQuestionsEntity.class));
            return true;
        }
        return false;
    }

    @Override
    public QuizQuestions search(Long id) {
        return null;
    }

    @Override
    public boolean update(QuizQuestions quizQuestions) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<QuizQuestions> getAll() {
        List<QuizQuestions> quizQuestionsList = new ArrayList<>();
        quizQuestionsDao.findAll().forEach(quizQuestionsEntity -> {
            QuizQuestions quizObject = objectMapper.convertValue(quizQuestionsEntity,QuizQuestions.class);
            quizObject.setOptionSet(quizOptionsService.getAll());
            quizQuestionsList.add(quizObject);
        });
        return quizQuestionsList;
    }

    @Override
    public void saveAll(List<QuizQuestions> quizQuestions) {
        quizQuestions.forEach(this::save);
    }
}
