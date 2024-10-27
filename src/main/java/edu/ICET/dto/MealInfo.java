package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealInfo {
    private Long id;
    @NotNull
    @Pattern(regexp = "^(Snack|BreakFast|Lunch|Dinner)$", message = "Invalid meal name")
    private String name;
    @Size(min = 2, max = 150, message = "Description should be between 2 and 50 characters")
    private String description;
    @NotNull
    private Integer calories;
    @NotNull
    private LocalTime time;
}
