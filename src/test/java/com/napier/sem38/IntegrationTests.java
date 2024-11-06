package com.napier.sem38;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Class for testing how our methods work with the database
 */
public class IntegrationTests
{
    // instances of classes
    static Database _database;
    static CountryList _countryList;

    /**
     * initialise the database and classes for testing
     */
    @BeforeAll
    public static void Initialise()
    {
        _database = new Database();
        _database.Connect("localhost:33060", 30000);

        _countryList = new CountryList(_database);
    }

    /**
     * test checks world list function works as intended with no params
     */
    @Test
    void CountryWorldListTest()
    {
        // get function output
        List<String> result = _countryList.GetWorldList();

        // store top 3 countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("China - Population: 1277558000");
        expected.add("India - Population: 1013662000");
        expected.add("United States - Population: 278357000");

        // check actual = expected
        assertEquals(expected, actual);
    }
}
