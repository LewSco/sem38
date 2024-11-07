package com.napier.sem38;

import java.util.List;

/**
 * class used to display the results of any queries.
 */
public class Display
{
    /**
     * the constructor takes no parameters.
     */
    public Display(){}

    /**
     * function to display the singular result of a query
     * @param name the name of the query
     * @param result the result of the query
     */
    public void Show(String name, String result)
    {
        // check for errors
        try
        {
            if (name == null)
                throw new Exception("name cannot be null");
            if (name.isEmpty())
                throw new Exception("name cannot be empty");
            if (result == null)
                throw new Exception("thing cannot be null");
            if (result.isEmpty())
                throw new Exception("thing cannot be empty");
        }
        catch(Exception exception)
        {
            System.out.println("\nDisplay Error:\n" +exception.getMessage() + "\n");
            return;
        }

        // print result
        System.out.println(name + ": " + result);
    }

    /**
     * Displays all elements in array list (query results if assigned)
     * @param listName the name of the list from a query
     * @param list the resulting list of the query
     */
    public void Show(String listName, List<String> list)
    {
        // to catch the errors from testing
        try
        {
            // check if the listName is null or empty
            if (listName == null)
                throw new Exception("listName is null");
            if (listName.isEmpty())
                throw new Exception("listName is empty");

            // check if the list is null or empty
            if (list == null)
                throw new Exception("list is null");
            if (list.isEmpty())
                throw new Exception("list is empty");
        }
        catch(Exception exception)
        {
            // print error messages
            System.out.println("Display Error: \n" + exception.getMessage());
            return;
        }

        // print the name of the list
        System.out.println(listName + ":");

        // seems like the java equivalent of a foreach loop
        for (String thing : list) // foreach (String thing in _list)
        {
            System.out.println(thing);  // Console.WriteLine(thing);
        }
    }


}
