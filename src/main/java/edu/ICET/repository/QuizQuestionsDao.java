package edu.ICET.repository;

import edu.ICET.entity.QuizQuestionsEntity;
import edu.ICET.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionsDao extends JpaRepository<QuizQuestionsEntity,Long> {
}
