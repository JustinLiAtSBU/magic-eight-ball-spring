package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.Channel;
import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.model.User;
import com.example.magic_eight_ball.repository.channel.ChannelRepository;
import com.example.magic_eight_ball.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    ChannelService channelService;

    @GetMapping("/all")
    public ResponseEntity<List<Channel>> getAllChannels() {
        try {
            List<Channel> channels = channelRepository.findAll();
            if (channels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(channels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getChannelCount() {
        try {
            List<Channel> channels = channelRepository.findAll();
            return new ResponseEntity<>(channels.size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{channelId}")
    public ResponseEntity<Channel> getChannelById(@PathVariable("channelId") String channelId) {
        System.out.println("channelId: " + channelId);
        Optional<Channel> channelData = channelRepository.findByChannelId(channelId);
        return channelData.map(channel -> new ResponseEntity<>(channel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createChannel(
            @RequestParam String channelId,
            @RequestParam String channelName,
            @RequestParam String guild,
            @RequestBody ArrayList<String> members) {
        System.out.println(members);
        HashSet<String> membersSet = new HashSet<>(members);
        Optional<Channel> channel = channelRepository.findByChannelId(channelId);
        if (channel.isPresent()) {
            Channel confirmedChannel = channel.get();
            channelService.updateChannelMembers(confirmedChannel, membersSet);
            return new ResponseEntity<>("Channel " + channelName + " members updated.", HttpStatus.NO_CONTENT);
        } else {
            channelService.createChannelWithMembers(channelId, channelName, guild, membersSet);
            return ResponseEntity.ok("Channel " + channelName + " created.");
        }
    }

    @PutMapping("/updatewatchedmovies/{channelId}")
    public ResponseEntity<String> updateChannelWatchedMovies(@PathVariable("channelId") String channelId, @RequestBody Movie movie) {
        Optional<Channel> channel = channelRepository.findByChannelId(channelId);

        if (!channel.isPresent()) {
            return new ResponseEntity<>("Channel " + channelId + " does not exist.", HttpStatus.NOT_FOUND);
        } else {
            Channel confirmedChannel = channel.get();
            channelService.updateChannelWatchedMovies(confirmedChannel, movie);
            return new ResponseEntity<>("Updated channels watched movies", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/updatewatchedtvshows/{channelId}")
    public ResponseEntity<String> updateChannelWatchedTvShows(@PathVariable("channelId") String channelId, @RequestBody TvShow tvShow) {
        Optional<Channel> channel = channelRepository.findByChannelId(channelId);

        if (!channel.isPresent()) {
            return new ResponseEntity<>("Channel " + channelId + " does not exist.", HttpStatus.NOT_FOUND);
        } else {
            Channel confirmedChannel = channel.get();
            channelService.updateChannelWatchedTvShows(confirmedChannel, tvShow);
            return new ResponseEntity<>("Updated channels watched TV shows", HttpStatus.NO_CONTENT);
        }
    }
}