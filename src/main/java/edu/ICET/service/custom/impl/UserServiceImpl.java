package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.*;
import edu.ICET.entity.UserEntity;
import edu.ICET.entity.UserWithPlanEntity;
import edu.ICET.repository.DietaryInfoDao;
import edu.ICET.repository.LoginDao;
import edu.ICET.repository.UserDao;
import edu.ICET.repository.UserWithPlanDao;
import edu.ICET.service.custom.DietPlanService;
import edu.ICET.service.custom.DietaryInfoService;
import edu.ICET.service.custom.LoginService;
import edu.ICET.service.custom.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserWithPlanDao userWithPlanDao;
    private final DietaryInfoService dietaryInfoService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(User user) {
        userDao.save(objectMapper.convertValue(user, UserEntity.class));
        return userDao.equals(user);
    }

    @Override
    public User search(Long id) {
        return objectMapper.convertValue(userDao.findById(id), User.class);
    }

    @Override
    public boolean update(User newUser) {
        UserEntity mappedEntity = objectMapper.convertValue(newUser, UserEntity.class);
        userDao.save(mappedEntity);
        return userDao.equals(mappedEntity);
    }

    @Override
    public boolean delete(Long id) {
        userWithPlanDao.deleteById(id);
        return !userWithPlanDao.existsById(id);
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        userDao.findAll().forEach(userEntity -> {
            userList.add(objectMapper.convertValue(userEntity, User.class));
        });
        return userList;
    }

    @Override
    public Long getUserId() {
        return userDao.count();
    }

    @Override
    public boolean saveNewUser(UserWithPlan userWithPlan) {
        DietaryInfo dietaryInfo = userWithPlan.getDietaryInfo();
        dietaryInfoService.setCalculatedData(dietaryInfo);
        userWithPlan.getDietPlan().setStartDate(userWithPlan.getUser().getRegDate());
        userWithPlan.getDietPlan().setEndDate(dietaryInfo.getTargetDate());
        userWithPlan.getDietPlan().setDietType(dietaryInfo.getDietPreference().equals("none") ? "General Diet":dietaryInfo.getDietPreference());
        userWithPlanDao.save(objectMapper.convertValue(userWithPlan, UserWithPlanEntity.class));
        return false;
    }

    @Override
    public UserWithPlan getUserWithPlanBy(Long id){
        return (objectMapper.convertValue(userWithPlanDao.findByLoginId(id),UserWithPlan.class));
    }

    @Override
    public boolean verifyEmail(String email) {
        return userDao.existsByEmail(email);
    }

}
