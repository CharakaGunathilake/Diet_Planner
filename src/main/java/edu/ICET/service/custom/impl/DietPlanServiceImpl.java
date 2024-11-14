package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.DietPlan;
import edu.ICET.dto.DietaryInfo;
import edu.ICET.entity.DietPlanEntity;
import edu.ICET.repository.DietPlanDao;
import edu.ICET.service.custom.DietPlanService;
import edu.ICET.service.custom.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DietPlanServiceImpl implements DietPlanService {

    private final DietPlanDao dietPlanDao;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public boolean save(DietPlan dietPlan) {
        dietPlan.setUserId(userService.getUserId());
        dietPlanDao.save(objectMapper.convertValue(dietPlan, DietPlanEntity.class));
        return dietPlanDao.existsById(dietPlan.getId());
    }

    @Override
    public DietPlan search(Long id) {
        return objectMapper.convertValue(dietPlanDao.findById(id), DietPlan.class);
    }

    @Override
    public boolean update(DietPlan newdietPlan) {
        DietPlanEntity mappedEntity =  objectMapper.convertValue(search(newdietPlan.getId()), DietPlanEntity.class);
        dietPlanDao.save(mappedEntity);
        return dietPlanDao.equals(newdietPlan);
    }

    @Override
    public boolean delete(Long id) {
        dietPlanDao.deleteById(id);
        return !dietPlanDao.existsById(id);
    }

    @Override
    public List<DietPlan> getAll() {
        List<DietPlan> dietPlanList = new ArrayList<>();
        dietPlanDao.findAll().forEach(dietPlan -> {
            dietPlanList.add(objectMapper.convertValue(dietPlan, DietPlan.class));
        });
        return dietPlanList;
    }

    @Override
    public void setPlanDetails(@NotNull(message = "Diet info can't be empty") DietaryInfo dietaryInfo) {
        DietPlan dietPlan = new DietPlan();
//        dietPlan.setDescription(di);
    }
}
