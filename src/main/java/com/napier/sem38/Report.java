package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Report
{
    Database _database;

    /**
     * Class Constructor
     * Assigns the database.
     * @param database reference to the database
     */
    public Report(Database database)
    {
        // store the database reference
        _database = database;
    }

    //region Country

    /**
     * Queries the database to find code, name, continent, region,
     * population and capital of specified country.
     * Stores the results in the array list variable.
     * @param query the query to send to the database to get the countries report.
     * @return a list of strings containing the resulting city report/s.
     */
    private List<String> GetCountryReport(String query)
    {
        //initialise list of results
        List<String> reports = new ArrayList<>();

        try
        {
            // get the results from the database using provided query
            ResultSet results = _database.Query(query);

            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                reports.add(
                        "\n\t" + "Code: " + results.getString("Code") +
                                "\n\t" + "Name: " + results.getString("Name") +
                                "\n\t" + "Continent: " + results.getString("Continent") +
                                "\n\t" + "Region: " + results.getString("Region") +
                                "\n\t" + "Population: " + results.getString("Population") +
                                "\n\t" + "Capital: " + results.getString("Capital")
                );
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return reports;
    }

    /**
     * sets up a query using a supplied country name to get a report from the database
     * @param countryName the name of the country you want a report on
     * @return a string containing the country report.
     */
    public String CountryReport(String countryName)
    {
        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Name = '" + countryName  + "';";

        //return resulting list
        return GetCountryReport(query).get(0);
    }

    /**
     * function to get the country reports of all countries in the database.
     * @return a list of strings containing the country reports.
     */
    public List<String> CountryReportList()
    {
        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID;";

        //get resulting list and get rid of the first line break
        return GetCountryReport(query);
    }

    //endregion

    //region City

    /**
     * Queries the database to find name, country, district and
     * population of specified city.
     * Stores the results in the array list variable.
     * @param query the query to send to the database to get the city's report.
     * @return a list of strings containing the resulting city report/s.
     */
    private List<String> GetCityReport(String query)
    {
        //initialise list of results
        List<String> reports = new ArrayList<>();

        try
        {
            // get the results from the database using provided query
            ResultSet results = _database.Query(query);

            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                reports.add(
                        "\n\t" + "Name: " + results.getString("Name") +
                                "\n\t" + "Country: " + results.getString("Country") +
                                "\n\t" + "District: " + results.getString("District") +
                                "\n\t" + "Population: " + results.getString("Population")
                );
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return reports;
    }

    /**
     * sets up a query using a supplied city name to get a report from the database.
     * @param cityName the name of the city you want a report on.
     * @return a string containing the city report.
     */
    public String CityReport(String cityName)
    {
        // SQL query to get the country
        String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON country.Code = city.CountryCode " +
                "WHERE city.Name = '" + cityName  + "';";

        //return resulting list
        return GetCityReport(query).get(0);
    }

    //endregion

    //region Capital

    /**
     * Queries the database to find name, district and
     * population of the specified country's capital.
     * Stores the results in the array list variable.
     * @param query the query to send to the database to get the capital report.
     * @return a list of strings containing the resulting capital report.
     */
    private String GetCapitalReport(String query)
    {
        //initialise list of results
        List<String> reports = new ArrayList<>();

        try
        {
            // get the results from the database using provided query
            ResultSet results = _database.Query(query);

            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                reports.add(
                        "\n\t" + "Name: " + results.getString("Name") +
                                "\n\t" + "District: " + results.getString("District") +
                                "\n\t" + "Population: " + results.getString("Population")
                );
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return reports.get(0);
    }

    /**
     * sets up a query using a supplied country name to get a report from the database for the capital city.
     * @param countryName the name of the country you want a report on the capital of.
     * @return a string containing the city report.
     */
    public String CapitalReport(String countryName)
    {
        // SQL query to get the country
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON country.Capital = city.ID " +
                "WHERE country.Name = '" + countryName  + "';";

        //return resulting list
        return GetCapitalReport(query);
    }

    //endregion
}
