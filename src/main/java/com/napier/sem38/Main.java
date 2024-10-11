package com.napier.sem38;

import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {

        Database _database = new Database(); // initialise our database connection
        CountryList _countryList = new CountryList(_database); // initialise the country list
        TopNPopCountries _topNPopCountries = new TopNPopCountries(_database); //initialise list of top n populated countries

        // Connect to database
        _database.connect();

        // query the database for the worldList
        _countryList.GetWorldList();

        //query the database for top N populated countries
        _topNPopCountries.GetTopPopCountries(3);

        _countryList.Display(); // display our results
        _topNPopCountries.Display(); //display top n pop results

        //Get an instance with the db connection for CountientListByPop
        CountientListByPop listByPop = new CountientListByPop(a.con);

        // Execute the query and display results (This is a test using Asia)
        listByPop.getCountriesByPopulation("Asia");

        // Disconnect from database
        _database.disconnect();
    }
}