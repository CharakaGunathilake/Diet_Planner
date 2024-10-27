package edu.ICET.repository;

import edu.ICET.entity.DietaryInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaryInfoDao extends JpaRepository<DietaryInfoEntity,Long> {
}
