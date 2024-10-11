package com.napier.sem38;

import java.sql.*;


public class CountientListByPop
{
        private Connection con;

        // Constructor to initialize connection
        public CountientListByPop(Connection connection) {
                this.con = connection;
        }

        // Method for fetching countries by population in a continent
        public void getCountriesByPopulation(String continent) {
                try {
                        Statement stmt = con.createStatement();
                        String query = "SELECT Name, Population " +
                                "FROM country " +
                                "WHERE Continent = '" + continent + "' " +
                                "ORDER BY Population DESC";
                        ResultSet rs = stmt.executeQuery(query);

                        while (rs.next()) {
                                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("population"));
                        }
                } catch (SQLException e) {
                        System.out.println("SQL error: " + e.getMessage());
                }
        }
}
