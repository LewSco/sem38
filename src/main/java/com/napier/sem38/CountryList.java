package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryList
{
    List<String> _list; // list for storing the countries
    Database _database;


    // class constructor
    public CountryList(Database database)
    {
        // we initialise the list
        _list = new ArrayList<>();
        _database = database; // store the database reference
    }


    // function for getting the list of countries in the world ordered for population
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

    // display the list
    public void Display()
    {

        System.out.println("List of countries:");

        // seems like the java equivalent of a foreach loop
        for (String country : _list) // foreach (String country in _list)
        {
            System.out.println(country);  // Console.WriteLine(country);
        }
    }
}
