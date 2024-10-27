package edu.ICET.repository;

import edu.ICET.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<LoginEntity,Long> {
}
