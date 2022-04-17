package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.User;

public interface UserService {
    User createUserWithWatchedMovies(String name, Movie movie);
    User updateUsersWatchedMovies(User user, Movie movie);
}
