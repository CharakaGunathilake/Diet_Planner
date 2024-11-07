package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.MealInfo;
import edu.ICET.entity.MealInfoEntity;
import edu.ICET.repository.MealInfoDao;
import edu.ICET.service.custom.MealInfoServiceMy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealInfoServiceImplMy implements MealInfoServiceMy {

    private final MealInfoDao mealInfoDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(MealInfo mealInfo) {
        mealInfoDao.save(objectMapper.convertValue(mealInfo, MealInfoEntity.class));
        return mealInfoDao.existsById(mealInfo.getId());
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
}
