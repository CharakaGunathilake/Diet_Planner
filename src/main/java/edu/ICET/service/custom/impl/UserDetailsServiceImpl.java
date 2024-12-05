package edu.ICET.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ICET.dto.Login;
import edu.ICET.dto.UserPrincipal;
import edu.ICET.entity.LoginEntity;
import edu.ICET.repository.LoginDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginDao loginDao;
    private final ObjectMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginEntity login = loginDao.findByUsername(username);
        if (login == null) {
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(mapper.convertValue(login, Login.class));
    }
}
