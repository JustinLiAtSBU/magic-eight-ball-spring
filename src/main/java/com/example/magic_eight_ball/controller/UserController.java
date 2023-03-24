package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.User;
import com.example.magic_eight_ball.repository.user.UserRepository;
import com.example.magic_eight_ball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users.size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/watched")
    public ResponseEntity<User> updateUser(@RequestParam String name, @RequestBody Movie movie) {
        Optional<User> user = userRepository.findByName(name);

        if (!user.isPresent()) {
            User newUser = userService.createUserWithWatchedMovies(name, movie);
            return ResponseEntity.ok(newUser);
        } else {
            User updatedUser = userService.updateUsersWatchedMovies(user.get(), movie);
            return ResponseEntity.ok(updatedUser);
        }
    }
}
