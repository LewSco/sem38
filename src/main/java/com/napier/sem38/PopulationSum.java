package com.napier.sem38;

import java.sql.ResultSet;

/**
 * Class for retrieving population data from the world database
 */
public class PopulationSum
{
    Database _database;

    /**
     * extra constructor used for unit testing
     */
    public PopulationSum()
    {

    }

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
     * Returns world population as a long int.
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
    public long GetContinentPop(String continent)
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
    public long GetCountryPop(String country)
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

    //region Display

    /**
     * function to display the population sum the user searched for
     * @param sumName the name of the sum displayed to the user
     * @param population the population the user searched for
     */
    public void Display(String sumName, long population)
    {
        // check for errors
        try
        {
            if (sumName == null)
                throw new Exception("sumName cannot be null");
            if (sumName.isEmpty())
                throw new Exception("sumName cannot be empty");
            if (population == 0)
                throw new Exception("population cannot be zero");
            if (population < 0)
                throw new Exception("population cannot be negative");
        }
        catch(Exception exception)
        {
            System.out.println("\nDisplay Error:\n" +exception.getMessage() + "\n");
            return;
        }

        // print result
        System.out.println(sumName + ": " + population);
    }

    //endregion
}
