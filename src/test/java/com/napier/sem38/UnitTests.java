package com.napier.sem38;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * class for unit testing. For testing individual functions that aren't interacting with other systems
 */
public class UnitTests
{

    static Display _display;

    /**
     * Initialise Method
     * used to initialise the classes for testing
     */
    @BeforeAll
    static void Initialise()
    {
        // call the display constructor
        _display = new Display();
    }

    /**
     * Test to check the display list Show() method works with an expected input.
     */
    @Test
    void DisplayListShowExpected()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _display.Show("Test List",
                testList);
    }

    /**
     * Test to check the display list Show() method works with a null listName param.
     */
    @Test
    void DisplayListShowNameNull()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _display.Show(null,
                testList);
    }

    /**
     * Test to check the display list Show() method works with an empty string listName param.
     */
    @Test
    void DisplayListShowNameEmpty()
    {
        List<String> testList = new ArrayList<>();
        testList.add("Country1 with a population of 24 peps");
        testList.add("Country2 with a population of 20 peps");
        testList.add("Country3 with a population of 12 peps");

        _display.Show("",
                testList);
    }

    /**
     * Test to check the display list Show() method works with a null list param.
     */
    @Test
    void DisplayListShowListNull()
    {
        List<String> testList = null;
        _display.Show("Test List",
                testList);
    }

    /**
     * Test to check the display list Show() method works with an empty name param.
     */
    @Test
    void DisplayListShowListEmpty()
    {
        List<String> testList = new ArrayList<>();
        _display.Show("Test List",
                testList);
    }

    /**
     * Test to check the display String Show() method works with expected inputs.
     */
    @Test
    void DisplayStringShowDisplayExpected()
    {
        _display.Show("Test",
                "102313123");
    }

    /**
     * Test to check the display String Show() method works with a null name param.
     */
    @Test
    void DisplayStringShowNameNull()
    {
        _display.Show(null,
                "102313123");
    }

    /**
     * Test to check the display String Show() method works with an empty name param.
     */
    @Test
    void DisplayStringShowNameEmpty()
    {
        _display.Show("",
                "102313123");
    }

    /**
     * Test to check the display String Show() method works with a null result param.
     */
    @Test
    void DisplayStringShowResultNull()
    {
        String result = null;
        _display.Show("Test",
                result);
    }

    /**
     * Test to check the String Show() method works with an empty result param.
     */
    @Test
    void DisplayStringShowResultEmpty()
    {
        _display.Show("Test",
                "");
    }
}
