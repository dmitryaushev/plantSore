package com.aushev.plantstore.controller;

import com.aushev.plantstore.exception.UserAlreadyExistsException;
import com.aushev.plantstore.exception.UserNotExistException;
import com.aushev.plantstore.model.User;
import com.aushev.plantstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "user/show_users";
    }

    @GetMapping("/get")
    public String getUser(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        return "user/user_details";
    }

    @GetMapping("/findUser")
    public String showFindUserPage(){
        return "user/find_user";
    }

    @GetMapping("/find")
    public String findUser(@RequestParam("email") String email, Model model) {
        try {
            model.addAttribute("user", userService.findUser(email));
            return "user/user_details";
        } catch (UserNotExistException e) {
            model.addAttribute("error", e.getMessage());
            return "user/find_user";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/createUser")
    public String showCreateUserPage() {
        return "user/create_user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/create_user";
        }

        try {
            userService.createUser(user);
            return "user/user_details";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("message", e.getMessage());
            return "user/create_user";
        }
    }

    @GetMapping("/edit")
    public String showEditUserPage(@RequestParam("id") UUID id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        return "user/edit_user";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/edit_user";
        }

        try {
            userService.updateUser(user);
            return "user/user_details";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("message", e.getMessage());
            return "user/edit_user";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") UUID id) {
        userService.deleteUser(id);
        return "redirect:/user/showUsers";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.createUser(user);
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }
}
