package com.example.magic_eight_ball.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("movie")
public class Movie {

    @Id
    private String id;

    private String tconst;
    private String title;
    private Double rating;
    private int votes;
    private String rottenTomatoes;
    private String metacritic;
    private int year;
    private int runtime;
    private String country;
    private String[] languages;
    private String[] genres;
    private String director;
    private String actors;
    private String plot;
    private String awards;
    private String poster;

    public String toString() {
        StringBuilder desc = new StringBuilder("Movie (" + tconst + "): " + title);
        desc.append("\n├── Rating: ").append(rating);
        desc.append("\n├── Votes: ").append(votes);
        desc.append("\n├── Year: ").append(year);
        desc.append("\n├── Country: ").append(country);
        desc.append("\n└── Genres: ").append(Arrays.toString(genres));
        return desc.toString();
    }
}
