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
     */
    private String GetCountryReport(String query)
    {
        //initialise list of results
        String report = "";

        // get the results from the database using provided query
        ResultSet results = _database.Query(query);

        try
        {
            // this moves through the results until there are no more
            while (results.next())
            {
                // add country data to list
                report =
                        "\n\t" + "Code: " + results.getString("Code") +
                                "\n\t" + "Name: " + results.getString("Name") +
                                "\n\t" + "Continent: " + results.getString("Continent") +
                                "\n\t" + "Region: " + results.getString("Region") +
                                "\n\t" + "Population: " + results.getString("Population") +
                                "\n\t" + "Capital: " + results.getString("Capital");
            }
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving data from ResultSet!");
        }

        // return the resulting list
        return report;
    }

    public String CountryReport(String countryName)
    {
        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Name = '" + countryName  + "';";

        //return resulting list
        return GetCountryReport(query);
    }

    public String CountryReport()
    {
        // SQL query to get the country
        String query = "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name as Capital " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID;";

        //get resulting list and get rid of the first line break
        return GetCountryReport(query);
    }

    //endregion

}
