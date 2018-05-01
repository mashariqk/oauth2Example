package com.oauth2.example.services;

import com.oauth2.example.dtos.UserDTO;

import java.util.List;

public interface UserService {

    boolean createNewUser(UserDTO userDTO);

    List<UserDTO> fetchByExample(UserDTO userDTO);
}
