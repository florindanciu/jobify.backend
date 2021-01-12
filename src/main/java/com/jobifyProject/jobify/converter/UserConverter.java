package com.jobifyProject.jobify.converter;

import com.jobifyProject.jobify.dto.UserDto;
import com.jobifyProject.jobify.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto modelToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> modelToDto(List<User> userList) {
        return userList.stream().map(user -> modelToDto(user)).collect(Collectors.toList());
    }

    public User dtoToModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<User> dtoToModel(List<UserDto> userDtoList) {
        return userDtoList.stream().map(userDto -> dtoToModel(userDto)).collect(Collectors.toList());
    }
}
