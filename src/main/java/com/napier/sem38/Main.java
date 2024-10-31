package com.napier.sem38;

import java.util.List;

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

        // initialize the class instances
        CountryList _countryList = new CountryList(_database);
        PopulationSum _populationSum = new PopulationSum(_database);
        CityList _cityList = new CityList(_database);

        // country lists
        List<String> _countryWorldList;
        List<String> _countryWorldListN;
        List<String> _countryContinentList;
        List<String> _countryRegionList;

        // populations
        Long _worldPop;

        // variables for storing names and numbers
        int _number;
        String _name;

        // connect to database
        if (args.length < 1)
        {
            _database.connect("localhost:33060", 30000);
        }
        else
        {
            _database.connect(args[0], Integer.parseInt(args[1]));
        }

        // get world list of countries ordered by population
        _countryWorldList = _countryList.GetWorldList();
        // display the list
        _countryList.Display("\nList of the worlds countries by population",
                _countryWorldList);

        // get world list of countries ordered by population up to n number of countries
        _number = 4;
        _countryWorldListN = _countryList.GetWorldList(_number);
        // display results
        _countryList.Display("\nList of the worlds countries by population up to " + _number,
                _countryWorldListN);

        // get continent list of countries ordered by population
        _name = "Asia";
        _countryContinentList = _countryList.GetContinentList(_name);
        // display results
        _countryList.Display("\nList of countries in " + _name + " by population",
                _countryContinentList);

        // get region list of countries ordered by population
        _name = "Caribbean";
        _countryList.GetRegionList(_name);

        // display the list
        _name = "the " + _name;
        System.out.println("\nList of countries in " + _name + " by population:");
        _countryList.Display();

        // get world population
        _worldPop = _populationSum.GetWorldPop();
        // print world population
        _populationSum.Display("World Population",
                _worldPop);

        //Issue 14 GitHub
        // Get cities in a continent (e.g., "Asia") ordered by population
        _name = "Asia";
        _cityList.citiesInContinentLargetoSmall(_name);

        System.out.println("\nList of cities in " + _name + " by population:");
        _cityList.Display();

        //Issue 15 GitHub
        //Get the cities in a region ordered by population
        _name = "Caribbean";
        _cityList.citiesInRegionLargetoSmall(_name);

        System.out.println("\nList of cities in " + _name + " by population:");
        _cityList.Display();

        // disconnect from database
        _database.disconnect();
    }
}
