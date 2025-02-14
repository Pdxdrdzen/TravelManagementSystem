package com.travelmanagementsystem;

import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;

class AboutTest {
    private About about;

    @BeforeEach
    void setUp() {
        about = new About();
    }

    @AfterEach
    void tearDown() {
        about.dispose();
    }

    @Test
    void testInitialComponentStates() {
        assertNotNull(about.back, "Back button should be initialized");
        assertTrue(about.isVisible(), "Frame should be visible by default");

        assertEquals(500, about.getWidth(), "Width should be 500");
        assertEquals(550, about.getHeight(), "Height should be 550");

        assertEquals(Color.WHITE, about.getContentPane().getBackground(), "Background should be white");
    }

    @Test
    void testBackButtonAction() {
        ActionEvent mockEvent = new ActionEvent(about.back, ActionEvent.ACTION_PERFORMED, "back");
        about.actionPerformed(mockEvent);

        assertFalse(about.isVisible(), "Frame should not be visible after clicking back");
    }

    @Test
    void testMessageContent() {
        assertFalse(about.message.isEmpty(), "Message should not be empty");
        assertTrue(about.message.contains("O nas"), "Message should contain title");
    }
}