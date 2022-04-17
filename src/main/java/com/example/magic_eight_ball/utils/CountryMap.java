package com.example.magic_eight_ball.utils;

import java.util.HashMap;
import java.util.Locale;

public class CountryMap {
    public static HashMap<String, String> countries;

    public CountryMap() {
        countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(iso, l.getDisplayCountry().toUpperCase());
        }
    }

    public static String getNameFromISO(String iso) {
        return countries.get(iso.toUpperCase());
    }
}
