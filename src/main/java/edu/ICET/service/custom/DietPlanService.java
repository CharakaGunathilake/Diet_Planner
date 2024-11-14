package edu.ICET.service.custom;

import edu.ICET.dto.DietPlan;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.service.SuperService;
import jakarta.validation.constraints.NotNull;

public interface DietPlanService extends SuperService<DietPlan> {
    void setPlanDetails(@NotNull(message = "Diet info can't be empty") DietaryInfo dietaryInfo);
}
