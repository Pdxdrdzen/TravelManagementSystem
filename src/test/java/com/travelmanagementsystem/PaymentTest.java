package com.travelmanagementsystem;

import org.junit.jupiter.api.*;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    @AfterEach
    void tearDown() {
        payment.dispose();
    }

    @Test
    void testInitialComponentStates() {
        assertNotNull(payment.pay, "Pay button should be initialized");
        assertNotNull(payment.back, "Back button should be initialized");
        assertTrue(payment.isVisible(), "Frame should be visible by default");

        assertEquals(800, payment.getWidth(), "Width should be 800");
        assertEquals(600, payment.getHeight(), "Height should be 600");
    }

    @Test
    void testBackButtonAction() {
        ActionEvent mockEvent = new ActionEvent(payment.back, ActionEvent.ACTION_PERFORMED, "back");
        payment.actionPerformed(mockEvent);

        assertFalse(payment.isVisible(), "Frame should not be visible after clicking back");
    }

    @Test
    void testPayButtonAction() {

        ActionEvent mockEvent = new ActionEvent(payment.pay, ActionEvent.ACTION_PERFORMED, "pay");
        payment.actionPerformed(mockEvent);

        assertFalse(payment.isVisible(), "Payment frame should not be visible after clicking pay");
    }
}