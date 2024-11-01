package com.napier.sem38;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * class for unit testing. For testing individual functions that aren't interacting with other systems
 */
public class UnitTests
{

    static CountryList _countryList;
    static PopulationSum _populationSum;
    static CityList _cityList;

    /**
     * Initialise Method
     * used to initialise the classes for testing
     */
    @BeforeAll
    static void Initialise()
    {
        _countryList = new CountryList();
        _populationSum = new PopulationSum();
        _cityList = new CityList();
    }

    /**
     * Test to check the CountryList display method works with an expected input.
     */
    @Test
    void CountryListDisplayExpectedInput()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _countryList.Display("Test List",
                testList);
    }

    /**
     * Test to check the CountryList display method works with a null listName param.
     */
    @Test
    void CountryListDisplayNameNull()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _countryList.Display(null,
                testList);
    }

    /**
     * Test to check the CountryList display method works with an empty string listName param.
     */
    @Test
    void CountryListDisplayNameEmpty()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _countryList.Display("",
                testList);
    }

    /**
     * Test to check the CountryList display method works with a null list param.
     */
    @Test
    void CountryListDisplayListNull()
    {
        _countryList.Display("Test List",
                null);
    }

    /**
     * Test to check the CountryList display method works with an empty listName param.
     */
    @Test
    void CountryListDisplayListEmpty()
    {
        List<String> testList = new ArrayList<>();
        _countryList.Display("Test List",
                testList);
    }

    /**
     * Test to check the CityList display method works with an expected input.
     */
    @Test
    void CityListDisplayExpectedInput()
    {
        List<String> testList = new ArrayList<>();
        testList.add("City1 with a population of 24 peps");
        testList.add("City2 with a population of 20 peps");
        testList.add("City3 with a population of 12 peps");

        _cityList.Display("Test List",
                testList);
    }

    /**
     * Test to check the CityList display method works with a null listName param.
     */
    @Test
    void CityListDisplayNameNull()
    {
        List<String> testList = new ArrayList<>();
        testList.add("City1 with a population of 24 peps");
        testList.add("City2 with a population of 20 peps");
        testList.add("City3 with a population of 12 peps");

        _cityList.Display(null,
                testList);
    }

    /**
     * Test to check the CityList display method works with an empty string listName param.
     */
    @Test
    void CityListDisplayNameEmpty()
    {
        List<String> testList = new ArrayList<>();
        testList.add("City1 with a population of 24 peps");
        testList.add("City2 with a population of 20 peps");
        testList.add("City3 with a population of 12 peps");

        _cityList.Display("",
                testList);
    }

    /**
     * Test to check the CityList display method works with a null list param.
     */
    @Test
    void CityListDisplayListNull()
    {
        _cityList.Display("Test List",
                null);
    }

    /**
     * Test to check the CityList display method works with an empty listName param.
     */
    @Test
    void CityListDisplayListEmpty()
    {
        List<String> testList = new ArrayList<>();
        _cityList.Display("Test List",
                testList);
    }

    /**
     * Test to check the PopulationSum display method works with expected inputs.
     */
    @Test
    void PopulationSumDisplayExpectedInput()
    {
        _populationSum.Display("Test Sum",
                102313123);
    }

    /**
     * Test to check the PopulationSum display method works with a null sumName param.
     */
    @Test
    void PopulationSumDisplayNameNull()
    {
        _populationSum.Display(null,
                102313123);
    }

    /**
     * Test to check the PopulationSum display method works with an empty sumName param.
     */
    @Test
    void PopulationSumDisplayNameEmpty()
    {
        _populationSum.Display("",
                102313123);
    }

    /**
     * Test to check the PopulationSum display method works with a negative population param.
     */
    @Test
    void PopulationSumDisplayNumberNegative()
    {
        _populationSum.Display("Test Sum",
                -123);
    }

    /**
     * Test to check the PopulationSum display method works with a Zero population param.
     */
    @Test
    void PopulationSumDisplayNumberZero()
    {
        _populationSum.Display("Test Sum",
                0);
    }
}
