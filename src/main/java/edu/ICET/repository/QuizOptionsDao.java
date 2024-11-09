package edu.ICET.repository;

import edu.ICET.entity.QuizOptionsEntity;
import edu.ICET.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizOptionsDao extends JpaRepository<QuizOptionsEntity,Long> {
    List<QuizOptionsEntity> findByQuestionId(Long id);
}
