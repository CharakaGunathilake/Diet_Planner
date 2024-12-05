package edu.ICET.service.custom;

import edu.ICET.dto.Login;
import edu.ICET.service.SuperService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends SuperService<Login>{
    boolean checkUsername(String username);
    String verify(Login login);
}
