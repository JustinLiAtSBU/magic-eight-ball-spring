package com.example.magic_eight_ball.utils;

import com.example.magic_eight_ball.types.Genre;
import org.springframework.util.StringUtils;

public final class FuzzyMatcher {
    static final int ACCEPTANCE_DISTANCE = 2;
    static final double ACCEPTANCE_PERCENT = 0.25;


    public static String genreBestMatch(String seq) {
        String match = "";
        int dist = Integer.MAX_VALUE;
        for (Genre genre: Genre.values()) {
            int curr = levenshtein(seq, genre.toString());
            if (curr <= ACCEPTANCE_DISTANCE && (double)curr/genre.toString().length() <= ACCEPTANCE_PERCENT && curr < dist) {
                dist = curr;
                match = genre.toString();
            }
        }
        return StringUtils.capitalize(match.toLowerCase());
    }

    private static int levenshtein(String s1, String s2) {
        return dist(s1.toCharArray(), s2.toCharArray());
    }

    private static int dist(char[] s1, char[] s2) {
        int[] prev = new int[s2.length + 1];

        for(int j = 0; j < s2.length + 1; j++) {
            prev[j] = j;
        }

        for(int i = 1; i < s1.length + 1; i++) {
            int[] curr = new int[s2.length + 1];
            curr[0] = i;

            for(int j = 1; j < s2.length + 1; j++) {
                int d1 = prev[j] + 1;
                int d2 = curr[j - 1] + 1;
                int d3 = prev[j - 1];
                if (s1[i - 1] != s2[j - 1]) {
                    d3 += 1;
                }
                curr[j] = Math.min(Math.min(d1, d2), d3);
            }

            prev = curr;
        }
        return prev[s2.length];
    }
}
