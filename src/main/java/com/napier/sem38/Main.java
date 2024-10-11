package com.napier.sem38;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Database _database = new Database(); // Initialize database connection
        CountryList _countryList = new CountryList(_database); // Initialize the country list
        TopNPopCountries _topNPopCountries = new TopNPopCountries(_database); // Initialize top populated countries list
        CountientListByPop _countientListByPop = new CountientListByPop(_database); // Initialize continent list

        // Connect to database
        _database.connect();

        // Get world list ordered by population
        _countryList.GetWorldList();

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
