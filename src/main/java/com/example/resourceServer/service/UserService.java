package com.example.resourceServer.service;

import com.example.resourceServer.dto.UserRequestDto;

import java.util.List;

public interface UserService {
    void addUser(UserRequestDto userRequestDto);

    List<UserRequestDto> getAllUser();

    void updatePrivileges(List<UserRequestDto> userRequestDto);

}
