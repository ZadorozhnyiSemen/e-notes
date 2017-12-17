package com.epam.spring.controller;

import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(@PathVariable(value = "s", required = false) String s) {
        if (s != null) {
            final List<User> searchUsers = userService.findAllByUsername(s);
            return new ResponseEntity<>(searchUsers, HttpStatus.OK);
        } else {
            final List<User> users = userService.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@RequestParam Long userId) {
        User deactivatedUser = userService.delete(userId);
        return new ResponseEntity<>(deactivatedUser, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        int updated = userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
