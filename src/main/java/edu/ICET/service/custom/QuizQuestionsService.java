package edu.ICET.service.custom;

import edu.ICET.dto.QuizObject;
import edu.ICET.dto.QuizQuestions;
import edu.ICET.dto.User;
import edu.ICET.service.SuperService;

import java.util.List;

public interface QuizQuestionsService extends SuperService<QuizQuestions> {
    void saveAll(List<QuizQuestions> quizQuestions);
    List<QuizObject> getAllObjects();
    List<QuizObject> getByObjectType(String type);
}
