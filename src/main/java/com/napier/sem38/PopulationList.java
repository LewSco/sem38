package com.napier.sem38;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for retrieving population data from the world database
 */
public class PopulationList
{
    ArrayList<String> _list;
    Database _database;

    /**
     * Class Constructor
     * Assigns the database and initialises the array list.
     * @param database
     */
    public PopulationList(Database database)
    {
        _list = new ArrayList<>();
        _database = database; // store the database reference
    }

    //region World Functions
    /**
     * Queries the database to find world population.
     * Returns world population as a long int.
     */
    public long GetWorldPop()
    {
        // set up our query statement
        String query =
                "SELECT SUM(Population) AS WorldPop " +
                        "FROM country;";

        try
        {
            //clear list
            _list.clear();

            //get results from database
            ResultSet results = _database.Query(query);

            //add results to list
            while (results.next())
            {
                _list.add(results.getString("WorldPop"));
            }

            //convert result to long int and return
            return Long.parseLong(_list.get(0));
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println(exception.getMessage());
        }

        //if errored when receiving data, return 0
        return 0;
    }
    //endregion
}
