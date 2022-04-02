package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.User;
import com.gorshkov.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.gorshkov.movieland.util.FileReaderUtil.getRowsFromUrl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private static final String URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e11/download/user.txt";
    private static final int LINES_NUMBER = 3;

    private final UserService userService;

    @GetMapping("/add")
    public String addUser() {

        List<String> rows = getRowsFromUrl(URL_STRING);
        List<User> users = new ArrayList<>();
        User user = null;

        for (int i = 0; i < rows.size(); i++) {
            if (i % LINES_NUMBER == 0) {
                user = new User();
                user.setUserName(rows.get(i));
            } else if (i % LINES_NUMBER == 1) {
                user.setEmail(rows.get(i));
            } else {
                user.setPassword(rows.get(i));
                users.add(user);
                log.info("user: {}", user);
                userService.addUser(user);
            }
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<User> getAll(Model model) {

        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return users;
    }
}
