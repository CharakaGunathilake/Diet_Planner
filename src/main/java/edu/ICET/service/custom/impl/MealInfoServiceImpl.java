package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.MealInfo;
import edu.ICET.entity.MealInfoEntity;
import edu.ICET.repository.MealInfoDao;
import edu.ICET.service.custom.MealInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealInfoServiceImpl implements MealInfoService {

    private final MealInfoDao mealInfoDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(MealInfo mealInfo) {
        mealInfoDao.save(objectMapper.convertValue(mealInfo, MealInfoEntity.class));
        return false;
    }

    @Override
    public MealInfo search(Long id) {
        return objectMapper.convertValue(mealInfoDao.findById(id),MealInfo.class);
    }

    @Override
    public boolean update(MealInfo mealInfo) {
        mealInfoDao.save(objectMapper.convertValue(mealInfo, MealInfoEntity.class));
        return false;
    }

    @Override
    public boolean delete(Long id) {
        mealInfoDao.deleteById(id);
        return false;
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
