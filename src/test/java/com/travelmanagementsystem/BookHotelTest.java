package com.travelmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookHotelTest {

    @Mock
    private JLabel mockUsernameLabel;
    @Mock
    private JLabel mockPhoneLabel;
    @Mock
    private JLabel mockPriceLabel;
    @Mock
    private Choice mockDestinationPackage;
    @Mock
    private Choice mockHotelPackage;
    @Mock
    private Choice mockFoodChoice;
    @Mock
    private JTextField mockPeopleTextField;
    @Mock
    private JTextField mockNightsTextField;
    @Mock
    private Connect mockConnect;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private BookHotel bookHotel;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

    
        when(mockConnect.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("username")).thenReturn("TestUser");
        when(mockResultSet.getString("phone")).thenReturn("123456789");

    
        bookHotel = spy(new BookHotel("TestUser") {
            @Override
            protected Connect createConnect() {
                return mockConnect;
            }
        });

    
        bookHotel.usernameLabel = mockUsernameLabel;
        bookHotel.phoneLabel = mockPhoneLabel;
        bookHotel.priceLabel = mockPriceLabel;
        bookHotel.destinationPackage = mockDestinationPackage;
        bookHotel.hotelPackage = mockHotelPackage;
        bookHotel.foodChoice = mockFoodChoice;
        bookHotel.peopleTextField = mockPeopleTextField;
        bookHotel.nightsTextField = mockNightsTextField;
    }

    @Test
    void testLoadCustomerData_success() {
        
        bookHotel.loadCustomerData();

        
        verify(mockUsernameLabel, times(1)).setText("TestUser");
        verify(mockPhoneLabel, times(1)).setText("123456789");
    }

    @Test
    void testUpdateHotels() {
        
        when(mockDestinationPackage.getSelectedItem()).thenReturn("Hurghada, Egipt");


        bookHotel.updateHotels();

        
        verify(mockHotelPackage).add("The V Luxury Resort");
    }

    @Test
    void testCalculatePrice_success() throws SQLException {
        
        when(mockHotelPackage.getSelectedItem()).thenReturn("The V Luxury Resort");
        when(mockPeopleTextField.getText()).thenReturn("2");
        when(mockNightsTextField.getText()).thenReturn("3");
        when(mockFoodChoice.getSelectedItem()).thenReturn("Tak");

        
        when(mockResultSet.getString("costperperson")).thenReturn("100");
        when(mockResultSet.getString("food")).thenReturn("50");

        
        bookHotel.calculatePrice();

        
        verify(mockPriceLabel).setText("5400 Zł");
    }

    @Test
    void testBookHotel_success() throws SQLException {
        
        when(mockUsernameLabel.getText()).thenReturn("TestUser");
        when(mockDestinationPackage.getSelectedItem()).thenReturn("Hurghada, Egipt");
        when(mockHotelPackage.getSelectedItem()).thenReturn("The V Luxury Resort");
        when(mockPeopleTextField.getText()).thenReturn("2");
        when(mockFoodChoice.getSelectedItem()).thenReturn("Tak");
        when(mockPhoneLabel.getText()).thenReturn("123456789");
        when(mockPriceLabel.getText()).thenReturn("5400 Zł");

        
        bookHotel.bookHotel();

    
        verify(mockPreparedStatement).executeUpdate();
    }
}