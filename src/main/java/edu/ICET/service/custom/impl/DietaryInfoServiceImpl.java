package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.entity.DietaryInfoEntity;
import edu.ICET.repository.DietaryInfoDao;
import edu.ICET.service.custom.DietaryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietaryInfoServiceImpl implements DietaryInfoService {

    private final DietaryInfoDao dietaryInfoDao;
    private final ObjectMapper objectMapper;
    private DietaryInfo dietaryInfo;

    @Override
    public boolean save(DietaryInfo dietaryInfo) {
        dietaryInfoDao.save(objectMapper.convertValue(dietaryInfo, DietaryInfoEntity.class));
        return false;
    }

    @Override
    public DietaryInfo search(Long id) {
        return objectMapper.convertValue(dietaryInfoDao.findById(id), DietaryInfo.class);
    }

    @Override
    public boolean update(DietaryInfo newDietaryInfo) {
        DietaryInfo dietaryInfo = search(newDietaryInfo.getId()) != null ? newDietaryInfo : null;
        dietaryInfoDao.save(objectMapper.convertValue(dietaryInfo, DietaryInfoEntity.class));
        return dietaryInfo.equals(newDietaryInfo);
    }

    @Override
    public boolean delete(Long id) {
        dietaryInfoDao.deleteById(id);
        return false;
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
