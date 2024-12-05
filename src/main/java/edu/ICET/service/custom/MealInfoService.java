package edu.ICET.service.custom;

import edu.ICET.dto.MealInfo;
import edu.ICET.service.SuperService;

import java.util.Date;
import java.util.List;

public interface MealInfoService extends SuperService<MealInfo> {
    boolean setMealCompleted(Boolean status, Long userId, Long mealId, String completedDate);
    List<MealInfo> getAllByUserId(Long id,String date);
}
