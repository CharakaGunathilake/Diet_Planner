package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.MealInfo;
import edu.ICET.entity.MealInfoEntity;
import edu.ICET.repository.MealInfoDao;
import edu.ICET.service.custom.MealInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        System.out.println(listByUserId + " == " + id + " // " + date);
        return listByUserId;
    }
}
