package edu.ICET.service.custom;

import edu.ICET.dto.DietaryInfo;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import edu.ICET.dto.UserWithPlan;
import edu.ICET.entity.UserWithPlanEntity;
import edu.ICET.service.SuperService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends SuperService<User> {
    Long getUserId();
    boolean saveNewUser(@Valid UserWithPlan userWithPlan);
    UserWithPlan getUserWithPlanBy(Long id);
    boolean verifyEmail(@Valid String email);
}
