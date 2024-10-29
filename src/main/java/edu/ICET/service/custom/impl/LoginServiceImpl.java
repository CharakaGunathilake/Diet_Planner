package edu.ICET.service.custom.impl;

import ch.qos.logback.core.util.COWArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import edu.ICET.entity.LoginEntity;
import edu.ICET.entity.MealInfoEntity;
import edu.ICET.repository.LoginDao;
import edu.ICET.service.custom.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(Login login) {
        loginDao.save(objectMapper.convertValue(login, LoginEntity.class));
        return loginDao.existsById(login.getId());
    }

    @Override
    public Login search(Long id) {
        return objectMapper.convertValue(loginDao.findById(id), Login.class);
    }

    @Override
    public boolean update(Login newLogin) {
        LoginEntity mappedEntity = objectMapper.convertValue(search(newLogin.getId()), LoginEntity.class);
        loginDao.save(mappedEntity);
        return loginDao.equals(mappedEntity);
    }


    @Override
    public boolean delete(Long id) {
        loginDao.deleteById(id);
        return !loginDao.existsById(id);
    }

    @Override
    public List<Login> getAll() {
        List<Login> loginList = new ArrayList<>();
        loginDao.findAll().forEach(loginEntity -> {
            loginList.add(objectMapper.convertValue(loginEntity, Login.class));
        });
        return loginList;
    }
}
