package com.napier.sem38;

import java.sql.*;
import java.util.Scanner;

public class Main {
    /**
     * Main method for the application.
     * Instantiates, runs and displays all of the currently existing classes.
     * @param args
     */
    public static void main(String[] args) {
        Database _database = new Database(); // Initialize database connection
        CountryList _countryList = new CountryList(_database); // Initialize the country list
        TopNPopCountries _topNPopCountries = new TopNPopCountries(_database); // Initialize top populated countries list
        CountientListByPop _countientListByPop = new CountientListByPop(_database); // Initialize continent list

        // Connect to database
        _database.connect();

        // Get world list ordered by population
        _countryList.GetWorldList();

        String line = "";
        while(line == "" || line == null)
        {
            try
            {
                Thread.sleep(1000);
                Scanner sc = new Scanner(System.in);
                line = sc.nextLine();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        System.out.println(line);

        // Get top N populated countries
        _topNPopCountries.GetTopPopCountries(3);

        // Get continent list by population (replace "Asia" with the desired continent)
        _countientListByPop.getCountriesByPopulation("Asia");

        // Display all results
        _countryList.Display();
        _topNPopCountries.Display();
        _countientListByPop.Display();

        // Disconnect from database
        _database.disconnect();
    }
}
