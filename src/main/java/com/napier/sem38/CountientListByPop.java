package com.napier.sem38;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountientListByPop {
        private Database _database;
        private List<String> _list;

        // Constructor to initialize the database

        /**
         * Constructor for the class
         * Assigns database variable and instantiates list
         * @param database
         */
        public CountientListByPop(Database database) {
                _database = database;
                _list = new ArrayList<>();
        }

        // Method for fetching countries by population in a continent

        /**
         * Queries the database to sort by population for a given continent.
         * Stores the results in an array list
         * @param continent
         */
        public void getCountriesByPopulation(String continent) {
                _list.clear(); // clear the list for fresh data

                try {
                        // SQL query to get countries in a continent ordered by population
                        String query = "SELECT Name, Population " +
                                "FROM country " +
                                "WHERE Continent = '" + continent + "' " +
                                "ORDER BY Population DESC";

                        ResultSet rs = _database.Query(query);

                        while (rs.next()) {
                                // Store the result in the list
                                _list.add("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
                        }
                } catch (SQLException e) {
                        System.out.println("SQL error: " + e.getMessage());
                }
        }

        // Display the list

        /**
         * Loops through and displays all elements in the array list
         */
        public void Display() {
                System.out.println("Continent List By Population:");
                for (String country : _list) {
                        System.out.println(country);
                }
        }
}
