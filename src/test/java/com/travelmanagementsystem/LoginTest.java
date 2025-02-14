
package com.travelmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginTest {
    private Login login;
    @Mock
    private Connect mockConnect;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private Connection mockConnection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        login = new Login();
    }

    @Test
    void testSuccessfulLogin() throws SQLException {
        when(mockConnect.getConnection()).thenReturn(mockConnection);
        when(mockConnect.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        login.tfusername.setText("testUser");
        login.tfpassword.setText("testPass");

        ActionEvent mockEvent = new ActionEvent(login.login, ActionEvent.ACTION_PERFORMED, "login");

        assertDoesNotThrow(() -> login.actionPerformed(mockEvent));
    }

    @Test
    void testFailedLogin() throws SQLException {
        when(mockConnect.getConnection()).thenReturn(mockConnection);
        when(mockConnect.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        login.tfusername.setText("wrongUser");
        login.tfpassword.setText("wrongPass");

        ActionEvent mockEvent = new ActionEvent(login.login, ActionEvent.ACTION_PERFORMED, "login");

        assertDoesNotThrow(() -> login.actionPerformed(mockEvent));
    }

    @Test
    void testSignupButtonClick() {
        ActionEvent mockEvent = new ActionEvent(login.signup, ActionEvent.ACTION_PERFORMED, "signup");

        login.actionPerformed(mockEvent);

        assertFalse(login.isVisible());
    }

    @Test
    void testForgotPasswordButtonClick() {
        ActionEvent mockEvent = new ActionEvent(login.ForgotPassword, ActionEvent.ACTION_PERFORMED, "forgotPassword");

        login.actionPerformed(mockEvent);

        assertFalse(login.isVisible());
    }
}
