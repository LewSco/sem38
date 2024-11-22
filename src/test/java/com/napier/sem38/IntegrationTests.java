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
    static Main _main;
    static Database _database;
    static CountryList _countryList;
    static CityList _cityList;
    static PopulationSum _populationSum;
    static Report _report;

    /**
     * initialise the database and classes for testing
     */
    @BeforeAll
    public static void Initialise()
    {
        _main = new Main();

        _database = new Database();
        _database.Connect("localhost:33060", 30000);

        _countryList = new CountryList(_database);
        _cityList = new CityList(_database);
        _populationSum = new PopulationSum(_database);
        _report = new Report(_database);

    }

    /**
     * added running main to integration testing
     */
    @Test
    void RunMain()
    {
        String[] args = new String[0];
        _main.main(args);
    }

    //region Country

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
    void CountryWorldListWithLimitTest()
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

    //endregion

    //region City

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
    void CitiesInRegionTest()
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
    void CitiesInContientTest()
    {
        String continent = "Asia";
        List<String> cities = _cityList.citiesInContinentLargetoSmall(continent);

        // Check that the list is not empty and has expected values
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertTrue(cities.get(0).contains("Population:"));

    }

    @Test
    void CityWorldListTest()
    {
        // get function output
        List<String> result = _cityList.GetWorldList();


        // store top 3 countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("Mumbai (Bombay) - Population: 10500000");
        expected.add("Seoul - Population: 9981619");
        expected.add("São Paulo - Population: 9968485");

        // check actual = expected
        assertEquals(expected, actual);
    }

    //endregion

    //region Population

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

    //endregion

    //region Reports

    /**
     * Test checks if the correct report is returned for the supplied country name.
     */
    @Test
    void CountryReport()
    {
        String actual = _report.CountryReport("Belarus");
        String expected =
                "\n\tCode: BLR" +
                        "\n\t" + "Name: Belarus" +
                        "\n\t" + "Continent: Europe" +
                        "\n\t" + "Region: Eastern Europe" +
                        "\n\t" + "Population: 10236000" +
                        "\n\t" + "Capital: Minsk"
                ;

        assertEquals(expected, actual);
    }

    /**
     * Test checks if the correct report is returned for the supplied country name.
     */
    @Test
    void CityReport()
    {
        // get report
        String actual = _report.CityReport("Petare");
        // set expected result
        String expected =
                "\n\t" + "Name: Petare" +
                        "\n\t" + "Country: Venezuela" +
                        "\n\t" + "District: Miranda" +
                        "\n\t" + "Population: 488868";

        // check the actual and expected are equal
        assertEquals(expected, actual);
    }

    /**
     * tests the CapitalReport method of the reports class
     */
    @Test
    void CapitalReport()
    {
        // get report
        String actual = _report.CapitalReport("Brazil");
        // set expected result
        String expected =
                "\n\t" + "Name: Brasília" +
                        "\n\t" + "District: Distrito Federal" +
                        "\n\t" + "Population: 1969868";

        // check the actual and expected are equal
        assertEquals(expected, actual);
    }
    //endregion

}
