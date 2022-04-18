package com.example.magic_eight_ball.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("channel")
public class Channel {

    @Id
    private String id;

    private String name;
    private String channelId;
    private String guild;
    private Set<String> members;
    private Set<String> watchedMovies;
    private Set<String> watchedTvShows;

    public Channel(String channelId, String channelName, String guild, HashSet<String> members) {
        this.name = channelName;
        this.channelId = channelId;
        this.guild = guild;
        this.watchedMovies = new HashSet<>();
        this.watchedTvShows = new HashSet<>();
        this.members = members;
    }
}
