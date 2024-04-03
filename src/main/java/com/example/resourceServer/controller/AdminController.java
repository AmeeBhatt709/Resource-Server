package com.example.resourceServer.controller;

import com.example.resourceServer.dto.UserRequestDto;
import com.example.resourceServer.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    @PreAuthorize("isMember('read')")
    @GetMapping
    public String getHello()
    {
        return "Hello Admin";
    }

    @GetMapping("/getAllUser")
    public List<UserRequestDto> getAllUser()
    {
        return userService.getAllUser();
    }

    @PostMapping("/addPrivileges")
    public  String  upDatePrivilege( @Valid @RequestBody List<UserRequestDto> userRequestDto)
    {
        this.userService.updatePrivileges(userRequestDto);
        return "permission has been updated";

    }


}
