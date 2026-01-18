package com.hsfulda.demo.user;

import java.util.List;

public interface UserService {
    List<User> getUsersList();

    User getUserById(int id);

    List<User> addNewUser(User newUser);

    long getUserId();
}
