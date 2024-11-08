package com.napier.sem38;

import java.sql.ResultSet;

/**
 * Class for retrieving population data from the world database
 */
public class PopulationSum
{
    // field to store our reference to the database
    Database _database;

    /**
     * Class Constructor
     * Assigns the database and initialises the array list.
     * @param database a reference to the database connection
     */
    public PopulationSum(Database database)
    {
        _database = database; // store the database reference
    }

    //region World Functions
    /**
     * Queries the database to find world population.
     * @return world population as a Long int.
     */
    public Long GetWorldPop()
    {
        long pop = -1;

        // set up our query statement
        String query =
                "SELECT SUM(Population) AS WorldPop " +
                        "FROM country;";

        try
        {

            //get results from database
            ResultSet results = _database.Query(query);

            //add results to list
            while (results.next())
            {
                pop = Long.parseLong(results.getString("WorldPop"));
            }

        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
        }

        //if errored when receiving data, return 0
        return pop;
    }
    //endregion

    //region Continent Population

    /**
     * Queries the database to find the population of a specific continent.
     * @param continent the continent for which to calculate the population sum
     * @return the total population of the specified continent as a long integer
     */
    public Long GetContinentPop(String continent)
    {
        long pop = -1;

        // SQL query to calculate the population of a continent
        String query = "SELECT SUM(Population) AS ContinentPop " +
                "FROM country " +
                "WHERE Continent = '" + continent + "';";

        try
        {
            // Get results from database
            ResultSet results = _database.Query(query);

            // Parse results
            if (results.next())
            {
                pop = results.getLong("ContinentPop");
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving continent population data!");
        }

        return pop;
    }
    //endregion

    //region Country Population
    /**
     * Queries the database to find the population of a specific country.
     * @param country the country for which to calculate the population
     * @return the total population of the specified country as a long integer
     */
    public Long GetCountryPop(String country)
    {
        long pop = -1;

        // SQL query to calculate the population of a country
        String query = "SELECT Population AS CountryPop " +
                "FROM country " +
                "WHERE Name = '" + country + "';";

        try
        {
            // Get results from database
            ResultSet results = _database.Query(query);

            // Parse results
            if (results.next())
            {
                pop = results.getLong("CountryPop");
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving country population data!");
        }

        return pop;
    }
    //endregion

    //region District Population
    /**
     * Queries the database to find the population of a specific district.
     * @param district the country for which to calculate the population
     * @return the total population of the specified district as a long integer
     */
    public Long GetDistrictPop(String district)
    {
        long pop = -1;

        // SQL query to calculate the population of a District
        String query = "SELECT SUM(Population) AS DistrictPop " +
                "FROM city " +
                "WHERE district = '" + district + "';";

        try
        {
            // Get results from database
            ResultSet results = _database.Query(query);

            // Parse results
            if (results.next())
            {
                pop = results.getLong("DistrictPop");
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving country population data!");
        }

        return pop;
    }
    //endregion

    //region Region Population
    /**
     * Queries the database to find the population of a specific region.
     * @param region the region for which to calculate the population sum
     * @return the total population of the specified region as a long integer
     */
    public Long GetRegionPop(String region)
    {
        long pop = -1;

        // SQL query to calculate the population of a region
        String query = "SELECT SUM(Population) AS RegionPop " +
                "FROM country " +
                "WHERE Region = '" + region + "';";

        try
        {
            // Get results from database
            ResultSet results = _database.Query(query);

            // Parse results
            if (results.next())
            {
                pop = results.getLong("RegionPop");
            }
        }
        catch (Exception exception)
        {
            // Print error messages
            System.out.println(exception.getMessage());
            System.out.println("Error retrieving country population data!");
        }

        return pop;
    }
    //endregion
}
