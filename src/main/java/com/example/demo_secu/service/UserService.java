package com.example.demo_secu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    public record UserRecord(String id, String name, String createdAt) {
    }


    @FeignClient(name = "users", url = "https://64f651fc2b07270f705e62c0.mockapi.io")
    public interface IUserClientService {
        @GetMapping("/v1/users")
        List<UserRecord> getAllUsers();
    }


    @Service
    public static class UserClientServiceImpl {
        private final IUserClientService IUserClientService;

        public UserClientServiceImpl(IUserClientService IUserClientService) {
            this.IUserClientService = IUserClientService;
        }

        public List<UserRecord> getAllUsers(String orderBy, String filterBy) {
            List<UserRecord> users = IUserClientService.getAllUsers();


            if (filterBy != null) {
                users = users.stream()
                        .filter(user -> user.name() != null && user.name().contains(filterBy))
                        .collect(Collectors.toList());
            }


            if (orderBy != null) {
                if (orderBy.equals("createdAt")) {
                    users.sort(Comparator.comparing(UserRecord::createdAt));
                } else if (orderBy.equals("id")) {
                    users.sort(Comparator.comparing(UserRecord::id));
                }
            }

            return users;
        }
    }
}