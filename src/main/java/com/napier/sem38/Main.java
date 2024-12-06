package com.napier.sem38;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


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
        List<String> cells;
        List<String> cities;


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

        String[] countryRepHeadings = new String[]{"Code", "Name", "Continent", "Region", "Population", "Capital"};

        // GitIssue 7
        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetWorldList(), _report),
                "1");


        // GitIssue 10
        _number = 4;
        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetWorldList(_number), _report),
                "4");

        // GitIssue 8
        _name = "Asia";
        // display results
        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetContinentList(_name),_report) ,
                "2");


        // // GitIssue 9
        _name = "Eastern Europe";
        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetRegionList(_name),_report) ,
                "3");


        // GitIssue 32
        List<String> WorldPop = new ArrayList<String>();
        WorldPop.add(_populationSum.WorldPop().toString());
        _display.OutputFile(new String[]{"World Population"},
                WorldPop,
                "World Population");

        // GitIssue 13
        cities = _cityList.GetWorldList();
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "7");

        //GitIssue 14
        // Get and display cities in a continent (e.g., "Asia") ordered by population
        _name = "Asia";
        cities = _cityList.citiesInContinentLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "8");

        //GitIssue 15
        //Get and display the cities in a region ordered by population
        _name = "Caribbean";
        cities = _cityList.citiesInRegionLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "9");

        //GitIssue 16
        // Get and display cities in a country ordered by population
        _name = "United States";
        cities = _cityList.citiesInCountryLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "10");

        //GitIssue 17
        // Get and display the list of cities in a district ordered by population
        _name = "Texas";
        cities = _cityList.citiesInDistrictLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "11");

        //GitIssue 18
        _number = 4;
        cities = _cityList.GetTopNPopulatedCities(_number);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "12");

        //GitIssue 19
        _name = "Asia";
        _number = 3;

        _cityList.TopNPopCitiesInContinent(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "13");

        //GitIssue 20
        _name = "Middle East";
        _number = 3;
        cities = _cityList.TopNPopCitiesInRegion(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "14");

        //GitIssue 21
        _number = 3;
        _name = "Spain";
        cities = _cityList.TopNPopCitiesInCountry(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "15");

        //GitIssue 22
        _number = 3;
        _name = "Rio de Janeiro";
        cities = _cityList.TopNPopCitiesInDistrict(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "16");

        //GitIssue 23
        cities = _cityList.CapitalMostLeastPop();
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "17");

        //GitIssue24
        _name = "Asia";
        cities = _cityList.GetCapitalCitiesInContinentLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "18");

        //GitIssue25
        _name = "Middle East";
        cities = _cityList.GetCapitalCitiesInRegionLargetoSmall(_name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "19");

        // GitIssue 26
        _number = 5;
        cities = _cityList.GetTopNPopulatedCapitals(_number);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "20");

        //GitIssue 27
        _number = 3;
        _name = "Asia";
        _display.OutputFile(new String[]{"Top populated capital cities of " + _name},
                _cityList.TopNPopCapitalInContinent(_number, _name) ,
                "Top %d populated capital cities in %s");
        cities = _cityList.TopNPopCapitalInContinent(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "21");

        //GitIssue 28
        _number = 3;
        _name = "Middle East";
        cities = _cityList.TopNPopCapitalInRegion(_number, _name);
        cells = new ArrayList<>();

        for (String city : cities)
        {
            List<String> repo = _report.CityReport(city);
            cells.addAll(repo);
        }
        _display.OutputFile(new String[]{"Name", "Country", "District", "Population"},
                cells,
                "22");

        //GitIssue 33
        //Get and display the population in a continent
        _name = "Asia";
        List<String> contPop = new ArrayList<String>();
        contPop.add(_populationSum.ContinentPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                contPop,
                "Population of the Continent " + _name);

        //GitIssue 35
        // Get and Display the population of a country
        _name = "France";
        List<String> countryPop = new ArrayList<String>();
        countryPop.add(_populationSum.CountryPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                countryPop,
                "Population of the Country " + _name);

        //GitIssue 34
        // Get and Display the population of a Region
        _name = "Caribbean";
        List<String> PopRegion = new ArrayList<String>();
        PopRegion.add(_populationSum.RegionPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                PopRegion,
                "Population of the Region " + _name);

        //GitIssue 36
        //Get and Display the population of a district
        _name = "Texas";
        List<String> PopDistrict = new ArrayList<String>();
        PopDistrict.add(_populationSum.DistrictPop(_name).toString());
        _display.OutputFile(new String[]{_name + "Population"},
                PopDistrict,
                "Population of the District " + _name);



        // GitIssue 39
        _name = "Belgium";
        List<String> contReport = new ArrayList<String>();
        contReport.add(_report.CountryReport(_name).toString());
        _display.OutputFile(new String[]{_name + "Country Report"},
                contReport,
                "Country Report " + _name);


        // GitIssue 41
        List<String> BrCapRep = new ArrayList<String>();
        BrCapRep.add(_report.CapitalReport("Brazil"));
        _display.OutputFile(new String[]{_name + "Country Report"},
                BrCapRep,
                "Capital Report" + _name);


        //GitIssue 29
        _display.Show("City Per Continent Distribs", _populationSum.PopCityDistribContinent());
        _display.OutputFile(new String[]{"Continent", "TotalPop", "CityPop", "NonCityPop"},
                _populationSum.PopCityDistribContinent(),
                "23");

        //GitIssue 37
        //Get and display the population of a city
        _name = "Edinburgh";
        _display.Show("Population of the " + _name,
                _populationSum.CityPop(_name).toString());
        ArrayList<String> output = new ArrayList<>();
        output.add(_populationSum.CityPop("Edinburgh").toString());
        _display.OutputFile(new String[]{"Edinburgh Population"},
                output,
                "31");

        //GitIssue 30

        _display.Show("City per Region Distribution", _populationSum.PopCityDistribRegion());
        _display.OutputFile(new String[]{"Region", "TotalPop", "CityPop", "NonCityPop"},
                _populationSum.PopCityDistribRegion(),
                "24");


        // Get and display the capital cities in a region ordered by population
        _name = "Caribbean";
        _display.OutputFile(new String[]{"Capital Cities in the Region"},
                _cityList.GetCapitalCitiesInRegionLargetoSmall(_name) ,
                "Capital Cities in the Region " + _name + " by Population");


        //GitIssue 31
        _display.OutputFile(new String[]{"Country", "TotalPop", "CityPop", "NonCityPop"},
                _populationSum.PopCityDistribCountry() ,
                "25");

        //GitIssue 42
        _name = "France";
        _display.OutputFile(new String[]{"Population Distribution Report for " + _name,},
                _report.GetPopDistribReport(_name) ,
                "City per Country Distribution");

        //GitIssue 11
        // Specify the continent and N
        _name = "Asia";
        _number = 5;
        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetTopNPopulatedCountriesInContinent(_name, _number), _report) ,
                "5");

        // Get and display the top N populated countries in a region
        _name = "Caribbean";
        _number = 5;
        _display.Show("Top " + _number + " populated countries in the region " + _name,
                _countryList.GetTopNPopulatedCountriesInRegion(_name, _number));

        _display.OutputFile(countryRepHeadings,
                CountryList.GetCountryReportList(_countryList.GetTopNPopulatedCountriesInRegion(_name, _number),_report),
                "6");

        // List of languages to query
        List<String> languages = Arrays.asList("Chinese", "English", "Hindi", "Spanish", "Arabic");

        // Display the total speakers for each language
        for (String language : languages)
        {
            Long totalSpeakers = _populationSum.getLanguageSpeakers(language);
            _display.Show(language + " Speakers", totalSpeakers.toString());

        }

        // GitIssue 40
        _display.Show("Petare City Report", _report.CityReport("Petare"));

        // disconnect from database
        _database.Disconnect();
    }
}
