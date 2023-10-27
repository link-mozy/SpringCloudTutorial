package com.dream.userservice.service;

import com.dream.userservice.dto.UserDto;
import com.dream.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
