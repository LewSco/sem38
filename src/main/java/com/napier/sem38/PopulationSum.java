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

    public Long GetPopulation(String query)
    {
        long pop = -1;

        try
        {
            //get results from database
            ResultSet results = _database.Query(query);

            //add results to list
            while (results.next())
            {
                pop = Long.parseLong(results.getString("pop"));
            }

        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Error retrieving result from database!");
            System.out.println(exception.getMessage());
        }

        return pop;
    }

    //region World Functions
    /**
     * Queries the database to find world population.
     * @return world population as a Long int.
     */
    public Long WorldPop()
    {
        long pop;

        // set up our query statement
        String query =
                "SELECT SUM(Population) AS pop " +
                        "FROM country;";

        //get results from database
        pop = GetPopulation(query);

        return pop;
    }
    //endregion

    //region Continent Population

    /**
     * Queries the database to find the population of a specific continent.
     * @param continent the continent for which to calculate the population sum
     * @return the total population of the specified continent as a long integer
     */
    public Long ContinentPop(String continent)
    {
        long pop;

        // SQL query to calculate the population of a continent
        String query = "SELECT SUM(Population) AS pop " +
                "FROM country " +
                "WHERE Continent = '" + continent + "';";

        try
        {
            //get results from database
            pop = GetPopulation(query);

            // check for error code
            if (pop == -1)
                throw new Exception("Error Code: -1");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Failed to retrieve " + continent + " population!");
            System.out.println(exception.getMessage());

            // return -1 in case pop = nothing
            return -1L;
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
    public Long CountryPop(String country)
    {
        long pop;

        // SQL query to calculate the population of a country
        String query = "SELECT Population AS pop " +
                "FROM country " +
                "WHERE Name = '" + country + "';";

        try
        {
            //get results from database
            pop = GetPopulation(query);

            // check for error code
            if (pop == -1)
                throw new Exception("Error Code: -1");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Failed to retrieve " + country + " population!");
            System.out.println(exception.getMessage());

            // return -1 in case pop = nothing
            return -1L;
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
    public Long DistrictPop(String district)
    {
        long pop;

        // SQL query to calculate the population of a District
        String query = "SELECT SUM(Population) AS pop " +
                "FROM city " +
                "WHERE district = '" + district + "';";

        try
        {
            //get results from database
            pop = GetPopulation(query);

            // check for error code
            if (pop == -1)
                throw new Exception("Error Code: -1");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Failed to retrieve " + district + " population!");
            System.out.println(exception.getMessage());

            // return -1 in case pop = nothing
            return -1L;
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
    public Long RegionPop(String region)
    {
        long pop;

        // SQL query to calculate the population of a region
        String query = "SELECT SUM(Population) AS pop " +
                "FROM country " +
                "WHERE Region = '" + region + "';";

        try
        {
            //get results from database
            pop = GetPopulation(query);

            // check for error code
            if (pop == -1)
                throw new Exception("Error Code: -1");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Failed to retrieve " + region + " population!");
            System.out.println(exception.getMessage());

            // return -1 in case pop = nothing
            return -1L;
        }

        return pop;
    }

    //endregion
}
