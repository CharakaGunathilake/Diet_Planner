package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.entity.DietaryInfoEntity;
import edu.ICET.repository.DietaryInfoDao;
import edu.ICET.service.custom.DietPlanService;
import edu.ICET.service.custom.DietaryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietaryInfoServiceImpl implements DietaryInfoService {
    private final DietaryInfoDao dietaryInfoDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(DietaryInfo dietaryInfo) {
        dietaryInfoDao.save(objectMapper.convertValue(dietaryInfo,DietaryInfoEntity.class));
        return dietaryInfoDao.equals(dietaryInfo);
    }

    @Override
    public void setCalculatedData(DietaryInfo dietaryInfo) {
        dietaryInfo.setBmi(calculateBMI(dietaryInfo.getHeight(), dietaryInfo.getWeight()));
        dietaryInfo.setDcr(calculateDCR(dietaryInfo.getActivityRate(), dietaryInfo.getGender(), dietaryInfo.getWeight(), dietaryInfo.getHeight(), dietaryInfo.getAge()));
        dietaryInfo.setCaloriesNeeded(getCaloriesNeeded(dietaryInfo.getTargetWeight(), dietaryInfo.getWeight()));
        dietaryInfo.setBmiStatus(getBmiStatus(dietaryInfo.getBmi()));
        dietaryInfo.setTargetDate(calculateTargetDate(dietaryInfo.getCaloriesNeeded(),dietaryInfo.getDcr(),dietaryInfo.getTargetDateString()));
    }

    @Override
    public DietaryInfo search(Long id) {
        return objectMapper.convertValue(dietaryInfoDao.findById(id), DietaryInfo.class);
    }

    @Override
    public boolean update(DietaryInfo newDietaryInfo) {
        DietaryInfoEntity mappedEntity = objectMapper.convertValue(search(newDietaryInfo.getId()), DietaryInfoEntity.class);
        dietaryInfoDao.save(mappedEntity);
        return dietaryInfoDao.equals(mappedEntity);
    }

    @Override
    public boolean delete(Long id) {
        dietaryInfoDao.deleteById(id);
        return !dietaryInfoDao.existsById(id);
    }

    @Override
    public List<DietaryInfo> getAll() {
        List<DietaryInfo> dietaryInfoList = new ArrayList<>();
        dietaryInfoDao.findAll().forEach(dietaryInfoEntity -> {
            dietaryInfoList.add(objectMapper.convertValue(dietaryInfoEntity, DietaryInfo.class));
        });
        return dietaryInfoList;
    }

    private Integer calculateDCR(String activityRate, String gender, Double weight, Double height, Integer age) {
        double dcr;
        switch (gender.toLowerCase()) {
            case "male":
                dcr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                return (int) (dcr * getActivityRate(activityRate));
            case "female":
                dcr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
                return (int) (dcr * getActivityRate(activityRate));
            default:
                return 0;
        }
    }

    private Double getActivityRate(String activityRate) {
        return switch (activityRate.toLowerCase()) {
            case "sedentary (little to no exercise)" -> 1.2;
            case "lightly active (exercise 1-2 times a week)" -> 1.375;
            case "moderately active (exercise 3-5 times a week)" -> 1.55;
            case "very active (intense exercise or physical job)" -> 1.725;
            default -> 0.0;
        };
    }

    private Double calculateBMI(Double height, Double weight) {
        height /=100;
        return weight/(height*height);
    }

    private String getBmiStatus(Double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private Integer getCaloriesNeeded(Integer targetWeight, Double weight) {
        if (targetWeight > weight) {
            int weightDiff = (int) (targetWeight - weight);
            return weightDiff * 7700;
        }
        return 0;
    }

    Date calculateTargetDate(Integer caloriesNeeded,Integer dcr, String targetDate) {
        int dateFormula = 24 * 60 * 60 * 1000;
        return switch (targetDate.toLowerCase()) {
            case "within 1 month" -> new Date(new Date().getTime() + 30L * dateFormula);
            case "within 3 months" -> new Date(new Date().getTime() + 90L * dateFormula);
            case "within 6 months" -> new Date(new Date().getTime() + 180L * dateFormula);
            case "within 1 year" -> new Date(new Date().getTime() + 365L * dateFormula);
            default -> new Date(new Date().getTime() + (long) (caloriesNeeded/dcr) * dateFormula);
        };
    }
}
