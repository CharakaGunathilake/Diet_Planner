package edu.ICET.service.custom;

import edu.ICET.dto.MealInfo;
import edu.ICET.service.SuperService;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface MealInfoService extends SuperService<MealInfo> {
    boolean setMealCompleted(Boolean status, Long userId, String mealId, String timeCompleted);
    List<MealInfo> getAllByUserId(Long id,String date);
    void addMealsForTheDay(@NotNull Integer mealPlan);
}
