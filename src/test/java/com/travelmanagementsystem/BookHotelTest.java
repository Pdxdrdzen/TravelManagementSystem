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
    
    
    doReturn(mockConnect).when(mockConnect).createConnect();
    
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getString("username")).thenReturn("TestUser");
    when(mockResultSet.getString("phone")).thenReturn("123456789");

    bookHotel = spy(new BookHotel("TestUser") {
        @Override
        protected Connect createConnect() {
            return mockConnect;
        }
    });

        // Przypisanie mocków do pól BookHotel
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
        // Wywołanie metody loadCustomerData
        bookHotel.loadCustomerData();

        // Weryfikacja, czy etykiety zostały ustawione poprawnie
        verify(mockUsernameLabel, times(1)).setText("TestUser");
        verify(mockPhoneLabel, times(1)).setText("123456789");
    }

    @Test
    void testUpdateHotels() {
        // Symulacja wybrania kierunku
        when(mockDestinationPackage.getSelectedItem()).thenReturn("Hurghada, Egipt");

        // Wywołanie metody updateHotels
        bookHotel.updateHotels();

        // Weryfikacja, czy odpowiedni hotel został dodany
        verify(mockHotelPackage).add("The V Luxury Resort");
    }

    @Test
    void testCalculatePrice_success() throws SQLException {
        // Symulacja danych wejściowych
        when(mockHotelPackage.getSelectedItem()).thenReturn("The V Luxury Resort");
        when(mockPeopleTextField.getText()).thenReturn("2");
        when(mockNightsTextField.getText()).thenReturn("3");
        when(mockFoodChoice.getSelectedItem()).thenReturn("Tak");

        // Symulacja odpowiedzi z bazy danych
        when(mockResultSet.getString("costperperson")).thenReturn("100");
        when(mockResultSet.getString("food")).thenReturn("50");

        // Wywołanie metody calculatePrice
        bookHotel.calculatePrice();

        // Weryfikacja, czy cena została poprawnie obliczona
        verify(mockPriceLabel).setText("5400 Zł");
    }

    @Test
    void testBookHotel_success() throws SQLException {
        // Symulacja danych wejściowych
        when(mockUsernameLabel.getText()).thenReturn("TestUser");
        when(mockDestinationPackage.getSelectedItem()).thenReturn("Hurghada, Egipt");
        when(mockHotelPackage.getSelectedItem()).thenReturn("The V Luxury Resort");
        when(mockPeopleTextField.getText()).thenReturn("2");
        when(mockFoodChoice.getSelectedItem()).thenReturn("Tak");
        when(mockPhoneLabel.getText()).thenReturn("123456789");
        when(mockPriceLabel.getText()).thenReturn("5400 Zł");

        // Wywołanie metody bookHotel
        bookHotel.bookHotel();

        // Weryfikacja, czy executeUpdate został wywołany
        verify(mockPreparedStatement).executeUpdate();
    }
}