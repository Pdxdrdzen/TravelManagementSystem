package com.travelmanagementsystem;

// Nowa klasa do testowania
public class DashboardLogic {
    public final String username;

    public DashboardLogic(String username) {
        this.username = username;
    }

    public boolean handlePersonalDetails(Object source, Object addDetails, Object viewDetails,
                                         Object updateDetails, Object deleteDetails) {
        if (source == addDetails) {
            return true;
        }
        if (source == viewDetails) {
            return true;
        }
        if (source == updateDetails) {
            return true;
        }
        if (source == deleteDetails) {
            return true;
        }
        return false;
    }

    public boolean handlePackages(Object source, Object check, Object book, Object view) {
        if (source == check) {
            return true;
        }
        if (source == book) {
            return true;
        }
        if (source == view) {
            return true;
        }
        return false;
    }

    public boolean handleHotels(Object source, Object view, Object book,
                                Object viewBooked, Object dest) {
        if (source == view) {
            return true;
        }
        if (source == book) {
            return true;
        }
        if (source == viewBooked) {
            return true;
        }
        if (source == dest) {
            return true;
        }
        return false;
    }
}