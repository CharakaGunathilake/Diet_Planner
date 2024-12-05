package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.Login;
import edu.ICET.dto.UserPrincipal;
import edu.ICET.entity.LoginEntity;
import edu.ICET.repository.LoginDao;
import edu.ICET.service.custom.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;
    private final JWTService jwtService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;


    @Override
    public boolean save(Login login) {
        try {
            login.setPassword(new BCryptPasswordEncoder(12).encode(login.getPassword()));
            loginDao.save(objectMapper.convertValue(login, LoginEntity.class));
            return loginDao.existsByUsername(login.getUsername());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String verify(Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUsername(), login.getPassword()
            ));
            return jwtService.generateToken(login.getUsername());
        } catch (AuthenticationException e) {
            return "Failure";
        }
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

    @Override
    public boolean checkUsername(String username) {
        List<Login> loginList = getAll();
        for (Login login : loginList) {
            return login.getUsername().equalsIgnoreCase(username);
        }
        return false;
    }
}
