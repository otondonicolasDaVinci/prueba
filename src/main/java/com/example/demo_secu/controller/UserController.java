package com.example.demo_secu.controller;

import com.example.demo_secu.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService.UserClientServiceImpl userClientService;


    public UserController(UserService.UserClientServiceImpl userClientService) {
        this.userClientService = userClientService;
    }


    @GetMapping("/test-practico/v1/users/all")
    public List<UserService.UserRecord> getAllUsersWithFeign(@RequestParam(required = false) String orderBy, @RequestParam(required = false) String filterBy) {
        return userClientService.getAllUsers(orderBy, filterBy);
    }
}
