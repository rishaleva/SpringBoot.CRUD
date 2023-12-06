package ru.rishaleva.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rishaleva.springtask.model.User;
import ru.rishaleva.springtask.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String CreateUserForm(@ModelAttribute("user") User user) {
        return "userCreate";
    }

    @PostMapping("/userCreate")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/removeUser")
    public String removeUser(@RequestParam("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/updateUser")
    public String getEditUserForm(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "userUpdate";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
