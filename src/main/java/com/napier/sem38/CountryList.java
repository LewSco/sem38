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

    //endregion

    //region Country Report

    /**
     * Queries the database to find code, name, continent, region,
     * population and capital of specified country.
     * Stores the results in the array list variable.
     */
    public String CountryReport(String countryName)
    {
        // initialise the return list
        String report = "";

        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Name = '" + countryName  + "';";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {

            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                report =
                        "\n \t" + "Code: " + results.getString("Code") +
                        "\n \t" + "Name: " + results.getString("Name") +
                        "\n \t" + "Continent: " + results.getString("Continent") +
                        "\n \t" + "Region: " + results.getString("Region") +
                        "\n \t" + "Population: " + results.getString("Population") +
                        "\n \t" + "Capital: " + results.getString("Capital")
                        ;
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return report;
    }

    public List<String> CountryReport()
    {
        // initialise the return list
        List<String> list = new ArrayList<>();

        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID;";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {

            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                list.add(
                        "\n \t" + "Code: " + results.getString("Code") +
                                "\n \t" + "Name: " + results.getString("Name") +
                                "\n \t" + "Continent: " + results.getString("Continent") +
                                "\n \t" + "Region: " + results.getString("Region") +
                                "\n \t" + "Population: " + results.getString("Population") +
                                "\n \t" + "Capital: " + results.getString("Capital")
                );
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
}
