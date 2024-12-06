package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * This function is exclusively used in PopDistribReport() to create string for each entry
     * @param results - the result of a given query on the databse
     * @param type - the type of entry this is
     * @param entry - if type name isnt in resultset type it in, otherwise leave as blank
     * @return String to add to the output list in PopDistribReport()
     */
    private String GetDistribReportEntry(ResultSet results, String type, String entry)
    {
        //create string
        String out = "";
        try
        {
            //set to first result and acquire entries
            results.next();
            Long total = results.getLong("total");
            Long cityPop = results.getLong("cityPop");
            Long nonCityPop = results.getLong("nonCityPop");

            //if empty get first column, otherwise use what was provided
            if(Objects.equals(entry, ""))
            {
                out = type + " - " + results.getNString(1);
            }
            else
            {
                out = type + " - " + entry;
            }

            //add all the otehr columns to report, and calculate percentages
            out +=
                    "\n\ttotal: " + PopulationSum.FormatLong(total) +
                    "\n\tcityPop: " + PopulationSum.FormatLong(cityPop) + " (" + Math.round(cityPop.floatValue()/total.floatValue()*100) + "%)" +
                    "\n\tnonCityPop: " + PopulationSum.FormatLong(nonCityPop) + " (" + Math.round(nonCityPop.floatValue()/total.floatValue()*100) + "%)";
        }
        catch(Exception e)
        {
            //print error
            System.out.println(e.getMessage());
            System.out.println("Error while retrieving data");
        }
        return out;
    }

    /***
     * Returns a report showing the city and noncity population distribution for
     * a given country (along with that countries region and continent)
     * @param country - The country the user wants a report for
     * @return List<String> which will be printed through display
     */
    public List<String> GetPopDistribReport(String country)
    {
        var out = new ArrayList<String>();

        try
        {
            //get resultsets for all necessary queries
            String curQuery = "SELECT country.Population AS total, cityPop, (country.Population - cityPop) as nonCityPop " +
                    "FROM (SELECT SUM(Population) as cityPop, CountryCode FROM city GROUP BY CountryCode) c " +
                    "JOIN country ON country.Code = c.CountryCode " +
                    "WHERE country.Name = '" + country + "'";

            var countResults = _database.Query(curQuery);

            curQuery = "SELECT country.Region as reg, SUM(country.Population) AS total, SUM(countSum) AS cityPop, (SUM(country.Population) - SUM(countSum)) as nonCityPop " +
                    "FROM (SELECT SUM(Population) as countSum, CountryCode FROM city GROUP BY CountryCode) c " +
                    "JOIN country ON country.Code = c.CountryCode " +
                    "WHERE country.Region = (SELECT Region FROM country WHERE Name = '" + country + "') " +
                    "GROUP BY country.Region ";

            var regResults = _database.Query(curQuery);

            curQuery = "SELECT country.Continent AS cont, SUM(country.Population) AS total, SUM(CountSum) AS cityPop, (SUM(country.Population) - SUM(CountSum)) as nonCityPop " +
                    "FROM (SELECT SUM(Population) as CountSum, CountryCode FROM city GROUP BY CountryCode) c " +
                    "JOIN country ON country.Code = c.CountryCode " +
                    "WHERE country.Continent = (SELECT Continent FROM country WHERE Name = '" + country + "') " +
                    "GROUP BY country.Continent ";

            var contResults = _database.Query(curQuery);

            //add all reports to out list
            out.add(GetDistribReportEntry(contResults, "Continent", ""));
            out.add(GetDistribReportEntry(regResults, "Region", ""));
            out.add(GetDistribReportEntry(countResults, "Country", country));
        }catch(Exception e)
        {
            //print error
            System.out.println(e.getMessage());
            System.out.println("Error retrieving data from the database.");
        }
        //return list
        return  out;
    }

    /**
     * Queries the database to find code, name, continent, region,
     * population and capital of specified country.
     * Stores the results in the array list variable.
     * @param query the query to send to the database to get the countries report.
     * @return a list of strings containing the resulting city report/s.
     */
    public List<String> GetCountryReport(String query)
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
    public List<String> GetCityReport(String query)
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
                reports.add(results.getString("Name"));
                reports.add(results.getString("Country"));
                reports.add(results.getString("District"));
                reports.add(results.getString("Population"));
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
     * @return a List<String> containing the city report.
     */
    public List<String> CityReport(String cityName)
    {
        // SQL query to get the country
        String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON country.Code = city.CountryCode " +
                "WHERE city.Name = '" + cityName  + "';";

        //return resulting list
        return GetCityReport(query);
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
    public String GetCapitalReport(String query)
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

        // to make sure we don't try to check an index the list doesn't have
        try
        {
            if (reports.isEmpty())
                throw new Exception("Reports list is Empty!");
        }
        catch (Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
            System.out.println("No reports found!");

            return "";
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
