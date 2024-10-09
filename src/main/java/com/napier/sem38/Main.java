package com.napier.sem38;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {

        Database _database = new Database(); // initialise our database connection
        CountryList _countryList = new CountryList(_database); // initialise the country list

        // Connect to database
        _database.connect();

        // query the database for the worldList
        _countryList.GetWorldList();

        _countryList.Display(); // display our results

        // Disconnect from database
        _database.disconnect();
    }
}