package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.User;
import edu.ICET.entity.UserEntity;
import edu.ICET.repository.UserDao;
import edu.ICET.service.custom.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(User user) {
         userDao.save(objectMapper.convertValue(user, UserEntity.class));
         return false;
    }

    @Override
    public User search(Long id) {
        return objectMapper.convertValue(userDao.findById(id),User.class);
    }

    @Override
    public boolean update(User user) {
        userDao.save(objectMapper.convertValue(user, UserEntity.class));
        return false;
    }

    @Override
    public boolean delete(Long id) {
        userDao.deleteById(id);
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        userDao.findAll().forEach(userEntity -> {
            userList.add(objectMapper.convertValue(userEntity, User.class));
        });
        return userList;
    }
}
