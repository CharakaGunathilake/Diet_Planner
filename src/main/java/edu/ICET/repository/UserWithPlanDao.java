package edu.ICET.repository;

import edu.ICET.entity.UserEntity;
import edu.ICET.entity.UserWithPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithPlanDao extends JpaRepository<UserWithPlanEntity,Long> {
    UserWithPlanEntity findByloginId(Long id);
}
