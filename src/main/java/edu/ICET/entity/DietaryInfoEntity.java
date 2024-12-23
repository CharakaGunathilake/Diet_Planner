package edu.ICET.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DietaryInfo")
public class DietaryInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    private Double height;
    private Double weight;
    private String dietPreference;
    private String activityRate;
    private String goal;
    private String specificCuisine;
    private String intolerances;
    private Integer targetWeight;
    private Date targetDate;
    private String caloriesDeficit;
    private Double bmi;
    private Integer waterIntake;
    private Integer caloriesNeeded;
    private String sleepPattern;
    private String stressFrequency;
    private Integer mealPlan;
    private String cookingHabit;
    private Integer dcr;
    private String bmiStatus;
}
