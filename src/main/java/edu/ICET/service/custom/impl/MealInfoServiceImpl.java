package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.MealInfo;
import edu.ICET.entity.MealInfoEntity;
import edu.ICET.repository.MealInfoDao;
import edu.ICET.service.custom.MealInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealInfoServiceImpl implements MealInfoService {

    private final MealInfoDao mealInfoDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(MealInfo mealInfo) {
        mealInfoDao.save(objectMapper.convertValue(mealInfo, MealInfoEntity.class));
        return mealInfoDao.equals(mealInfo);
    }

    @Override
    public MealInfo search(Long id) {
        return objectMapper.convertValue(mealInfoDao.findById(id), MealInfo.class);
    }

    @Override
    public boolean update(MealInfo newMealInfo) {
        MealInfoEntity mappedEntity = objectMapper.convertValue(search(newMealInfo.getId()), MealInfoEntity.class);
        mealInfoDao.save(mappedEntity);
        return mealInfoDao.equals(mappedEntity);
    }

    @Override
    public boolean delete(Long id) {
        mealInfoDao.deleteById(id);
        return !mealInfoDao.existsById(id);
    }

    @Override
    public List<MealInfo> getAll() {
        List<MealInfo> mealInfoList = new ArrayList<>();
        mealInfoDao.findAll().forEach(mealInfoEntity -> {
            mealInfoList.add(objectMapper.convertValue(mealInfoEntity, MealInfo.class));
        });
        return mealInfoList;
    }

    @Override
    public boolean setMealCompleted(Boolean status, Long userId, String mealName, String timeCompleted) {
        boolean bool = false;
        for (MealInfo mealInfo : getAllByUserId(userId, String.valueOf(LocalDate.now()))) {
            if (mealInfo.getMealName().equals(mealName)) {
                mealInfo.setTimeCompleted(timeCompleted);
                mealInfo.setCompletedMeal(status);
                bool = true;
            }
        }
        return bool;
    }

    @Override
    public List<MealInfo> getAllByUserId(Long id, String date) {
        List<MealInfo> listByUserId = new ArrayList<>();
        getAll().forEach(mealInfo -> {
            if (mealInfo.getUserId().equals(id) && mealInfo.getMealDate().equals(date)) {
                listByUserId.add(mealInfo);
            }
        });
        return listByUserId;
    }

    @Override
    public void addMealsForTheDay(Integer mealPlan) {
        String[] mealArray;
        String[] mealTimeArray;
        switch (mealPlan) {
            case 2:
                mealArray = new String[]{"Meal 1", "Meal 2"};
                mealTimeArray = new String[]{"Between 10:00 AM and 12:00 PM", "Between 12:00 PM and 6:00 PM"};
                break;
            case 4:
                mealArray = new String[]{"BreakFast", "Lunch", "Afternoon Snack", "Dinner"};
                mealTimeArray = new String[]{"Between 8:00 AM and 10:00 AM", "Between 11:00 AM and 1:00 PM", "Between 3:00 PM and 5:00 PM", "7:00 PM"};
                break;
            case 5:
                mealArray = new String[]{"BreakFast", "Second Breakfast", "Lunch", "Afternoon Snack", "Dinner"};
                mealTimeArray = new String[]{"Between 6:00 AM and 8:00 AM", "Between 8:00 AM and 11:00 AM", "Between 11:00 AM and 1:00 PM", "Between 3:00 PM and 5:00 PM", "7:00 PM"};
                break;
            default:
                mealArray = new String[]{"BreakFast", "Lunch", "Dinner"};
                mealTimeArray = new String[]{"9:00 AM", "Between 12:00 PM and 2:00 PM", "7:00 PM"};
                break;
        }
        setUpMeals(mealArray, mealTimeArray, LocalDate.now().toString());
    }

    private void setUpMeals(String[] mealArray, String[] mealTimeArray, String date) {
        for (int i = 0; i != mealArray.length; i++) {
            save(new MealInfo(
                    null,
                    0L,
                    0L,
                    "",
                    mealArray[i],
                    "",
                    0,
                    "",
                    mealTimeArray[i],
                    "",
                    date,
                    false
            ));
        }
    }
}
