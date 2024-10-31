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

    //end region
}
