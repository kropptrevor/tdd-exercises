package com.example.test.search.city;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.example.search.city.CitySearch;

import org.junit.Before;
import org.junit.Test;

public class SearchCityTest {

    @Before
    public void setup() {
        citySearch = new CitySearch();
    }

    CitySearch citySearch;

    @Test
    public void tooShortInput() {
        List<String> results = citySearch.search("");
        assertNotNull(results);
        assertEquals(0, results.size());

        results = citySearch.search("V");
        assertNotNull(results);
        assertEquals(0, results.size());
    }

    @Test
    public void searchExact() {
        List<String> results = citySearch.search("Vancouver");
        assertNotNull(results);
        assertArrayEquals(new String[] { "Vancouver" }, results.toArray());
    }

    @Test
    public void searchExactCaseInsensitive() {
        List<String> results = citySearch.search("vancouver");
        assertNotNull(results);
        assertArrayEquals(new String[] { "Vancouver" }, results.toArray());
    }

    @Test
    public void wildcard() {
        List<String> results = citySearch.search("*");
        assertNotNull(results);
        assertArrayEquals(
                "Paris, Budapest, Skopje, Rotterdam, Valencia, Vancouver, Amsterdam, Vienna, Sydney, New York City, London, Bangkok, Hong Kong, Dubai, Rome, Istanbul"
                        .split(", "),
                results.toArray());
    }

    @Test
    public void partial() {
        List<String> results = citySearch.search("ancouver");
        assertNotNull(results);
        assertArrayEquals(new String[] { "Vancouver" }, results.toArray());
    }

    @Test
    public void multipleCities() {
        List<String> results = citySearch.search("dam");
        assertNotNull(results);
        assertArrayEquals(new String[] { "Rotterdam", "Amsterdam" }, results.toArray());
    }

}
