package edu.ICET.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietaryInfo {
    private Long id;
    @NotNull
    private Integer age;
    @NotNull
    private Double height;
    @NotNull
    private Double weight;
    @NotNull
    private String dietPreference;
    @NotNull
    private String activityRate;
    @NotNull
    private String goal;
    @NotNull
    private String specificCuisine;
    @NotNull
    private String intolerances;
    @NotNull
    @Min(value = 10, message = "Invalid weight")
    @Max(value = 200, message = "Invalid weight")
    private Double targetWeight;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;
    private Integer caloriesDeficit;
    @NotNull
    private Double bmi;
    @NotNull
    private Integer waterIntake;
    @NotNull
    private String sleepPattern;
    @NotNull
    private String stressFrequency;
    @NotNull
    private Integer caloriesNeeded;
    @NotNull
    private Integer mealPlan;
    @NotNull
    private String cookingHabit;
    @NotNull
    private Integer dcr;
    @NotNull
    @Pattern(regexp = "^(Overweight|Normal|Underweight|Obese)$")
    private String bmiStatus;
    private Long UserId;
}
