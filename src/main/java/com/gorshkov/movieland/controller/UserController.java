package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.User;
import com.gorshkov.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gorshkov.movieland.util.Parser.parseUser;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String addUsers() {
        List<User> users = parseUser();
        userService.saveAll(users);
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<User> getAll(Model model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return users;
    }
}
