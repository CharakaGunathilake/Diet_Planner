package edu.ICET.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String activityRate;
    private String goals;
    private String specificCuisine;
    private String intolerances;
    private Double targetWeight;
    private LocalDate targetDate;
    private Double bmi;
    private String dcr;
    private Integer caloriesNeeded;
    private Integer caloriesDeficit;
    private String bmiStatus;
}
