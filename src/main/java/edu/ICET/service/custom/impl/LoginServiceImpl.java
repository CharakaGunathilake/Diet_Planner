package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import edu.ICET.entity.LoginEntity;
import edu.ICET.repository.LoginDao;
import edu.ICET.service.custom.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(Login login) {
        loginDao.save(objectMapper.convertValue(login, LoginEntity.class));
        return false;
    }

    @Override
    public Login search(Long id) {
        return objectMapper.convertValue(loginDao.findById(id), Login.class);
    }

    @Override
    public boolean update(Login login) {
        loginDao.save(objectMapper.convertValue(login, LoginEntity.class));
        return false;
    }

    @Override
    public boolean delete(Long id) {
        loginDao.deleteById(id);
        return false;
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
