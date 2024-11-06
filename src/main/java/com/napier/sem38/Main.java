package com.napier.sem38;

import java.util.List;

public class Main {
    /**
     * Main method for the application.
     * Instantiates, runs and displays all of the currently existing classes.
     * @param args arguments
     */
    public static void main(String[] args)
    {
        // initialize database connection
        Database _database = new Database();

        // initialise the display class
        Display _display = new Display();

        // initialise the query classes
        CountryList _countryList = new CountryList(_database);
        PopulationSum _populationSum = new PopulationSum(_database);
        CityList _cityList = new CityList(_database);

        // variables for storing names and numbers
        int _number;
        String _name;

        // connect to database
        if (args.length < 1)
        {
            _database.Connect("localhost:33060", 30000);
        }
        else
        {
            _database.Connect(args[0], Integer.parseInt(args[1]));
        }

        // display the list
        _display.Show("\nList of the worlds countries by population",
                _countryList.GetWorldList());

        // get world list of countries ordered by population up to n number of countries
        _number = 4;
        // display results
        _display.Show("\nList of the worlds countries by population up to " + _number,
                _countryList.GetWorldList(_number));

        // get continent list of countries ordered by population
        _name = "Asia";
        // display results
        _display.Show("\nList of countries in " + _name + " by population",
                _countryList.GetContinentList(_name));

        // get region list of countries ordered by population
        _name = "Caribbean";
        // display the list
        _name = "the " + _name;
        _display.Show("List of countries in " + _name + " by population:",
                _countryList.GetRegionList(_name));

        // print world population
        _display.Show("World Population",
                _populationSum.GetWorldPop().toString());

        //Issue 14 GitHub
        // Get and display cities in a continent (e.g., "Asia") ordered by population
        _name = "Asia";
        _display.Show("List of cities in " + _name + " by population:",
                _cityList.citiesInContinentLargetoSmall(_name));

        //Issue 15 GitHub
        //Get and display the cities in a region ordered by population
        _name = "Caribbean";
        _display.Show("List of cities in " + _name + " by population:",
                _cityList.citiesInRegionLargetoSmall(_name));

        //GitIssue 33
        //Get and display the population in a continent
        _name = "Asia";
        _display.Show("Population of the Continent " + _name,
                _populationSum.GetContinentPop(_name).toString());

        //GitIssue 35
        // Get and Display the population of a country
        _name = "France";
        _display.Show("Population of the Country " + _name,
                _populationSum.GetCountryPop(_name).toString());

        //GitIssue 34
        // Get and Display the population of a Region
        _name = "Caribbean";
        _display.Show("Population of the Region " + _name,
                _populationSum.GetRegionPop(_name).toString());

        //GitIssue 36
        //Get and Display the population of a district
        _name = "Texas";
        _display.Show("Population of the District " + _name,
                _populationSum.GetDistrictPop(_name).toString());

        // Retrieve and display the top N populated capitals
        _number = 10;
        _display.Show("Top " + _number + " Populated Capital Cities",
                _cityList.GetTopNPopulatedCapitals(_number).toString());

        // disconnect from database
        _database.Disconnect();
    }
}
