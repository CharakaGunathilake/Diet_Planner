package edu.ICET.service.custom;

import edu.ICET.dto.User;
import edu.ICET.service.SuperService;

public interface UserService extends SuperService<User> {
    Long getUserId();
}
