package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityList
{
    Database _database;

    /**
     * extra constructor used for unit testing
     */
    public CityList()
    {

    };

    /**
     * Class Constructor
     * Assigns the database and instantiates list.
     * @param database
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

    /**
     * Displays all elements in the array list (query results if assigned)
     */
    public void Display(String listName, List<String> list)
    {
        // to catch the errors from testing
        try
        {
            if (listName == null)
                throw new Exception("listName is null");
            if (listName.isEmpty())
                throw new Exception("listName is empty");
            if (list == null)
                throw new Exception("list is null");
            if (list.isEmpty())
                throw new Exception("list is empty");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Display Error: \n" + exception.getMessage());
            return;
        }

        // print the name of the list
        System.out.println(listName + ":");
        // seems like the java equivalent of a foreach loop
        for (String country : list) // foreach (String country in _list)
        {
            System.out.println(country);  // Console.WriteLine(country);
        }
    }
}
