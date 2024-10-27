package edu.ICET.repository;

import edu.ICET.entity.DietProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietProgressDao extends JpaRepository<DietProgressEntity,Long> {
}
