package com.ex2.shop.controller;

import com.ex2.shop.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public UserDto test() {

        UserDto userDto = new UserDto();
        userDto.setAge(20);
        userDto.setName("chan");

        return userDto;
    }
}
