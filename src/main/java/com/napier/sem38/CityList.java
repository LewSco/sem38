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
     * Produce a list of all the cities in a region organized by largest population to smallest
     */
    public List<String> citiesInRegionLargetoSmall(String region)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get cities in a region ordered by population, joining city and country tables
        String query = "SELECT city.Name, city.Population " +
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
                String city = results.getString("Name");
                int population = results.getInt("Population");
                list.add(city + " - Population: " + population);
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
                "ORDER BY city.Population DESC";

        // Get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                // Add city name and population to the list
                String city = results.getString("Name");
                int population = results.getInt("Population");
                list.add(city + " - Population: " + population);
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

    public List<String> GetTopNPopulatedCapitals(int n) {
        List<String> list = new ArrayList<>();

        // SQL query to get the top N populated capital cities
        String query = "SELECT city.Name, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        // Execute the query and retrieve results
        ResultSet results = _database.Query(query);

        try {
            // Iterate through the results
            while (results.next()) {
                // Get the city name and population, and add it to the list
                String cityName = results.getString("Name");
                int population = results.getInt("Population");
                list.add(cityName + " - Population: " + population);
            }
        } catch (Exception exception) {
            // Print error messages if any
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }
}
