package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietPlan {
    private Long id;
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @Size(min = 2, max = 150, message = "Description should be between 2 and 50 characters")
    private String description;
    @NotNull
    private Date startDate;
    @NotNull
    private String endDateString;
    private Date endDate;
    @NotNull
    private String dietType;
    private Long userId;
}
