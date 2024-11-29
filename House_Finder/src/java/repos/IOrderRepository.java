/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import java.util.Date;
import java.util.List;
import model.Orders;

/**
 *
 * @author ADMIN
 */
public interface IOrderRepository {

    //Retrieve a list of all orders.
    List<Orders> getall();

    //Retrieve a list of all orders.
    List<Orders> getallPaging(int pageIndex);

    //Retrieve a list of orders based on owner ID.
    List<Orders> getOrderByOwnerID(String ownerID, int pageIndex);

    //Retrieve orders based on search and filter criteria, specific to an owner.
    List<Orders> getOrdersBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, String renterSearch, int _villageID, int _hamletID, int _status, int pageIndex);

    //Retrieve orders based on search and filter criteria, specific to a renter.
    List<Orders> getOrdersBySearchAndFilterWithRenterID(String renterID, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex);

    //Retrieve orders based on search and filter criteria, specific to a admin.
    List<Orders> getOrdersBySearchAndFilterWithAdmin(String renterSearch, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex);

    //Change the status of an order.
    void changeOrderStatus(int orderID, int status);

    //Retrieve an order based on its ID and user ID.
    Orders getOrdeByID(String orderID, String userID);

    //Retrieve an order based on owner ID and order ID.
    Orders getOrdeByOwnerID(int userID, int orderID);

    //Get the current room count of a house.
    int getCurrentRoom(int houseID);

    //Get the house ID associated with an order ID.
    int getHouseIDbyOrderID(int orderID);

    //Add a new booking order.
    void addBooking(int houseID, int userID, Date dateBooking, int status);

    //Retrieve orders based on renter ID.
    List<Orders> getOrderByRenterID(String renterID, int pageIndex);

    //Perform check-in for an order.
    void checkIn(int orderID, Date dateCheckIn);

    //Perform check-out for an order.
    void checkOut(int orderID, Date dateCheckOut);

    //Get details of an order.
    Orders getOrdeDetail(int orderID);
}
