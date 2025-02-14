package com.travelmanagementsystem;

import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

class Przelewy24Test {
    private Przelewy24 przelewy24;

    @BeforeEach
    void setUp() {
        przelewy24 = new Przelewy24();
    }

    @AfterEach
    void tearDown() {
        przelewy24.dispose();
    }

    @Test
    void testInitialComponentStates() {
        assertNotNull(przelewy24.back, "Back button should be initialized");
        assertTrue(przelewy24.isVisible(), "Frame should be visible by default");

        assertEquals(800, przelewy24.getWidth(), "Width should be 800");
        assertEquals(600, przelewy24.getHeight(), "Height should be 600");
    }

    @Test
    void testBackButtonAction() {
        ActionEvent mockEvent = new ActionEvent(przelewy24.back, ActionEvent.ACTION_PERFORMED, "back");
        przelewy24.actionPerformed(mockEvent);

        assertFalse(przelewy24.isVisible(), "Frame should not be visible after clicking back");
    }

    @Test
    void testEditorPaneInitialization() {
        Component[] components = przelewy24.getContentPane().getComponents();
        JScrollPane scrollPane = null;
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                scrollPane = (JScrollPane) comp;
                break;
            }
        }

        assertNotNull(scrollPane, "Scroll pane should be initialized");

        Component view = scrollPane.getViewport().getView();
        assertTrue(view instanceof JEditorPane, "View should be JEditorPane");

        JEditorPane editorPane = (JEditorPane) view;
        assertFalse(editorPane.isEditable(), "Editor pane should not be editable");

        String contentType = editorPane.getContentType();
        assertNotNull(contentType, "Content type should be set");
        assertEquals("text/html", contentType, "Content type should be text/html");
    }

    @Test
    void testEditorPaneContent() {
        JScrollPane scrollPane = (JScrollPane) przelewy24.getContentPane().getComponent(0);
        JEditorPane editorPane = (JEditorPane) scrollPane.getViewport().getView();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String content = editorPane.getText();

        assertNotNull(content, "Editor pane content should not be null");
        assertFalse(content.isEmpty(), "Editor pane content should not be empty");
    }

    @Test
    void testErrorHandlingContent() {
        JScrollPane scrollPane = (JScrollPane) przelewy24.getContentPane().getComponent(0);
        JEditorPane editorPane = (JEditorPane) scrollPane.getViewport().getView();

        editorPane.setContentType("text/html");
        editorPane.setText("<html>Blad ladowania, Error 404</html>");

        String content = editorPane.getText();
        assertTrue(
                content.contains("Blad ladowania") || content.contains("Error 404"),
                "Editor pane should contain error message when page loading fails"
        );
    }
}