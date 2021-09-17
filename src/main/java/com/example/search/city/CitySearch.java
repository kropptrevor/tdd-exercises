package com.example.search.city;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitySearch {

    private final String[] CITIES = new String[] { "Paris", "Budapest", "Skopje", "Rotterdam", "Valencia", "Vancouver",
            "Amsterdam", "Vienna", "Sydney", "New York City", "London", "Bangkok", "Hong Kong", "Dubai", "Rome",
            "Istanbul" };

    public List<String> search(String query) {
        List<String> result = new ArrayList<String>();
        if ("*".equals(query)) {
            result = List.of(CITIES);
            return result;
        }
        if (query.length() < 2) {
            return result;
        }
        List<String> cityList = List.of(CITIES);
        result = cityList.stream().filter((city) -> city.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        return result;
    }

}
