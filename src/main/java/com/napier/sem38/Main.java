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

        // GitIssue 7
        _display.OutputFile(new String[]{"World Countries"},
                _countryList.GetWorldList() ,
                "List of the worlds countries by population");

        // GitIssue 8
        _name = "Asia";
        // display results
        _display.OutputFile(new String[]{"continent list of countries by population"},
                _countryList.GetContinentList(_name) ,
                "List of countries in " + _name + " by population");

        // GitIssue 9
        _name = "Caribbean";
        _name = "the " + _name;
        _display.OutputFile(new String[]{"Countries by population"},
                _countryList.GetRegionList(_name) ,
                "List of countries in " + _name + " by population:");

        // GitIssue 10
        _number = 4;
        _display.OutputFile(new String[]{"World Countries"},
                _countryList.GetWorldList(_number) ,
                "List of the worlds countries by population up to " + _number);

        //GitIssue 11
        // Specify the continent and N
        _name = "Asia";
        _number = 5;
        _display.OutputFile(new String[]{"Top " + _number + " Populated Countries in " + _name,},
                _countryList.GetTopNPopulatedCountriesInContinent(_name, _number) ,
                "Top Populated Countries in " + _name);

        // GitIssue 12
        _name = "Caribbean";
        _number = 5;
        _display.OutputFile(new String[]{"Top " + _number + " populated countries in the region " + _name,},
                _countryList.GetTopNPopulatedCountriesInRegion(_name, _number) ,
                "Top Populated Countries in " + _name);

        // GitIssue 13
        _display.OutputFile(new String[]{"Worlds cities"},
                _cityList.GetWorldList() ,
                "List of the Worlds cities by population");

        //GitIssue 14
        // Get and display cities in a continent (e.g., "Asia") ordered by population
        _name = "Asia";
        _display.OutputFile(new String[]{"cities In Continent Large to Small"},
                _cityList.citiesInContinentLargetoSmall(_name),
                "List of cities in "+ _name +" by population:");

        //GitIssue 15
        //Get and display the cities in a region ordered by population
        _name = "Caribbean";
        _display.OutputFile(new String[]{"cities in a region ordered by population"},
                _cityList.citiesInRegionLargetoSmall(_name),
                "List of cities in " + _name + " by population:");

        //GitIssue 16
        // Get and display cities in a country ordered by population
        _name = "United States";
        _display.OutputFile(new String[]{"List of cities in the country " + _name + " by population:"},
                _cityList.citiesInCountryLargetoSmall(_name) ,
                "List of cities in the country" + _name);

        //GitIssue 17
        // Get and display the list of cities in a district ordered by population
        _name = "Texas";
        _display.OutputFile(new String[]{"List of cities in the district " + _name + " by population:"},
                _cityList.citiesInDistrictLargetoSmall(_name) ,
                "List of cities in the district");

        //GitIssue 18
        _number = 4;
        // display results
        _display.OutputFile(new String[]{"populated cities"},
                _cityList.GetTopNPopulatedCities(_number) ,
                "List of the most populated cities up to " + _number);

        //GitIssue 19
        _name = "Asia";
        _number = 3;
        _display.OutputFile(new String[]{"populated cities in"},
                _cityList.TopNPopCitiesInContinent(_number, _name) ,
                "Top %d populated cities in %s" + _number + _name);

        //GitIssue 20
        _name = "Middle East";
        _number = 3;
        _display.OutputFile(new String[]{"populated cities"},
                _cityList.TopNPopCitiesInRegion(_number, _name) ,
                "Top %d populated cities in %s " + _number + _name);

        //GitIssue 21
        _number = 3;
        _name = "Spain";
        _display.OutputFile(new String[]{"populated cities in district of " + _name},
                _cityList.TopNPopCitiesInCountry(_number, _name) ,
                "Top %d populated cities in %s");

        //GitIssue 22
        _number = 3;
        _name = "Rio de Janeiro";
        _display.OutputFile(new String[]{"populated cities in district"  + _name},
                _cityList.TopNPopCitiesInDistrict(_number, _name) ,
                "Top %d populated cities in %s district");

        //GitIssue 23
        _display.OutputFile(new String[]{"capitals by population"},
                _cityList.CapitalMostLeastPop() ,
                "List of all capitals by population");

        //GitIssue 25
        _display.OutputFile(new String[]{"populated cities"},
                _cityList.GetTopNPopulatedCities(_number) ,
                "List of the most populated cities up to " + _number);

        // GitIssue 26
        _number = 10;
        List<String> PopCapitalCit = new ArrayList<String>();
        PopCapitalCit.add(_cityList.GetTopNPopulatedCapitals(_number).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                PopCapitalCit,
                "Top " + _number + " Populated Capital Cities");

        //GitIssue 27
        _number = 3;
        _name = "Asia";
        _display.OutputFile(new String[]{"Top populated capital cities of " + _name},
                _cityList.TopNPopCapitalInContinent(_number, _name) ,
                "Top %d populated capital cities in %s");

        //GitIssue 28
        _number = 3;
        _name = "Middle East";
        _display.OutputFile(new String[]{"Top populated capital cities of " + _name + "region" },
                _cityList.TopNPopCapitalInRegion(_number, _name) ,
                String.format("Top %d populated capital cities in %s",_number, _name));

        //GitIssue 29
        _display.OutputFile(new String[]{"City Per Continent Distribs"},
                _populationSum.PopCityDistribContinent() ,
                "City Per Continent Distribs");

        //GitIssue 30
        _display.OutputFile(new String[]{"City per Region Distribution"},
                _populationSum.PopCityDistribRegion() ,
                "City per Region Distribution");

        //GitIssue 31
        _display.OutputFile(new String[]{"City per Country Distribution"},
                _populationSum.PopCityDistribCountry() ,
                "City per Country Distribution");

        // GitIssue 32
        List<String> WorldPop = new ArrayList<String>();
        WorldPop.add(_populationSum.WorldPop().toString());
        _display.OutputFile(new String[]{"World Population"},
                WorldPop,
                "World Population");

        //GitIssue 33
        //Get and display the population in a continent
        _name = "Asia";
        List<String> contPop = new ArrayList<String>();
        contPop.add(_populationSum.ContinentPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                contPop,
                "Population of the Continent " + _name);

        //GitIssue 34
        // Get and Display the population of a Region
        _name = "Caribbean";
        List<String> PopRegion = new ArrayList<String>();
        PopRegion.add(_populationSum.RegionPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                PopRegion,
                "Population of the Region " + _name);

        //GitIssue 35
        // Get and Display the population of a country
        _name = "France";
        List<String> countryPop = new ArrayList<String>();
        countryPop.add(_populationSum.CountryPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                countryPop,
                "Population of the Country " + _name);

        //GitIssue 36
        //Get and Display the population of a district
        _name = "Texas";
        List<String> PopDistrict = new ArrayList<String>();
        PopDistrict.add(_populationSum.DistrictPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                PopDistrict,
                "Population of the District " + _name);

        //GitIssue 37
        //Get and display the population of a city
        _name = "Edinburgh";
        List<String> EdinPop = new ArrayList<String>();
        EdinPop.add(_populationSum.CityPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                EdinPop,
                "Population of " + _name);

        // GitIssue 39
        _name = "Belgium";
        List<String> contReport = new ArrayList<String>();
        contReport.add(_report.CountryReport(_name).toString());
        _display.OutputFile(new String[]{_name + "Country Report"},
                contReport,
                "Country Report " + _name);

        // GitIssue 40
        List<String> PetCitRep = new ArrayList<String>();
        PetCitRep.add(_report.CityReport("Petare"));
        _display.OutputFile(new String[]{"Petare City Report"},
                PetCitRep,
                "Petare City Report");

        // GitIssue 41
        List<String> BrCapRep = new ArrayList<String>();
        BrCapRep.add(_report.CapitalReport("Brazil"));
        _display.OutputFile(new String[]{_name + "Country Report"},
                BrCapRep,
                "Capital Report" + _name);

        //GitIssue 42
        _name = "France";
        _display.OutputFile(new String[]{"Population Distribution Report for " + _name,},
                _report.GetPopDistribReport(_name) ,
                "City per Country Distribution");

        //GitIssue 25
        _name = "Caribbean";
        _display.OutputFile(new String[]{"Capital Cities in the Region"},
                _cityList.GetCapitalCitiesInRegionLargetoSmall(_name) ,
                "Capital Cities in the Region " + _name + " by Population");

        // disconnect from database
        _database.Disconnect();
    }
}
