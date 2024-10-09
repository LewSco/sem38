package com.napier.sem38;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        // Create new Application
        Main _main = new Main();

        // Connect to database
        _main.connect();

        // Disconnect from database
        _main.disconnect();
    }



    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://database:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // function for querying the database
    public ResultSet Query(String query)
    {
        // set up a results set with a null value
        ResultSet resultSet = null;

        // try to query the database
        try
        {

            if (query == null || query.isEmpty())
                throw new Exception("Query statement is null or empty!");

            // create sql statement
            Statement stmt = con.createStatement();

            // we query the database using the String parameter provided
            resultSet = stmt.executeQuery(query);

            if (resultSet == null)
                throw new Exception("Query results returned null!");

        }
        catch (Exception exception) // if an error occurs then we
        {
            // print the error message
            System.out.print(exception.getMessage());
            System.out.print("Query Failed!"); // and tell the user the query failed
        }

        // we return the results set even if its null
        return resultSet;
    }
}