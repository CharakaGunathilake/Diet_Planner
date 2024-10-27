package edu.ICET.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietProgress {
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    private Integer totalCalories;
    @NotNull
    private Integer targetRequired;
    @NotNull
    private Integer targetAchieved;
    @Size(min = 2, max = 150, message = "Description should be between 2 and 50 characters")
    private String description;
    @NotNull
    @Min(value = 2)
    @Max(value = 6)
    private Integer mealsTaken;
}
