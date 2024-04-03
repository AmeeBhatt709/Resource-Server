package com.example.resourceServer.controller;
import com.example.resourceServer.dto.UserRequestDto;
import com.example.resourceServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

//    @PostMapping("/add")
//    public String addUser(@RequestBody UserRequestDto userRequestDto) {
//            this.userService.addUser(userRequestDto);
//        return "User Has been registered";
//    }
    @GetMapping("/add")
    public String addUser() {
//        this.userService.addUser(userRequestDto);
        return "User Has been registered";
    }
}
