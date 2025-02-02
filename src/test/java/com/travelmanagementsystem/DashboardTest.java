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

@ExtendWith(MockitoExtension.class)
class DashboardTest {
    @Mock
    private Connect mockConnect;
    
    @Mock
    private PreparedStatement mockPreparedStatement;
    
    @Mock
    private ResultSet mockResultSet;
    
    private Dashboard dashboard;

    @BeforeEach
    @BeforeEach
    void setUp() throws SQLException {
        
        when(mockConnect.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        
        dashboard = new Dashboard("testUser") {
            @Override
            protected void setupImage() {
                
            }
            
            @Override
            protected Connect createConnect() {
                return mockConnect;
            }
        };
    }

    @Test
    void testHandlePersonalDetails() {
        
        assertTrue(dashboard.handlePersonalDetails(dashboard.addPersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.viewPersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.updatePersonalDetails));
        assertTrue(dashboard.handlePersonalDetails(dashboard.deletePersonalDetails));
        assertFalse(dashboard.handlePersonalDetails(new Object())); // Testowanie nieprawidłowego źródła
    }

    @Test
    void testHandlePackages() {
        
        assertTrue(dashboard.handlePackages(dashboard.checkpackages));
        assertTrue(dashboard.handlePackages(dashboard.bookpackages));
        assertTrue(dashboard.handlePackages(dashboard.viewpackages));
        assertFalse(dashboard.handlePackages(new Object())); // Testowanie nieprawidłowego źródła
    }

    @Test
    void testHandleHotels() {
        
        assertTrue(dashboard.handleHotels(dashboard.viewhotels));
        assertTrue(dashboard.handleHotels(dashboard.bookhotels));
        assertTrue(dashboard.handleHotels(dashboard.viewBookedHotels));
        assertTrue(dashboard.handleHotels(dashboard.destinations));
        assertFalse(dashboard.handleHotels(new Object())); 
    }

    @Test
    void testHandleUtilities() {
        
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);
        Process mockProcess = mock(Process.class);
        
        try {
            when(mockProcessBuilder.start()).thenReturn(mockProcess);
            doReturn(mockProcessBuilder).when(spy(new ProcessBuilder("")));
            
            assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.payments));
            assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.calculators));
            assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.notepad));
            assertDoesNotThrow(() -> dashboard.handleUtilities(dashboard.wiecej));
        } catch (Exception e) {
            fail("Shouldn't throw exception");
        }
    }

    @Test
    void testLaunchCalculator() {
        
        assertDoesNotThrow(() -> dashboard.launchCalculator());
    }

    @Test
    void testLaunchNotepad() {
        
        assertDoesNotThrow(() -> dashboard.launchNotepad());
    }

}