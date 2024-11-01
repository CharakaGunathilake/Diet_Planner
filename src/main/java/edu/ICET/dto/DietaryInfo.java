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
    private String activityRate;
    @NotNull
    private String goals;
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
    @NotNull
    private Double bmi;
    @Size(min = 1, max = 20, message = "Invalid dcr")
    private String dcr;
    @NotNull
    private Integer caloriesNeeded;
    @NotNull
    private Integer caloriesDeficit;
    @NotNull
    @Pattern(regexp = "^(Overweight|Normal|Underweight)$")
    private String bmiStatus;
}
