package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * class used to store methods for getting and displaying lists of countries
 */
public class CountryList
{
    Database _database;

    /**
     * extra constructor used for unit testing
     */
    public CountryList()
    {

    };

    /**
     * Class Constructor
     * Assigns the database.
     * @param database
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
     * @param n
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

    //region Display

    /**
     * Displays all elements in array list (query results if assigned)
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

    //endregion
}
