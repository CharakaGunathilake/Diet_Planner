package edu.ICET.service.custom;

import edu.ICET.dto.Login;
import edu.ICET.service.SuperService;

public interface LoginService extends SuperService<Login> {
    boolean checkUsername(String username);
    Login searchByUsername(String username);
}
