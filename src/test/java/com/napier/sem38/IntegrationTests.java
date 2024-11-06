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
    static Database _database;
    static CountryList _countryList;

    @BeforeAll
    public static void Initialise()
    {
        _database = new Database();
        _database.Connect("localhost:33060", 30000);

        _countryList = new CountryList(_database);
    }

    @Test
    void CountryWorldListTest()
    {
        List<String> result = _countryList.GetWorldList();

        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        List<String> expected = new ArrayList<>();
        expected.add("China - Population: 1277558000");
        expected.add("India - Population: 1013662000");
        expected.add("United States - Population: 278357000");

        assertEquals(expected, actual);
    }
}
