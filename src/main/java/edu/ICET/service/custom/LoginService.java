package edu.ICET.service.custom;

import edu.ICET.dto.Login;
import edu.ICET.service.SuperService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface LoginService extends SuperService<Login>{
    boolean checkUsername(String username);
    Map<String,String> verify(Login login);
}
