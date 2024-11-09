package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.QuizObject;
import edu.ICET.dto.QuizQuestions;
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
        QuizObject quizObject;
        if (quizQuestions.getOptionSet() != null) {
            if (quizOptionsService.saveAll(quizQuestions.getOptionSet())) {
                quizQuestionsDao.save(objectMapper.convertValue(quizQuestions, QuizQuestionsEntity.class));
                return true;
            }
        } else {
            quizQuestionsDao.save(objectMapper.convertValue(quizQuestions, QuizQuestionsEntity.class));
            return true;
        }
        return false;
    }

    @Override
    public QuizQuestions search(Long id) {
        return objectMapper.convertValue(quizQuestionsDao.findById(id),QuizQuestions.class);
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
            quizQuestionsList.add(objectMapper.convertValue(quizQuestionsEntity, QuizQuestions.class));
        });
        return quizQuestionsList;
    }

    @Override
    public void saveAll(List<QuizQuestions> quizQuestionsList) {
        quizQuestionsList.forEach(this::save);
    }

    @Override
    public List<QuizObject> getAllObjects() {
        List<QuizObject> objectList = new ArrayList<>();
        for (QuizQuestions quizQuestions : getAll()) {
            objectList.add(
                    new QuizObject(
                            quizQuestions.getId(),
                            quizQuestions,
                            quizOptionsService.searchAllByQuestionId(quizQuestions.getId())
                    ));
        }
        return objectList;
    }

    @Override
    public List<QuizObject> getByObjectType(String type) {
        List<QuizObject> objectList = new ArrayList<>();
        quizQuestionsDao.findByQuestionTypeEnum(type).forEach(quizQuestionsEntity -> {
            objectList.add(new QuizObject(
                    quizQuestionsEntity.getId(),
                    objectMapper.convertValue(quizQuestionsEntity,QuizQuestions.class),
                    quizOptionsService.searchAllByQuestionId(quizQuestionsEntity.getId())
            ));
        });
        return objectList;
    }
}
