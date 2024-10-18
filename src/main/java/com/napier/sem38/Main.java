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
        // initialize database connection
        Database _database = new Database();

        // initialize the list
        CountryList _countryList = new CountryList(_database);
        PopulationList _populationList = new PopulationList(_database);
        CityList _cityList = new CityList(_database);

        // variables for storing names and numbers
        int _number;
        String _name;

        // connect to database
        _database.connect();

        // get world list of countries ordered by population
        _countryList.GetWorldList();

        // display results
        System.out.println("\nList of the worlds countries by population:");
        _countryList.Display();

        // get world list of countries ordered by population up to n number of countries
        _number = 4;
        _countryList.GetWorldList(_number);

        // display results
        System.out.println("\nList of the worlds countries by population up to " +_number+ ":");
        _countryList.Display();

        // get continent list of countries ordered by population
        _name = "Asia";
        _countryList.GetContinentList(_name);

        // display results
        System.out.println("\nList of countries in " + _name + " by population:");
        _countryList.Display();

        // get region list of countries ordered by population
        _name = "Caribbean";
        _countryList.GetRegionList(_name);

        // display the list
        _name = "the " + _name;
        System.out.println("\nList of countries in " + _name + " by population:");
        _countryList.Display();

        //Issue 15 GitHub
        _name = "Caribbean";
        _cityList.citiesInRegionLargetoSmall(_name);

        System.out.println("\nList of cities in " + _name + " by population:");
        _cityList.Display();

        // get and print world population
        System.out.println("World Population: " + _populationList.GetWorldPop());

        // disconnect from database
        _database.disconnect();
    }
}
