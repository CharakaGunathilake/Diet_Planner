package edu.ICET.repository;

import edu.ICET.entity.DietPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanDao extends JpaRepository<DietPlanEntity,Long> {
}
