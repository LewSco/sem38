package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryList
{
    List<String> _list; // list for storing the countries
    Database _database;


    public CountryList(Database database)
    {
        // we initialise the list
        _list = new ArrayList<>();
        _database = database;
    }


    public void GetWorldList()
    {
        _list.clear();

        String query =
                "SELECT Name " +
                        "FROM country " +
                        "ORDER BY Population DESC";

        ResultSet results = _database.Query(query);

        try
        {
            while (results.next())
            {
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

    public void Display()
    {
        System.out.println("List of countries:");

        for (String country : _list)
        {
            System.out.println(country);
        }
    }
}
