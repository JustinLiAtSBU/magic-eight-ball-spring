package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.User;
import com.example.magic_eight_ball.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUserWithWatchedMovies(String name, Movie movie) {
        User newUser = new User(name, movie);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User updateUsersWatchedMovies(User user, Movie movie) {
        user.getWatchedMovies().putIfAbsent(movie.getTconst(), movie);
        userRepository.save(user);
        return user;
    }
}
