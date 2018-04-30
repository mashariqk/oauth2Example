package com.oauth2.example.services;

import com.oauth2.example.dtos.UserDTO;

public interface UserService {
    boolean createNewUser(UserDTO userDTO);
}
