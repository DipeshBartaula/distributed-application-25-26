package com.hsfulda.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    //Constructor Injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.getUsersList();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public List<User> createUser(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String postalCode
    ) {
        Address address = new Address(street, city, state, postalCode);
        User newUser = new User(id, firstName, lastName, address, email);
        return userService.addNewUser(newUser);
    }

}
