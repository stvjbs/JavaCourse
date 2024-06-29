package com.andersenlab.dao.impl;

import com.andersenlab.entity.User;

public interface UserCRUDable {
    void saveUser(User user);
    User getUserById(int id);
    void deleteUserById(int id);

}
