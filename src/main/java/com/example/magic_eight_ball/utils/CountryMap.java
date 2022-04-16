package com.example.magic_eight_ball.utils;

import java.util.HashMap;
import java.util.Locale;

public class CountryMap {
    private final HashMap<String, String> countries;

    public CountryMap() {
        this.countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            this.countries.put(iso, l.getDisplayCountry().toUpperCase());
        }
    }

    public String getNameFromISO(String iso) {
        return this.countries.get(iso.toUpperCase());
    }
}
