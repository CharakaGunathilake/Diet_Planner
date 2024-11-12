package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.entity.DietaryInfoEntity;
import edu.ICET.repository.DietaryInfoDao;
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
        dietaryInfo.setCaloriesNeeded(getCaloriesNeeded(dietaryInfo.getTargetWeight(), dietaryInfo.getWeight()));
        dietaryInfo.setBmiStatus(getBmiStatus(dietaryInfo.getBmi()));
        dietaryInfo.setDcr(calculateDCR(dietaryInfo.getActivityRate(), dietaryInfo.getGender(), dietaryInfo.getWeight(), dietaryInfo.getHeight(), dietaryInfo.getAge()));
        dietaryInfo.setTargetDate(calculateTargetDate(dietaryInfo.getTargetDateString()));
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
        System.out.println( activityRate + " - " +  gender + " - " +  weight + " - " +  height + " - " +  age);
        double dcr;
        switch (gender) {
            case "Male":
                dcr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                System.out.println(dcr);
                return (int) (dcr * getActivityRate(activityRate));
            case "Female":
                dcr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
                System.out.println(dcr);
                return (int) (dcr * getActivityRate(activityRate));
            default:
                return 0;
        }
    }

    private Double getActivityRate(String activityRate) {
        switch (activityRate) {
            case "Sedentary (little to no exercise)":
                return 1.2;
            case "Lightly active (exercise 1-2 times a week)":
                return 1.375;
            case "Moderately active (exercise 3-5 times a week)":
                return 1.55;
            case "Very active (intense exercise or physical job)":
                return 1.725;
        }
        return 0.0;
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

    Date calculateTargetDate(String targetDate) {
        switch (targetDate) {
            case "Within 1 month":
                return new Date(new Date().getTime() + 30L * 24 * 60 * 60 * 1000);
            case "Within 3 months":
                return new Date(new Date().getTime() + 90L * 24 * 60 * 60 * 1000);
            case "Within 6 months":
                return new Date(new Date().getTime() + 180L * 24 * 60 * 60 * 1000);
            case "Within 1 year":
                return new Date(new Date().getTime() + 365L * 24 * 60 * 60 * 1000);
            default:
                return new Date();
        }
    }
}
