package edu.ICET.repository;

import edu.ICET.entity.QuizOptionsEntity;
import edu.ICET.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizOptionsDao extends JpaRepository<QuizOptionsEntity,Long> {
}
