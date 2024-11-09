package edu.ICET.service.custom;

import edu.ICET.dto.QuizOptions;
import edu.ICET.entity.QuizOptionsEntity;
import edu.ICET.service.SuperService;

import java.util.List;
import java.util.Set;

public interface QuizOptionsService extends SuperService<QuizOptions> {
    boolean saveAll(List<QuizOptions> quizOptions);
    List<QuizOptions> searchAllByQuestionId(Long id);
}
