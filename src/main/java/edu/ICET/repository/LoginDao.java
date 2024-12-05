package edu.ICET.repository;

import edu.ICET.entity.LoginEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<LoginEntity,Long> {
    LoginEntity findByUsername(String username);
    boolean existsByUsername(@NotNull(message = "Username cannot be empty!") @Size(min = 2, max = 19, message = "Username should be between 2 and 19 characters") @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._-]{2,19}$", message = "Invalid Username Format") String username);
}
