package com.sda.datingapp.service;

import com.sda.datingapp.dto.UserDto;
import com.sda.datingapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public boolean userExist(String username);

    public User registerNewUser(UserDto userDto);
}
