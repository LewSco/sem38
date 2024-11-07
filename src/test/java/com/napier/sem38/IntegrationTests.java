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
    static CityList _cityList;
    static PopulationSum _populationSum;

    /**
     * initialise the database and classes for testing
     */
    @BeforeAll
    public static void Initialise()
    {
        _database = new Database();
        _database.Connect("localhost:33060", 30000);

        _countryList = new CountryList(_database);
        _cityList = new CityList(_database);
        _populationSum = new PopulationSum(_database);
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
    /**
     * test checks GetWorldList() with limits returns the expected number of countries that are searched for
     */
    @Test
    void GetWorldListWithLimitTest()
    {
        // get function output
        List<String> result = _countryList.GetWorldList(2);

        // store top N (2) countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("China - Population: 1277558000");
        expected.add("India - Population: 1013662000");

        // check actual = expected
        assertEquals(expected, actual);

    }

    /**
     * test checks GetContinentList() with limit returns the expected countries that are searched for in Asia
     */
    @Test
    void GetContinentList()
    {
        // get function output
        List<String> result = _countryList.GetContinentList("Asia");

        // store top 5 countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));
        actual.add(result.get(3));
        actual.add(result.get(4));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("China - Population: 1277558000");
        expected.add("India - Population: 1013662000");
        expected.add("Indonesia - Population: 212107000");
        expected.add("Pakistan - Population: 156483000");
        expected.add("Bangladesh - Population: 129155000");

        // check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * test checks GetRegionList() function works as intended
     */
    @Test
    void GetRegionList()
    {
        // get function output
        List<String> result = _countryList.GetRegionList("Caribbean");

        // store top 4 countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("Cuba - Population: 11201000");
        expected.add("Dominican Republic - Population: 8495000");
        expected.add("Haiti - Population: 8222000");

        // check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * test checks GetTopNPopulatedCapitals() returns the expected number of capital cities.
     */
    @Test
    void GetTopNPopulatedCapitalsTest()
    {
        //get function output
        List<String> result = _cityList.GetTopNPopulatedCapitals(3);

        // store top 3 Capital Cities
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("Seoul - Population: 9981619");
        expected.add("Jakarta - Population: 9604900");
        expected.add("Ciudad de México - Population: 8591309");

        // check actual = expected
        assertEquals(expected, actual);

    }

    /**
     * test checks CitiesInRegionLargetoSmallTest() returns the expected number of capital cities.
     */
    @Test
    void CitiesInRegionLargetoSmallTest()
    {
        //get function output
        List<String> result = _cityList.citiesInRegionLargetoSmall("Caribbean");

        // store top 3 Capital Cities
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));
        actual.add(result.get(3));
        actual.add(result.get(4));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("La Habana - Population: 2256000");
        expected.add("Santo Domingo de Guzmán - Population: 1609966");
        expected.add("Port-au-Prince - Population: 884472");
        expected.add("San Juan - Population: 434374");
        expected.add("Santiago de Cuba - Population: 433180");

        // check actual = expected
        assertEquals(expected, actual);

    }

    /**
     * test checks CitiesInRegionLargetoSmallTest() returns the expected number of cities.
     */
    @Test
    void citiesInContientLargetoSmallTest()
    {
        //get function output
        List<String> result = _cityList.citiesInRegionLargetoSmall("Asia");

        if(result.size() < 3)
        {
            fail("not enought data returned from db");
        }

        // store top 3 Cities in Asia
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("Mumbai (Bombay) - Population: 10500000");
        expected.add("Seoul - Population: 9981619");
        expected.add("Shanghai  - Population: 9696300");

        // check actual = expected
        assertEquals(expected, actual);

    }

    /**
     * test checks GetWorldPop() returns the expected results
     */
    @Test
    void GetWorldPop()
    {
        //Get the world population
        Long actual = _populationSum.GetWorldPop();

        //Expected output for the world population
        Long expected = 6078749450L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * test checks GetContinentPop() returns the expected results
     */
    @Test
    void GetContinentPop()
    {
        //Get the world population
        Long actual = _populationSum.GetContinentPop("Asia");

        //Expected output for the continent population
        Long expected = 3705025700L;

        //check actual = expected
        assertEquals(expected, actual);
    }
    /**
     * test checks GetCountryPop() returns the expected results
     */
    @Test
    void GetCountryPop()
    {
        //Get the Country population
        Long actual = _populationSum.GetCountryPop("France");

        //Expected output for the Country population
        Long expected = 59225700L;

        //check actual = expected
        assertEquals(expected, actual);
    }
    /**
     * test checks GetRegionPop() returns the expected results
     */
    @Test
    void GetRegionPop()
    {
        //Get the Region popualtion
        Long actual = _populationSum.GetRegionPop("Caribbean");

        //Expected output for the Region population
        Long expected = 38140000L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * test checks GetRegionPop() returns the expected results
     */
    @Test
    void GetDistrictPop()
    {
        //Get the District popualtion
        Long actual = _populationSum.GetDistrictPop("Texas");

        //Expected output for the District population
        Long expected = 9208281L;

        //check actual = expected
        assertEquals(expected, actual);
    }

}
