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
    @Min(value = 1, message = "Invalid weight")
    @Max(value = 20, message = "Invalid weight")
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
    @Pattern(regexp = "^(Normal|Overweight|Underweight)")
    private String bmiStatus;
}
