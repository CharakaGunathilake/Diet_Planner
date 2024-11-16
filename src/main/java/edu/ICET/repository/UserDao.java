package edu.ICET.repository;

import edu.ICET.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
}
