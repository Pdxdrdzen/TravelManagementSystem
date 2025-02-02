package com.travelmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    private DashboardLogic dashboard;
    private Object addPersonalDetails;
    private Object viewPersonalDetails;
    private Object updatePersonalDetails;
    private Object deletePersonalDetails;
    private Object checkpackages;
    private Object bookpackages;
    private Object viewpackages;
    private Object viewhotels;
    private Object bookhotels;
    private Object viewBookedHotels;
    private Object destinations;

    @BeforeEach
    void setUp() {
        dashboard = new DashboardLogic("testUser");
        // Tworzymy obiekty do testowania
        addPersonalDetails = new Object();
        viewPersonalDetails = new Object();
        updatePersonalDetails = new Object();
        deletePersonalDetails = new Object();
        checkpackages = new Object();
        bookpackages = new Object();
        viewpackages = new Object();
        viewhotels = new Object();
        bookhotels = new Object();
        viewBookedHotels = new Object();
        destinations = new Object();
    }

    @Test
    void testHandlePersonalDetails() {
        assertTrue(dashboard.handlePersonalDetails(addPersonalDetails, addPersonalDetails,
                viewPersonalDetails, updatePersonalDetails, deletePersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(viewPersonalDetails, addPersonalDetails,
                viewPersonalDetails, updatePersonalDetails, deletePersonalDetails));
        assertFalse(dashboard.handlePersonalDetails(new Object(), addPersonalDetails,
                viewPersonalDetails, updatePersonalDetails, deletePersonalDetails));
    }

    @Test
    void testHandlePackages() {
        assertTrue(dashboard.handlePackages(checkpackages, checkpackages,
                bookpackages, viewpackages));
        assertTrue(dashboard.handlePackages(bookpackages, checkpackages,
                bookpackages, viewpackages));
        assertFalse(dashboard.handlePackages(new Object(), checkpackages,
                bookpackages, viewpackages));
    }

    @Test
    void testHandleHotels() {
        assertTrue(dashboard.handleHotels(viewhotels, viewhotels, bookhotels,
                viewBookedHotels, destinations));
        assertTrue(dashboard.handleHotels(bookhotels, viewhotels, bookhotels,
                viewBookedHotels, destinations));
        assertFalse(dashboard.handleHotels(new Object(), viewhotels, bookhotels,
                viewBookedHotels, destinations));
    }
}