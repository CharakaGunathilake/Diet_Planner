package edu.ICET.service.custom;

import edu.ICET.dto.DietaryInfo;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import edu.ICET.dto.UserWithPlan;
import edu.ICET.entity.UserWithPlanEntity;
import edu.ICET.service.SuperService;
import jakarta.validation.Valid;

public interface UserService extends SuperService<User> {
    Long getUserId();
    boolean saveNewUser(@Valid UserWithPlan userWithPlan);

    boolean verifyEmail(@Valid String email);
}
