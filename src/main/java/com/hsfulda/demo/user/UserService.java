package com.hsfulda.demo.user;

import java.util.List;

public interface UserService {
    List<User> getUsersList();
    User getUserById(int id);
}
