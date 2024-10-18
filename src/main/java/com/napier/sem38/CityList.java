package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityList
{
    List<String> _list; // list for storing the cities
    Database _database;

    /**
     * Class Constructor
     * Assigns the database and instantiates list.
     * @param database
     */
    public CityList(Database database)
    {
        // we initialise the list
        _list = new ArrayList<>();
        _database = database; // store the database reference
    }
    /**
     * Produce a list of all the cities in a region organized by largest population to smallest
     */

    public void citiesInRegionLargetoSmall(String region)
    {
        // clear the list of any previous queries
        _list.clear();

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
                _list.add(city + " - Population: " + population);
            }
        }

        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }
    }

    /**
     * Displays all elements in the array list (query results if assigned)
     */
    public void Display()
    {
        // Java equivalent of a foreach loop
        for (String city : _list)
        {
            // Print city name and population
            System.out.println(city);
        }
    }
}
