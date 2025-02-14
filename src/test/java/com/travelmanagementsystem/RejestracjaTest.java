package com.travelmanagementsystem;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RejestracjaTest {

    private Rejestracja rejestracja;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rejestracja = new Rejestracja();
    }

    @AfterEach
    void tearDown() {
        rejestracja.dispose();
    }

    @Test
    void testInitialComponentStates() {
        assertNotNull(rejestracja.tfname, "Name field should be initialized");
        assertNotNull(rejestracja.tfusername, "Username field should be initialized");
        assertNotNull(rejestracja.tfpassword, "Password field should be initialized");
        assertNotNull(rejestracja.tfpassword2, "Confirm password field should be initialized");
        assertNotNull(rejestracja.create, "Create button should be initialized");
        assertNotNull(rejestracja.back, "Back button should be initialized");

        assertTrue(rejestracja.isVisible(), "Frame should be visible by default");
    }

    @Test
    void testEmptyFieldValidation() {
        // Simulate empty fields
        rejestracja.tfusername.setText("");
        rejestracja.tfname.setText("");
        rejestracja.tfpassword.setText("");
        rejestracja.tfpassword2.setText("");

        rejestracja.create.getActionListeners()[0].actionPerformed(
                new ActionEvent(rejestracja.create, ActionEvent.ACTION_PERFORMED, "create")
        );


        assertEquals("", rejestracja.tfusername.getText(), "Username field should be empty");
        assertEquals("", rejestracja.tfname.getText(), "Name field should be empty");
        assertEquals("", new String(rejestracja.tfpassword.getPassword()), "Password field should be empty");
        assertEquals("", new String(rejestracja.tfpassword2.getPassword()), "Confirm password field should be empty");
    }

    @Test
    void testPasswordMismatch() {
        rejestracja.tfusername.setText("testUser1234");
        rejestracja.tfname.setText("Test Name");
        rejestracja.tfpassword.setText("password123");
        rejestracja.tfpassword2.setText("password456");

        rejestracja.create.getActionListeners()[0].actionPerformed(
                new ActionEvent(rejestracja.create, ActionEvent.ACTION_PERFORMED, "create")
        );

        assertNotEquals(
                new String(rejestracja.tfpassword.getPassword()),
                new String(rejestracja.tfpassword2.getPassword()),
                "Passwords should not match"
        );
    }

    @Test
    void testSuccessfulValidation() {
        rejestracja.tfusername.setText("testUser1234");
        rejestracja.tfname.setText("Test Name");
        rejestracja.tfpassword.setText("password123");
        rejestracja.tfpassword2.setText("password123");

        rejestracja.create.getActionListeners()[0].actionPerformed(
                new ActionEvent(rejestracja.create, ActionEvent.ACTION_PERFORMED, "create")
        );

        assertEquals("testUser1234", rejestracja.tfusername.getText(), "Username should remain unchanged");
        assertEquals("Test Name", rejestracja.tfname.getText(), "Name should remain unchanged");
        assertEquals("password123", new String(rejestracja.tfpassword.getPassword()), "Password should remain unchanged");
        assertEquals("password123", new String(rejestracja.tfpassword2.getPassword()), "Confirm password should remain unchanged");
    }

    @Test
    void testBackButtonNavigation() {
        ActionEvent mockEvent = new ActionEvent(rejestracja.back, ActionEvent.ACTION_PERFORMED, "back");
        rejestracja.actionPerformed(mockEvent);

        assertFalse(rejestracja.isVisible(), "Frame should not be visible after clicking back");
    }

    @Tag("integration")
    @Test
    void testIntegrationWithDatabase() {
        rejestracja.tfusername.setText("testIntegrationUuuuser");
        rejestracja.tfname.setText("Test Integration");
        rejestracja.tfpassword.setText("password123");
        rejestracja.tfpassword2.setText("password123");

        rejestracja.actionPerformed(
                new ActionEvent(rejestracja.create, ActionEvent.ACTION_PERFORMED, "create")
        );


    }
}