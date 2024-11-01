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
        Long _continentPop;
        Long _countryPop;
        Long _regionPop;
        Long _districtPop;

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
        _countryRegionList = _countryList.GetRegionList(_name);
        // display the list
        _name = "the " + _name;
        _countryList.Display("List of countries in " + _name + " by population:",
                _countryRegionList);

        // get world population
        _worldPop = _populationSum.GetWorldPop();
        // print world population
        _populationSum.Display("World Population",
                _worldPop);

        //Issue 14 GitHub
        // Get and display cities in a continent (e.g., "Asia") ordered by population
        _name = "Asia";
        _cityList.Display("List of cities in " + _name + " by population:",
                _cityList.citiesInContinentLargetoSmall(_name));

        //Issue 15 GitHub
        //Get and display the cities in a region ordered by population
        _name = "Caribbean";
        _cityList.Display("List of cities in " + _name + " by population:",
                _cityList.citiesInRegionLargetoSmall(_name));

        //GitIssue 33
        //Get and display the population in a continent
        _name = "Asia";
        _continentPop = _populationSum.GetContinentPop(_name);
        _populationSum.Display("Population of the Continent " + _name, _continentPop);

        //GitIssue 35
        // Get and Display the population of a country
        _name = "France";
        _countryPop = _populationSum.GetCountryPop(_name);
        _populationSum.Display("Population of the Country " + _name, _countryPop);

        //GitIssue 34
        // Get and Display the population of a Region
        _name = "Caribbean";
        _regionPop = _populationSum.GetRegionPop(_name);
        _populationSum.Display("Population of the Region " + _name, _regionPop);

        //GitIssue 36
        //Get and Display the population of a district
        _name = "Texas";
        _districtPop = _populationSum.GetDistrictPop(_name);
        _populationSum.Display("Population of the District " + _name, _districtPop);


        // disconnect from database
        _database.disconnect();
    }
}
