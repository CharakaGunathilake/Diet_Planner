package edu.ICET.repository;

import edu.ICET.entity.MealInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealInfoDao extends JpaRepository<MealInfoEntity,Long> {
}
