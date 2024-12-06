package com.napier.sem38;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Array;
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

    //region Main

    /**
     * runs main without arguments
     */
    /*
    @Test
    void RunMainWithEmptyArguments()
    {
        String[] args = new String[0];
        _main.main(args);
    }*/

    /**
     * runs main with arguments
     *//*
    @Test
    void RunMainWithArguments()
    {
        String[] args = new String[] { "localhost:33060", "30000" };
        _main.main(args);
    }*/

    //endregion

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
        expected.add("China");
        expected.add("India");
        expected.add("United States");

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
        expected.add("China");
        expected.add("India");

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
        expected.add("China");
        expected.add("India");
        expected.add("Indonesia");
        expected.add("Pakistan");
        expected.add("Bangladesh");

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
        expected.add("Cuba");
        expected.add("Dominican Republic");
        expected.add("Haiti");

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
        expected.add("Seoul");
        expected.add("Jakarta");
        expected.add("Ciudad de México");

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
        expected.add("La Habana");
        expected.add("Santo Domingo de Guzmán");
        expected.add("Port-au-Prince");
        expected.add("San Juan");
        expected.add("Santiago de Cuba");

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
        expected.add("Mumbai (Bombay)");
        expected.add("Seoul");
        expected.add("São Paulo");

        // check actual = expected
        assertEquals(expected, actual);
    }

    //endregion

    //region Population

    /**
     * testing get population with an empty query
     */
    @Test
    void PopulationEmptyQuery()
    {
        _populationSum.GetPopulation("");
    }

    /**
     * testing get population with a null query
     */
    @Test
    void PopulationNullQuery()
    {
        _populationSum.GetPopulation(null);
    }

    /**
     * testing get population with a bad syntax query
     */
    @Test
    void PopulationBadSyntaxQuery()
    {
        _populationSum.GetPopulation(
                "Select SUM(Population) " +
                        "FROM world " +
                        "WHERE population = NULL;");
    }

    /**
     * test checks GetWorldPop() returns the expected results
     */
    @Test
    void WorldPop()
    {
        //Get the world population
        Long actual = _populationSum.WorldPop();

        //Expected output for the world population
        Long expected = 6078749450L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * test checks GetContinentPop() returns the expected results
     */
    @Test
    void ContinentPopExpected()
    {
        //Get the continent population
        Long actual = _populationSum.ContinentPop("Asia");

        //Expected output for the continent population
        Long expected = 3705025700L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a continent population
     */
    @Test
    void ContinentPopInvalidInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop("The Roman Empire");
    }

    /**
     * tests a null input to get a continent population
     */
    @Test
    void ContinentPopNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop(null);
    }

    /**
     * tests an empty input to get a continent population
     */
    @Test
    void ContinentPopEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop("");
    }


    /**
     * test checks GetCountryPop() returns the expected results
     */
    @Test
    void CountryPopExpected()
    {
        //Get the Country population
        Long actual = _populationSum.CountryPop("France");

        //Expected output for the Country population
        Long expected = 59225700L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a country population
     */
    @Test
    void CountryPopInvalidInput()
    {
        // Get the continent population
        Long actual = _populationSum.CountryPop("Jamie's dad's car");
    }

    /**
     * tests a null input to get a country population
     */
    @Test
    void CountryPopNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.CountryPop(null);
    }

    /**
     * tests an empty input to get a country population
     */
    @Test
    void CountryPopEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.CountryPop("");
    }

    /**
     * test checks GetRegionPop() returns the expected results
     */
    @Test
    void RegionPopExpected()
    {
        //Get the Region population
        Long actual = _populationSum.RegionPop("Caribbean");

        //Expected output for the Region population
        Long expected = 38140000L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a region population
     */
    @Test
    void RegionPopInvalidInput()
    {
        // Get the continent population
        Long actual = _populationSum.RegionPop("the dish washer");
    }

    /**
     * tests a null input to get a region population
     */
    @Test
    void RegionPopNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.RegionPop(null);
    }

    /**
     * tests an empty input to get a region population
     */
    @Test
    void RegionPopEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.RegionPop("");
    }

    /**
     * test checks GetRegionPop() returns the expected results
     */
    @Test
    void DistrictPopExpected()
    {
        //Get the District population
        Long actual = _populationSum.DistrictPop("Texas");

        //Expected output for the District population
        Long expected = 9208281L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a district population
     */
    @Test
    void DistrictPopInvalidInput()
    {
        // Get the continent population
        Long actual = _populationSum.DistrictPop("the dish washer");
    }

    /**
     * tests a null input to get a district population
     */
    @Test
    void DistrictPopNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.DistrictPop(null);
    }

    /**
     * tests an empty input to get a district population
     */
    @Test
    void DistrictPopEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.DistrictPop("");
    }

    //endregion

    //region Reports

    /**
     * Test checks if the correct report is returned for the supplied country name.
     */
    @Test
    void CountryReport()
    {
        var actual = _report.CountryReport("Belarus");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("BLR");
        expected.add("Belarus");
        expected.add("Europe");
        expected.add("Eastern Europe");
        expected.add("10236000");
        expected.add("Minsk");

        assertEquals(expected, actual);
    }

    /**
     * Test checks if the correct report is returned for the supplied country name.
     */
    @Test
    void CityReport()
    {
        // get report
        List<String> actual = _report.CityReport("Petare");
        // set expected result
        List<String> expected = new ArrayList<>();
        expected.add("Petare");
        expected.add("Venezuela");
        expected.add("Miranda");
        expected.add("488868");

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

    /**
     * tests the country report with an empty query
     */
    @Test
    void GetCountryReportEmptyQuery()
    {
        _report.GetCountryReport("");
    }

    /**
     * tests the city report with an empty query
     */
    @Test
    void GetCityReportEmptyQuery()
    {
        _report.GetCityReport("");
    }

    /**
     * tests the capital report with an empty query
     */
    @Test
    void GetCapitalReportEmptyQuery()
    {
        _report.GetCapitalReport("");
    }
    //endregion

    /**
     * tests citiesInRegionLaregtoSmall with Invalid Data to test the catch exception
     */
    @Test
    void citiesInRegionLargetoSmallInvalidRegionTest()
    {
        // Provide a non-existent region to trigger the catch block
        String invalidRegion = "MadeUpRegion";

        // Attempt to get cities in the non-existent region
        List<String> result = _cityList.citiesInRegionLargetoSmall(invalidRegion);

        // Assert that the list is empty because the region doesn't exist
        assertNotNull(result); // Ensure result is not null
        assertTrue(result.isEmpty(), "The result should be empty for a non-existent region");
    }

    /**
     * test checks CityPopEpected() returns the expected results
     */
    @Test
    void CityPopExpected()
    {
        //Get the Region population
        Long actual = _populationSum.CityPop("Edinburgh");

        //Expected output for the Region population
        Long expected = 450180L;

        //check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a city population
     */
    @Test
    void CityPopInvalidInput()
    {
        // Get the continent population
        Long actual = _populationSum.CityPop("Shangrla");
    }

    /**
     * tests a null input to get a city population
     */
    @Test
    void CityPopNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop(null);
    }

    /**
     * tests an empty input to get a continent population
     */
    @Test
    void CityPopEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop("");
    }

    /**
     * test checks GetTopNPopulatedCities works as intended with no params
     */
    @Test
    void GetTopNPopulatedCitiesExpected()
    {
        // get function output
        List<String> result = _cityList.GetWorldList();

        // store top 4 countries
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));
        actual.add(result.get(3));

        // set expected output
        List<String> expected = new ArrayList<>();
        expected.add("Mumbai (Bombay)");
        expected.add("Seoul");
        expected.add("São Paulo");
        expected.add("Shanghai");

        // check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a city population
     */
    @Test
    void GetTopNPopulatedCitiesInput()
    {
        // Get the continent population
        Long actual = _populationSum.CityPop("Hello");
    }

    /**
     * tests a null input to get a city population
     */
    @Test
    void GetTopNPopulatedCitiesNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop(null);
    }

    /**
     * tests an empty input to get a continent population
     */
    @Test
    void GetTopNPopulatedCitiesEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop("");
    }

    /**
     * test checks GetCapitalCitiesInRegionLargetoSmallTest works with the expected results
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallTest()
    {
        // Provide a valid region for the test
        String region = "Caribbean";

        // Get the result from the method
        List<String> result = _cityList.GetCapitalCitiesInRegionLargetoSmall(region);

        // Check that the result is not empty and contains expected data
        assertNotNull(result, "The result should not be null");
        assertTrue(result.size() > 0, "The result should contain data");

        // Store actual output for comparison
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));

        // Expected output for verification
        List<String> expected = new ArrayList<>();
        expected.add("La Habana");
        expected.add("Santo Domingo de Guzmán");

        // Verify actual matches expected
        assertEquals(expected, actual);
    }

    /**
     * tests an invalid input to get a city population
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallTestInput()
    {
        // Get the continent population
        Long actual = _populationSum.CityPop("Yellow");
    }

    /**
     * tests a null input to get a city population
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallTestNullInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop(null);
    }

    /**
     * tests an empty input to get a continent population
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallTestEmptyInput()
    {
        // Get the continent population
        Long actual = _populationSum.ContinentPop("");
    }


    /**
     * tests to see if we get the expected results of the CitiesInDistrictLargetoSmallTest
     */
    @Test
    void CitiesInDistrictLargetoSmallTest() {
        // Get function output
        String district = "Texas";
        List<String> result = _cityList.citiesInDistrictLargetoSmall(district);
        ArrayList<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // Ensure the result is not empty
        assertNotNull(result);
        assertTrue(result.size() > 0, "The result should contain at least one city.");

        // Example expected output for validation
        List<String> expected = new ArrayList<>();
        expected.add("Houston");
        expected.add("Dallas");
        expected.add("San Antonio");

        // Validate the expected results with the actual
        assertEquals(expected, actual);
    }

    /**
     * Tests an invalid input to get capital cities in a region.
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallInvalidInput()
    {
        // Attempt to get cities for an invalid region name
        List<String> result = _cityList.citiesInRegionLargetoSmall("Smelly");

    }

    /**
     * Tests a null input to get capital cities in a region.
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallNullInput()
    {
        // Attempt to get cities with a null region
        List<String> result = _cityList.citiesInRegionLargetoSmall(null);
    }

    /**
     * Tests an empty input to get capital cities in a region.
     */
    @Test
    void GetCapitalCitiesInRegionLargetoSmallEmptyInput()
    {
        // Attempt to get cities with an empty string as the region
        List<String> result = _cityList.citiesInRegionLargetoSmall("");
    }

    /**
     * Test to check citiesInCountryLargetoSmall() returns the expected results for a country.
     */
    @Test
    void citiesInCountryLargetoSmallTest()
    {
        // Get function output
        List<String> result = _cityList.citiesInCountryLargetoSmall("United States");

        // Store the top 3 cities
        List<String> actual = new ArrayList<>();
        actual.add(result.get(0));
        actual.add(result.get(1));
        actual.add(result.get(2));

        // Expected output
        List<String> expected = new ArrayList<>();
        expected.add("New York");
        expected.add("Los Angeles");
        expected.add("Chicago");

        // Check actual = expected
        assertEquals(expected, actual);
    }

    /**
     * Test citiesInCountryLargetoSmall() with null input
     */
    @Test
    void citiesInCountryLargetoSmallTestNullInput()
    {
        // Call the function with null input
        List<String> result = _cityList.citiesInCountryLargetoSmall(null);
    }

    /**
     * Test citiesInCountryLargetoSmall() with empty input
     */
    @Test
    void citiesInCountryLargetoSmallTestEmptyInput()
    {
        // Call the function with an empty string
        List<String> result = _cityList.citiesInCountryLargetoSmall("");
    }

    /**
     * Test citiesInCountryLargetoSmall() with an invalid country name
     */
    @Test
    void citiesInCountryLargetoSmallTestInvalidInput()
    {
        // Call the function with an invalid country name
        List<String> result = _cityList.citiesInCountryLargetoSmall("InvalidCountry");
    }

    /**
     * Test to check GetTopNPopulatedCountriesInContinentExpectedResultsTest() returns the expected results for a country.
     */
    @Test
    void GetTopNPopulatedCountriesInContinentExpectedResultsTest()
    {
        // Specify the continent and number of results
        String continent = "Asia";
        int n = 5;

        // Call the function
        List<String> actual = _countryList.GetTopNPopulatedCountriesInContinent(continent, n);

        // Define the expected result
        List<String> expected = new ArrayList<>();
        expected.add("China");
        expected.add("India");
        expected.add("Indonesia");
        expected.add("Pakistan");
        expected.add("Bangladesh");

        // Assert that the actual and expected results are the same
        assertEquals(expected, actual, "The actual list does not match the expected list of top countries in Asia.");
    }

    /**
     * Test to check GetTopNPopulatedCountriesInContinentLargeNTest() is larger than the amount of country
     */
    @Test
    void GetTopNPopulatedCountriesInContinentLargeNTest()
    {
        String continent = "Asia";
        int n = 55;

        List<String> result = _countryList.GetTopNPopulatedCountriesInContinent(continent, n);

        // Result should not exceed the actual number of countries in Asia
        assertNotNull(result);
        assertTrue(result.size() <= 55);
    }

    /**
     * Test to check GetTopNPopulatedCountriesInContinentNullInputTest() if it has a null input
     */
    @Test
    void GetTopNPopulatedCountriesInContinentNullInputTest()
    {
        String continent = null;
        int n = 5;

        List<String> result = _countryList.GetTopNPopulatedCountriesInContinent(continent, n);
    }

    /**
     * Test to check GetTopNPopulatedCountriesInContinentInvalidInputTest() if it has a null input
     */
    @Test
    void GetTopNPopulatedCountriesInContinentInvalidInputTest()
    {
        String continent = "InvalidContinent";
        int n = 5;

        List<String> result = _countryList.GetTopNPopulatedCountriesInContinent(continent, n);

        // Result should be empty
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test to check GetTopNPopulatedCountriesInContinentEmptyInputTest() if it has a null input
     */
    @Test
    void GetTopNPopulatedCountriesInContinentEmptyInputTest()
    {
        // Specify empty input for continent and a valid N
        String continent = "";
        int n = 5;

        List<String> result = _countryList.GetTopNPopulatedCountriesInContinent(continent, n);
    }

    /**
     * Tests that the method retrieves the expected top N populated countries in a region.
     */
    @Test
    void GetTopNPopulatedCountriesInRegionExpectedTest() {
        // Define input
        String region = "Caribbean";
        int n = 5;

        // Call the method
        List<String> result = _countryList.GetTopNPopulatedCountriesInRegion(region, n);

        // Expected output
        List<String> expected = new ArrayList<>();
        expected.add("Cuba");
        expected.add("Dominican Republic");
        expected.add("Haiti");
        expected.add("Puerto Rico");
        expected.add("Jamaica");

        // Assert that the result matches the expected output
        assertEquals(expected, result);
    }

    /**
     * Tests that the method handles an empty input for the region parameter
     */
    @Test
    void GetTopNPopulatedCountriesInRegionEmptyInputTest() {
        List<String> result = _countryList.GetTopNPopulatedCountriesInRegion("", 5);
    }

    /**
     * Tests that the method handles a null input for the region parameter
     */
    @Test
    void GetTopNPopulatedCountriesInRegionNullInputTest()
    {
        // Call the method with null region
        List<String> result = _countryList.GetTopNPopulatedCountriesInRegion(null, 5);
    }

    /**
     * Tests that the method handles an invalid value for the N parameter
     */
    @Test
    void GetTopNPopulatedCountriesInRegionInvalidNTest() {
        // Call the method with invalid N
        List<String> result = _countryList.GetTopNPopulatedCountriesInRegion("Caribbean", -1);
    }

    /**
     * Test to check the total number of Chinese speakers in the world.
     */
    @Test
    void getChineseSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("Chinese");
        Long expected = 1968265500L;
        assertEquals(expected, actual, "The number of Chinese speakers does not match the expected value.");
    }

    /**
     * Test to check the total number of English speakers in the world.
     */
    @Test
    void getEnglishSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("English");
        Long expected = 627418300L;
        assertEquals(expected, actual, "The number of English speakers does not match the expected value.");
    }

    /**
     * Test to check the total number of Hindi speakers in the world.
     */
    @Test
    void getHindiSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("Hindi");
        Long expected = 1046303000L;
        assertEquals(expected, actual, "The number of Hindi speakers does not match the expected value.");
    }

    /**
     * Test to check the total number of Spanish speakers in the world.
     */
    @Test
    void getSpanishSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("Spanish");
        Long expected = 750296800L;
        assertEquals(expected, actual, "The number of Spanish speakers does not match the expected value.");
    }

    /**
     * Test to check the total number of Arabic speakers in the world.
     */
    @Test
    void getArabicSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("Arabic");
        Long expected = 552045100L;
        assertEquals(expected, actual, "The number of Arabic speakers does not match the expected value.");
    }

    /**
     * Test to check behavior for a language not in the database.
     */
    @Test
    void getNonExistentLanguageSpeakersTest() {
        Long actual = _populationSum.getLanguageSpeakers("Klingon");
        Long expected = 0L;
        assertEquals(expected, actual, "The number of speakers for a non-existent language should be 0.");
    }

    /**
     * Test to check behavior for null input.
     */
    @Test
    void getLanguageSpeakersNullTest() {
        Long actual = _populationSum.getLanguageSpeakers(null);
        Long expected = 0L;
        assertEquals(expected, actual, "The result for null input should be 0.");
    }

    /**
     * Test to check behavior for empty input.
     */
    @Test
    void getLanguageSpeakersEmptyTest() {
        Long actual = _populationSum.getLanguageSpeakers("");
        Long expected = 0L;
        assertEquals(expected, actual, "The result for empty input should be 0.");
    }
}
