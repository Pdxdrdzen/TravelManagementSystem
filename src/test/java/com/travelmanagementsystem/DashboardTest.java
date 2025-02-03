package com.travelmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardTest {
    private Dashboard dashboard;
    private ActionEvent mockEvent;

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard("testUser");
        mockEvent = mock(ActionEvent.class);
    }

    @Test
    void testHandlePersonalDetails_Add() {
        mockEvent = new ActionEvent(dashboard.addPersonalDetails, ActionEvent.ACTION_PERFORMED, "");
        assertTrue(dashboard.handlePersonalDetails(mockEvent.getSource()));
    }

    @Test
    void testHandlePersonalDetails_View() {
        mockEvent = new ActionEvent(dashboard.viewPersonalDetails, ActionEvent.ACTION_PERFORMED, "");
        assertTrue(dashboard.handlePersonalDetails(mockEvent.getSource()));
    }

    @Test
    void testHandlePackages_Check() {
        mockEvent = new ActionEvent(dashboard.checkpackages, ActionEvent.ACTION_PERFORMED, "");
        assertTrue(dashboard.handlePackages(mockEvent.getSource()));
    }

    @Test
    void testHandleHotels_View() {
        mockEvent = new ActionEvent(dashboard.viewhotels, ActionEvent.ACTION_PERFORMED, "");
        assertTrue(dashboard.handleHotels(mockEvent.getSource()));
    }

    @Test
    void testLaunchCalculator() {
        Dashboard spyDashboard = Mockito.spy(dashboard);
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);

        doReturn(mockProcessBuilder).when(spyDashboard).createProcessBuilder("calc.exe");
        spyDashboard.launchCalculator();
        verify(spyDashboard).createProcessBuilder("calc.exe");
    }

    @Test
    void testLaunchNotepad() {
        Dashboard spyDashboard = Mockito.spy(dashboard);
        ProcessBuilder mockProcessBuilder = mock(ProcessBuilder.class);

        doReturn(mockProcessBuilder).when(spyDashboard).createProcessBuilder("notepad.exe");
        spyDashboard.launchNotepad();
        verify(spyDashboard).createProcessBuilder("notepad.exe");
    }
}
