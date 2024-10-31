package com.napier.sem38;

import java.sql.ResultSet;

/**
 * Class for retrieving population data from the world database
 */
public class PopulationSum
{
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
}
