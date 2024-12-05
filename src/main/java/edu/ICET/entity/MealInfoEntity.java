package edu.ICET.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "MealInfo")
public class MealInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UserId;
    private Long mealId;
    private String recipeName;
    private String mealName;
    private String cuisines;
    private Integer calories;
    private String imageLink;
    private String mealTime;
    private String timeCompleted;
    private String mealDate;
    private Boolean completedMeal;
}
