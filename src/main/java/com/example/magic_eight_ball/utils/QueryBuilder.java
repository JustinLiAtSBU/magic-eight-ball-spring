package com.example.magic_eight_ball.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public final class QueryBuilder {

    public static Query motionPictureQuery(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres, Sort sort) {
        List<Criteria> criteria = new ArrayList<>();
        if (iso != null) {
            Locale l = new Locale("", iso);
            String country = l.getDisplayCountry();
            criteria.add(Criteria.where("country").is(country));
        }
        if (minRating != null) {
            criteria.add(Criteria.where("rating").gte(minRating));
        }
        if (minVotes != null) {
            criteria.add(Criteria.where("votes").gte(minVotes));
        }
        if (minYear != null) {
            criteria.add(Criteria.where("year").gte(minYear));
        }
        if (genres != null) {
            List<String> matchedGenres = genres.stream()
                    .filter(genre -> !FuzzyMatcher.genreBestMatch(genre.toUpperCase()).equals(""))
                    .map(genre -> FuzzyMatcher.genreBestMatch(genre.toUpperCase()))
                    .collect(toList());
            criteria.add(Criteria.where("genres").all(matchedGenres));
        }

        org.springframework.data.mongodb.core.query.Query query = new Query();
        if (!criteria.isEmpty()) {
            Criteria criterion = new Criteria();
            criterion.andOperator(criteria);
            query.addCriteria(criterion);
        }
        if (top != null) {
            query.limit(top);
        }
        if (sort != null) {
            query.with(sort);
        }

        return query;
    }
}
