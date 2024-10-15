package com.napier.sem38;

import java.sql.*;
import java.util.Scanner;

public class Main {
    /**
     * Main method for the application.
     * Instantiates, runs and displays all of the currently existing classes.
     * @param args
     */
    public static void main(String[] args)
    {
        // Initialize database connection
        Database _database = new Database();

        // Initialize the list
        CountryList _countryList = new CountryList(_database);

        int _number;
        String _name;

        // Connect to database
        _database.connect();

        // Get world list ordered by population
        _countryList.GetWorldList();

        // Display results
        System.out.println("\nList of the worlds countries by population:");
        _countryList.Display();

        // Get world list ordered by population up to n number of countries
        _number = 4;
        _countryList.GetWorldList(_number);

        // Display results
        System.out.println("\nList of the worlds countries by population up to " +_number+ ":");
        _countryList.Display();

        // Get world list ordered by population up to n number of countries
        _name = "Asia";
        _countryList.GetContinentList(_name);

        // Display results
        System.out.println("\nList of countries in " + _name + " by population:");
        _countryList.Display();

        // Disconnect from database
        _database.disconnect();
    }
}
