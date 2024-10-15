package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryList
{
    List<String> _list; // list for storing the countries
    Database _database;

    /**
     * Class Constructor
     * Assigns the database and instantiates list.
     * @param database
     */
    public CountryList(Database database)
    {
        // we initialise the list
        _list = new ArrayList<>();
        _database = database; // store the database reference
    }

    //region World Functions

    /**
     * Queries the database to find countries in the world sorted by population.
     * Stores the results in the array list variable.
     */
    public void GetWorldList()
    {
        _list.clear(); // clear the list of any previous queries

        // set up our query statement
        String query =
                "SELECT Name " +
                        "FROM country " +
                        "ORDER BY Population DESC";

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                // add each country to the list
                _list.add(results.getString("Name"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

    }

    /**
     * Queries the database to find the top n most populated countries in the world.
     * Stores the results in an array list.
     * @param n
     */
    public void GetWorldList(int n)
    {
        _list.clear(); // clear the list of any previous queries

        // set up our query statement
        String query =
                "SELECT Name " +
                        "FROM country " +
                        "ORDER BY Population DESC LIMIT " + n;

        // get the results from the database
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                // add each country to the list
                _list.add(results.getString("Name"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

    }

    //endregion

    //region Continent

    /**
     * Queries the database to find countries in a continent sorted by population.
     * Stores the results in the array list variable.
     */
    public void GetContinentList(String continent)
    {
        _list.clear(); // clear the list of any previous queries

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
                _list.add(results.getString("Name"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

    }

    //endregion

    //region Region

    /**
     * Queries the database to find countries in a region sorted by population.
     * Stores the results in the array list variable.
     */
    public void GetRegionList(String region)
    {
        _list.clear(); // clear the list of any previous queries

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
                _list.add(results.getString("Name"));
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

    }

    //endregion

    //region Display

    /**
     * Displays all elements in array list (query results if assigned)
     */
    public void Display()
    {
        // seems like the java equivalent of a foreach loop
        for (String country : _list) // foreach (String country in _list)
        {
            System.out.println(country);  // Console.WriteLine(country);
        }
    }

    //endregion
}
