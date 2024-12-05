package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * class used to store methods for getting and displaying lists of countries
 */
public class CountryList
{
    // field to store our reference to the database
    Database _database;

    /**
     * Class Constructor
     * Assigns the database.
     * @param database reference to the database
     */
    public CountryList(Database database)
    {
        // store the database reference
        _database = database;
    }

    //region World Functions

    /**
     * Queries the database to find countries in the world sorted by population.
     * Stores the results in the array list variable.
     * @return the list of countries.
     */
    public List<String> GetWorldList()
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // set up our query statement
        String query =
                "SELECT Name, Population " +
                        "FROM country " +
                        "ORDER BY Population DESC;";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                // add each country to the list
                list.add(results.getString("Name") +
                        " - Population: " +
                        results.getString("Population"));
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
     * Queries the database to find the top n most populated countries in the world.
     * Stores the results in an array list.
     * @param n number we list up to
     * @return the list of countries.
     */
    public List<String> GetWorldList(int n)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // set up our query statement
        String query =
                "SELECT Name, Population " +
                        "FROM country " +
                        "ORDER BY Population DESC LIMIT " + n;

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                list.add(results.getString("Name") +
                        " - Population: " +
                        results.getString("Population"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return list;
    }

    //endregion

    //region Continent

    /**
     * Queries the database to find countries in a continent sorted by population.
     * Stores the results in the array list variable.
     * @param continent the continent to search.
     * @return list of countries
     */
    public List<String> GetContinentList(String continent)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get countries in a continent ordered by population
        String query = "SELECT Name, Population " +
                "FROM country " +
                "WHERE Continent = '" + continent + "' " +
                "ORDER BY Population DESC";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {

            // this moves through the results until there are no more
            while (results.next())
            {
                // add each country to the list
                list.add(results.getString("Name") +
                        " - Population: " +
                        results.getString("Population"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return list;
    }

    //endregion

    //region Region

    /**
     * Queries the database to find countries in a region sorted by population.
     * Stores the results in the array list variable.
     * @param region the region we are searching
     * @return list of countries
     */
    public List<String> GetRegionList(String region)
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get countries in a continent ordered by population
        String query = "SELECT Name, Population " +
                "FROM country " +
                "WHERE Region = '" + region + "' " +
                "ORDER BY Population DESC";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {

            // this moves through the results until there are no more
            while (results.next())
            {
                // add each country to the list
                list.add(results.getString("Name") +
                        " - Population: " +
                        results.getString("Population"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return list;
    }

    /**
     * Get the top N populated countries in a specific continent.
     * @param continent the continent to search
     * @param n the number of top populated countries to return
     * @return a list of top N populated countries in the continent
     */
    public List<String> GetTopNPopulatedCountriesInContinent(String continent, int n)
    {
        // Initialize the return list
        List<String> list = new ArrayList<>();

        // SQL query to get top N populated countries in a continent
        String query = "SELECT Name, Population " +
                "FROM country " +
                "WHERE Continent = '" + continent + "' " +
                "ORDER BY Population DESC " +
                "LIMIT " + n;

        // Execute the query
        ResultSet results = _database.Query(query);

        try
        {
            // Iterate through the results
            while (results.next())
            {
                String country = results.getString("Name");
                int population = results.getInt("Population");
                list.add(country + " - Population: " + population);
            }
        }
        catch (Exception exception)
        {
            // Handle errors
            System.out.println("Error: " + exception.getMessage());
        }

        return list;
    }

    /**
     * Queries the database to find the top N most populated countries in a region.
     * @param region the region to search
     * @param n the number of countries to retrieve
     * @return a list of the top N populated countries in the region
     */
    public List<String> GetTopNPopulatedCountriesInRegion(String region, int n) {
        // Initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get the top N populated countries in a region
        String query = "SELECT Name, Population " +
                "FROM country " +
                "WHERE Region = '" + region + "' " +
                "ORDER BY Population DESC " +
                "LIMIT " + n;

        // Execute the query
        ResultSet results = _database.Query(query);

        try {
            // Iterate through the results
            while (results.next()) {
                // Add each country to the list
                String countryName = results.getString("Name");
                int population = results.getInt("Population");
                list.add(countryName + " - Population: " + population);
            }
        } catch (Exception exception) {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        return list;
    }


    //endregion
}
