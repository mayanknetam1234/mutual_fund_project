package com.mayank.mutualFund.authentication.service;



import com.mayank.mutualFund.authentication.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    boolean isExistsByUsername(String userName);

    boolean isExistsByEmail(String email);

    List<User> getAllUser();

    Optional<User> getUserByEmail(String email);

    String getEmailOfUser();
}
