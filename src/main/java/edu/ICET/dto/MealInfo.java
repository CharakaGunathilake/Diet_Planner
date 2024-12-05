package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealInfo {
    private Long id;
    private Long UserId;
    @NotNull
    private Long mealId;
    @NotNull
    private String recipeName;
    @NotNull
    private String mealName;
    private String cuisines;
    private Integer calories;
    private String imageLink;
    @NotNull
    private String mealTime;
    private String timeCompleted;
    private String mealDate;
    private Boolean completedMeal;
}
