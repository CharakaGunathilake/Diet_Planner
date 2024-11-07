package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.entity.DietaryInfoEntity;
import edu.ICET.repository.DietaryInfoDao;
import edu.ICET.service.custom.DietaryInfoServiceMy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietaryInfoServiceImplMy implements DietaryInfoServiceMy {

    private final DietaryInfoDao dietaryInfoDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(DietaryInfo dietaryInfo) {
        dietaryInfoDao.save(objectMapper.convertValue(dietaryInfo, DietaryInfoEntity.class));
        return dietaryInfoDao.existsById(dietaryInfo.getId());
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
}
