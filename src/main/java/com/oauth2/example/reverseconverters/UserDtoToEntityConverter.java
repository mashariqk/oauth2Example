package com.oauth2.example.reverseconverters;

import com.oauth2.example.dtos.UserDTO;
import com.oauth2.example.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToEntityConverter implements Converter<UserDTO,User> {

    @Override
    public User convert(UserDTO source) {
        User target = new User();
        target.setUsername(source.getUsername());
        target.setEnabled(true);
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        return target;
    }
}
