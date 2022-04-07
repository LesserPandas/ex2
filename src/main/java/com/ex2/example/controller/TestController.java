package com.ex2.example.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class TestController {

    @GetMapping(value = "/")
    public String HelloWorld() {
        return "Hello World";
    }

//    @GetMapping(value = "/test")
//    public UserDto test() {
//
//        UserDto userDto = new UserDto();
//        userDto.setAge(20);
//        userDto.setName("chan");
//
//        return userDto;
//    }
}
