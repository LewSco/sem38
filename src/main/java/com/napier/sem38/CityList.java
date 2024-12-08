package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityList
{
    Database _database;

    /**
     * Class Constructor
     * Assigns the database and instantiates list.
     * @param database reference to database
     */
    public CityList(Database database)
    {
        // store the database reference
        _database = database;
    }

    /**
     * Queries the database to find cities in the world sorted by population.
     * Stores the results in the array list variable.
     * @return the list of cities.
     */
    public List<String> GetWorldList()
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // set up our query statement
        String query =
                "SELECT Name " +
                        "FROM city " +
                        "ORDER BY Population DESC;";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                // add each city to the list
                list.add(results.getString("Name"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Produce a list of all the cities in a region organized by largest population to smallest.
     * @param region the region to search
     * @return a list of the cities largest to small in population
     */

    //Split these into two different functions, one getting and storing the country
    public List<String> citiesInRegionLargetoSmall(String region)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get cities in a region ordered by population, joining city and country tables
        String query = "SELECT city.Name " +
                "FROM country " +
                "JOIN city ON country.Code = city.CountryCode " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Produce a list of all the cities in a continent organized by largest population to smallest
     * @param continent the continent to search
     * @return a list of the cities largest to small in population
     */
    public List<String> citiesInContinentLargetoSmall(String continent)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get cities in a continent ordered by population, joining city and country tables
        String query = "SELECT city.Name, city.Population " +
                "FROM country " +
                "JOIN city ON country.Code = city.CountryCode " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC;";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * a function for listing the world's capital cities largest to smallest in population up to specified number.
     * @param n the number of cities the list will display.
     * @return a list of the cities largest to small in population.
     */
    public List<String> GetTopNPopulatedCapitals(int n)
    {
        List<String> list = new ArrayList<>();

        // SQL query to get the top N populated capital cities
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting a list of all capital cities, sorted by largest to smallest population
     * @return a list of capital cities, largest to smallest pop.
     */
    public List<String> CapitalMostLeastPop()
    {
        List<String> list = new ArrayList<>();

        // SQL query to get capital cities and sort by population
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top N populated cities in a provided district
     * @return a list of top N populated cities from specified district
     */
    public List<String> TopNPopCitiesInDistrict(int n, String district)
    {
        List<String> list = new ArrayList<>();

        // Get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "WHERE city.District = '" + district + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top N populated cities in a provided continent
     * @return a list of top N populated cities from specified continent
     */
    public List<String> TopNPopCitiesInContinent(int n, String continent)
    {
        List<String> list = new ArrayList<>();

        // Get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON country.Code = city.CountryCode " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top N populated cities in a provided country
     * @return a list of top N populated cities from specified country
     */
    public List<String> TopNPopCitiesInCountry(int n, String country)
    {
        List<String> list = new ArrayList<>();

        // get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "WHERE city.CountryCode = (SELECT Code FROM country WHERE Name = '" + country + "') " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top n populated capital cities on a continent
     * @return a list of top n populated capital cities on a continent
     */
    public List<String> TopNPopCapitalInContinent(int n, String continent)
    {
        List<String> list = new ArrayList<>();

        // get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "WHERE city.ID IN (SELECT Capital FROM country WHERE Continent = '" + continent + "') " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top n populated capital cities on a region
     * @return a list of top n populated capital cities on a region
     */
    public List<String> TopNPopCapitalInRegion(int n, String region)
    {
        List<String> list = new ArrayList<>();

        // get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "WHERE city.ID IN (SELECT Capital FROM country WHERE Region = '" + region + "') " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Function for getting top n populated cities in a region
     * @return a list of top n populated cities in a region
     */
    public List<String> TopNPopCitiesInRegion(int n, String region)
    {
        List<String> list = new ArrayList<>();

        // get string for sql query
        String query = "SELECT city.Name " +
                "FROM city " +
                "WHERE city.CountryCode IN (SELECT Code FROM country WHERE Region = '" + region + "') " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * a function for listing the cities largest to smallest in population up to specified number.
     * @param n the number of cities the list will display.
     * @return a list of the cities largest to small in population.
     */
    public List<String> GetTopNPopulatedCities(int n)
    {
        List<String> list = new ArrayList<>();

        // SQL query to get the top N populated cities
        String query = "SELECT city.Name " +
                "FROM city " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                list.add(results.getString("Name"));
            }

        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Queries the database to find all the capital cities in a region organized by population (largest to smallest).
     * @param region the region to search
     * @return a list of capital cities in the specified region, organized by population
     */
    public List<String> GetCapitalCitiesInContinentLargetoSmall(String continent)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get capital cities in a region ordered by population
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.continent = '" + continent + "' " +
                "ORDER BY city.Population DESC";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Queries the database to find all the capital cities in a region organized by population (largest to smallest).
     * @param region the region to search
     * @return a list of capital cities in the specified region, organized by population
     */
    public List<String> GetCapitalCitiesInRegionLargetoSmall(String region)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get capital cities in a region ordered by population
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Produce a list of all the cities in a district organized by largest population to smallest.
     * @param district the district to search
     * @return a list of the cities largest to small in population
     */
    public List<String> citiesInDistrictLargetoSmall(String district) {
        // Initialize the return list
        List<String> list = new ArrayList<>();

        // SQL query to get cities in a district ordered by population
        String query = "SELECT Name " +
                "FROM city " +
                "WHERE District = '" + district + "' " +
                "ORDER BY Population DESC;";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try {
            // Iterate through the results
            while (results.next()) {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        } catch (Exception exception) {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }

    /**
     * Produces a list of all the cities in a country organized by largest population to smallest.
     * @param country the name of the country to search
     * @return a list of the cities in the country sorted by population
     */
    public List<String> citiesInCountryLargetoSmall(String country)
    {
        // Initialize the return list
        List<String> list = new ArrayList<>();

        // SQL query to get cities in a country ordered by population
        String query = "SELECT city.Name " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = '" + country + "' " +
                "ORDER BY city.Population DESC";

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                list.add(results.getString("Name"));
            }
        }
        catch (Exception exception)
        {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }
}
