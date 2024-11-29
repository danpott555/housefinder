/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import dal.OrderDAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;

/**
 * The OrderRepository class acts as an intermediary between the business logic
 * and the data access layer for managing orders.
 *
 * This class provides methods to perform various operations on houses, such as
 * retrieving all order, retrieving order by id, updating order status, and
 * more.
 *
 * It implements the IOrderRepository interface to define the contract for
 * order-related operations.
 *
 */
public class OrderRepositoty implements IOrderRepository {

    @Override
    public List<Orders> getall() {
        try {
            return new OrderDAO().getall();
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Orders> getallPaging(int pageIndex) {
        try {
            return new OrderDAO().getallPaging(pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Orders> getOrderByOwnerID(String ownerID, int pageIndex) {
        try {
            return new OrderDAO().getOrderByOwnerID(ownerID, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Orders> getOrdersBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, String renterSearch, int _villageID, int _hamletID, int _status, int pageIndex) {
        try {
            return new OrderDAO().getOrdersBySearchAndFilterWithOwnerID(ownerID, nameSearch, renterSearch, _villageID, _hamletID, _status, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changeOrderStatus(int orderID, int status) {
        try {
            new OrderDAO().changeOrderStatus(orderID, status);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Orders getOrdeByID(String orderID, String userID) {
        try {
            return new OrderDAO().getOrdeByID(orderID, userID);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Orders getOrdeByOwnerID(int userID, int orderID) {
        try {
            return new OrderDAO().getOrdeByOwnerID(userID, orderID);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int getCurrentRoom(int houseID) {
        try {
            return new OrderDAO().getCurrentRoom(houseID);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int getHouseIDbyOrderID(int orderID) {
        try {
            return new OrderDAO().getHouseIDbyOrderID(orderID);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void addBooking(int houseID, int userID, Date dateBooking, int status) {
        try {
            new OrderDAO().addBooking(houseID, userID, dateBooking, status);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Orders> getOrderByRenterID(String renterID, int pageIndex) {
        try {
            return new OrderDAO().getOrderByRenterID(renterID, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Orders> getOrdersBySearchAndFilterWithRenterID(String renterID, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) {
        try {
            return new OrderDAO().getOrdersBySearchAndFilterWithRenterID(renterID, nameSearch, _villageID, _hamletID, _status, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void checkIn(int orderID, Date dateCheckIn) {
        try {
            new OrderDAO().checkIn(orderID, dateCheckIn);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void checkOut(int orderID, Date dateCheckOut) {
        try {
            new OrderDAO().checkOut(orderID, dateCheckOut);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Orders getOrdeDetail(int orderID) {
        try {
            return new OrderDAO().getOrdeDetail(orderID);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Orders> getOrdersBySearchAndFilterWithAdmin(String renterSearch, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) {
        try {
            return new OrderDAO().getOrdersBySearchAndFilterWithAdmin(renterSearch, nameSearch, _villageID, _hamletID, _status, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(OrderRepositoty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
