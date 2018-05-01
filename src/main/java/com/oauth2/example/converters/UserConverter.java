package com.oauth2.example.converters;

import com.oauth2.example.dtos.UserDTO;
import com.oauth2.example.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<User,UserDTO> {
    @Override
    public UserDTO convert(User source) {
        UserDTO target = new UserDTO();
        target.setUsername(source.getUsername());
        target.setEnabled(source.isEnabled());
        target.setPassword("Credentials: [PROTECTED]");
        return target;
    }
}
