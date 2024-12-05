package com.napier.sem38;

import java.util.ArrayList;
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
        Report _report = new Report(_database);

        // cells for output table
        List<List<String>> cells = new ArrayList<>();


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

        // GitIssue7
        _display.OutputFile(new String[]{"World Countries"},
                _countryList.GetWorldList() ,
                "List of the worlds countries by population");


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
                _populationSum.WorldPop().toString());

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
                _populationSum.ContinentPop(_name).toString());

        //GitIssue 35
        // Get and Display the population of a country
        _name = "France";
        _display.Show("Population of the Country " + _name,
                _populationSum.CountryPop(_name).toString());

        //GitIssue 34
        // Get and Display the population of a Region
        _name = "Caribbean";
        _display.Show("Population of the Region " + _name,
                _populationSum.RegionPop(_name).toString());

        //GitIssue 36
        //Get and Display the population of a district
        _name = "Texas";
        _display.Show("Population of the District " + _name,
                _populationSum.DistrictPop(_name).toString());

        // Retrieve and display the top N populated capitals
        _number = 10;
        _display.Show("Top " + _number + " Populated Capital Cities",
                _cityList.GetTopNPopulatedCapitals(_number).toString());

        // GitIssue39
        _name = "Belgium";
        _display.Show("Country Report " + _name, _report.CountryReport(_name));
        _display.Show("All Country Reports", _report.CountryReportList());

        // GitIssue13
        _display.Show("List of the Worlds cities by population",_cityList.GetWorldList());

        // GitIssue40
        _display.Show("Petare City Report", _report.CityReport("Petare"));

        // GitIssue41
        _display.Show("Brazil Capital Report", _report.CapitalReport("Brazil"));

        //GitIssue23
        _display.Show("List of all capitals by population", _cityList.CapitalMostLeastPop());

        //GitIssue22
        _number = 3;
        _name = "Rio de Janeiro";
        _display.Show(String.format("Top %d populated cities in %s district",_number, _name), _cityList.TopNPopCitiesInDistrict(_number, _name));

        //GitIssue21
        _number = 3;
        _name = "Spain";
        _display.Show(String.format("Top %d populated cities in %s",_number, _name), _cityList.TopNPopCitiesInCountry(_number, _name));

        //GitIssue27
        _number = 3;
        _name = "Asia";
        _display.Show(String.format("Top %d populated capital cities in %s",_number, _name), _cityList.TopNPopCapitalInContinent(_number, _name));

        //GitIssue28
        _number = 3;
        _name = "Middle East";
        _display.Show(String.format("Top %d populated capital cities in %s",_number, _name), _cityList.TopNPopCapitalInRegion(_number, _name));

        //GitIssue29
        _display.Show("City Per Continent Distribs", _populationSum.PopCityDistribContinent());

        //GitIssue37
        //Get and display the population of a city
        _name = "Edinburgh";
        _display.Show("Population of the " + _name,
                _populationSum.CityPop(_name).toString());

        //GitIssue18
        _number = 4;
        // display results
        _display.Show("\nList of the most populated cities up to " + _number,
                _cityList.GetTopNPopulatedCities(_number));


        //Gitissue20
        _name = "Middle East";
        _number = 3;
        _display.Show(String.format("Top %d populated cities in %s",_number, _name), _cityList.TopNPopCitiesInRegion(_number, _name));

        //Gitissue30
        _display.Show("City per Region Distribution", _populationSum.PopCityDistribRegion());

        //GitIssue25
        // Get and display the capital cities in a region ordered by population
        _name = "Caribbean";
        _display.Show("Capital Cities in the Region " + _name + " by Population",
                _cityList.GetCapitalCitiesInRegionLargetoSmall(_name));


        //Gitissue31
        _display.Show("City per Country Distribution", _populationSum.PopCityDistribCountry());

        //Gitissue19
        _name = "Asia";
        _number = 3;
        _display.Show(String.format("Top %d populated cities in %s", _number, _name), _cityList.TopNPopCitiesInContinent(_number, _name));

        //Gitissue42
        _name = "France";
        _display.Show("Population Distribution Report for " + _name, _report.GetPopDistribReport(_name));

        // disconnect from database
        _database.Disconnect();
    }
}
