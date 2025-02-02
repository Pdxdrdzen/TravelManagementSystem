package com.travelmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Użyj Mockito z JUnit 5
class DashboardTest {

    @Mock
    private Connection mockConnection; // Mock połączenia z bazą danych

    @Mock
    private PreparedStatement mockPreparedStatement; // Mock PreparedStatement

    @Mock
    private ResultSet mockResultSet; // Mock ResultSet

    @InjectMocks
    private Dashboard dashboard; // Wstrzyknij mocki do Dashboard

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard("testUser");
    }

    @Test
    void testHandlePersonalDetails() {
        // Testowanie metody handlePersonalDetails
        assertTrue(dashboard.handlePersonalDetails(dashboard.addPersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.viewPersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.updatePersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.deletePersonalDetails));
        assertFalse(dashboard.handlePersonalDetails(new Object())); // Testowanie nieprawidłowego źródła
    }

    @Test
    void testHandlePackages() {
        // Testowanie metody handlePackages
        assertTrue(dashboard.handlePackages(dashboard.checkpackages));
        assertTrue(dashboard.handlePackages(dashboard.bookpackages));
        assertTrue(dashboard.handlePackages(dashboard.viewpackages));
        assertFalse(dashboard.handlePackages(new Object())); // Testowanie nieprawidłowego źródła
    }

    @Test
    void testHandleHotels() {
        // Testowanie metody handleHotels
        assertTrue(dashboard.handleHotels(dashboard.viewhotels));
        assertTrue(dashboard.handleHotels(dashboard.bookhotels));
        assertTrue(dashboard.handleHotels(dashboard.viewBookedHotels));
        assertTrue(dashboard.handleHotels(dashboard.destinations));
        assertFalse(dashboard.handleHotels(new Object())); // Testowanie nieprawidłowego źródła
    }

    @Test
    void testHandleUtilities() {
        // Sprawdzenie, czy metoda handleUtilities działa poprawnie
        assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.payments));
        assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.calculators));
        assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.notepad));
        assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.wiecej));
    }

    @Test
    void testLaunchCalculator() {
        // Testowanie metody launchCalculator
        assertDoesNotThrow(() -> dashboard.launchCalculator());
    }

    @Test
    void testLaunchNotepad() {
        // Testowanie metody launchNotepad
        assertDoesNotThrow(() -> dashboard.launchNotepad());
    }

}