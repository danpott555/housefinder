/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * The Orders class represents a booking order for a house rental. It includes
 * details such as the order ID, house ID, user ID, booking date, status,
 * check-in date, and check-out date.
 *
 * The class provides constructors and getter/setter methods for accessing and
 * modifying the attributes. It also overrides the `toString` method for
 * generating a string representation of the object.
 *
 */
public class Orders {

    private int orderID;             // Unique ID for the order
    private int houseID;             // ID of the rented house
    private int userID;              // ID of the user making the booking
    private Date dateBookingDate;    // Date of booking
    private int status;              // Status of the booking (e.g., pending, confirmed, canceled)
    private Date dateCheckIn;        // Check-in date
    private Date dateCheckOut;       // Check-out date

    /**
     * Default constructor for the Orders class.
     */
    public Orders() {
    }

    /**
     * Parameterized constructor for the Orders class.
     *
     * @param orderID Unique ID for the order
     * @param houseID ID of the rented house
     * @param userID ID of the user making the booking
     * @param dateBookingDate Date of booking
     * @param status Status of the booking
     * @param dateCheckIn Check-in date
     * @param dateCheckOut Check-out date
     */
    public Orders(int orderID, int houseID, int userID, Date dateBookingDate, int status, Date dateCheckIn, Date dateCheckOut) {
        this.orderID = orderID;
        this.houseID = houseID;
        this.userID = userID;
        this.dateBookingDate = dateBookingDate;
        this.status = status;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
    }

    // Getters and setters for class attributes
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDateBookingDate() {
        return dateBookingDate;
    }

    public void setDateBookingDate(Date dateBookingDate) {
        this.dateBookingDate = dateBookingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Orders object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", houseID=" + houseID + ", userID=" + userID + ", dateBookingDate=" + dateBookingDate + ", status=" + status + ", dateCheckIn=" + dateCheckIn + ", dateCheckOut=" + dateCheckOut + '}';
    }

}
