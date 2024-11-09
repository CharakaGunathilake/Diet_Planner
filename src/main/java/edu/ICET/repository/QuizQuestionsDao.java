package edu.ICET.repository;

import edu.ICET.entity.QuizQuestionsEntity;
import edu.ICET.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizQuestionsDao extends JpaRepository<QuizQuestionsEntity,Long> {
    List<QuizQuestionsEntity> findByQuestionTypeEnum(String type);
}
