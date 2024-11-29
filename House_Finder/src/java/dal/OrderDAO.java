/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;

/**
 * Data Access Object for interacting with the Orders table in the database.
 * Contains methods to retrieve order information and manage orders. Extends the
 * DBContext class to access the database connection.
 *
 */
public class OrderDAO extends DBContext {
    private static final String PAGING_SQL = " ORDER BY DateBooking DESC Offset ? rows fetch next 10 rows only";

    /**
     * Retrieves a list of all orders except those with Status 3 (canceled).
     *
     * return A list of Orders objects representing all orders. throws Exception
     * If an error occurs while accessing the database.
     */
    public List<Orders> getall() throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Status <>3 ORDER BY DateBooking DESC";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Orders
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listOrders;
    }
    
    /**
     * Retrieves a list of all orders except those with Status 3 (canceled).
     *
     * return A list of Orders objects representing all orders. throws Exception
     * If an error occurs while accessing the database.
     */
    public List<Orders> getallPaging(int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Status <>3" +PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setInt(1, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Orders
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listOrders;
    }

    /**
     * Retrieves a list of orders associated with a specific owner ID.
     *
     * @param ownerID The ID of the owner whose orders are to be retrieved.
     * @return A list of Orders objects associated with the specified owner.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Orders> getOrderByOwnerID(String ownerID, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        // SQL query to retrieve orders associated with the specified owner ID
        String sql = "SELECT* FROM Orders o INNER JOIN Houses h ON o.HouserID = h.HouseID\n"
                + "					  INNER JOIN Accounts a ON o.UserID = a.UserID\n"
                + "					  WHERE h.UserID = ? AND o.Status <> 3 " + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setString(1, ownerID);
            ps.setInt(2, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Orders
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listOrders;
    }

    public List<Orders> getOrdersBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, String renterSearch, int _villageID, int _hamletID, int _status, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        int indexOfVillageID = 0;
        int indexOfHamletID = 0;
        int indexOfStatus = 0;
        int lastIndex = 4;
        String sql = "SELECT * FROM Orders a INNER JOIN Houses b ON a.HouserID = b.HouseID\n"
                + "		             INNER JOIN Accounts c ON a.UserID = c.UserID\n"
                + "                WHERE a.Status <>3 AND b.UserID = ? AND b.HouseName LIKE ? AND (c.FirstName LIKE ? OR c.LastName LIKE ? )";
        if (_villageID != 0) {
            sql += " AND b.VillageID = ?";
            indexOfVillageID = ++lastIndex;
            if (_hamletID != 0) {
                sql += " AND b.HamletID = ?";
                indexOfHamletID = ++lastIndex;
            }
        }

        if (_status != -1) {
            sql += " AND a.Status = ?";
            indexOfStatus = ++lastIndex;
        }
        sql += PAGING_SQL;

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, ownerID);
            ps.setNString(2, "%" + nameSearch + "%");
            ps.setNString(3, "%" + renterSearch + "%");
            ps.setNString(4, "%" + renterSearch + "%");

            if (indexOfVillageID != 0) {
                ps.setInt(indexOfVillageID, _villageID);
                if (indexOfHamletID != 0) {
                    ps.setInt(indexOfHamletID, _hamletID);
                }
            }

            if (indexOfStatus != 0) {
                ps.setInt(indexOfStatus, _status);
            }
            ps.setInt(++lastIndex, pageIndex);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.close();
        }
        return listOrders;
    }

    /**
     * Retrieves a list of orders based on search and filter criteria for a
     * specific owner ID.
     *
     * @param ownerID The ID of the owner whose orders are to be retrieved.
     * @param nameSearch The search term for house names.
     * @param renterSearch The search term for renter names.
     * @param _villageID The ID of the village to filter by.
     * @param _hamletID The ID of the hamlet to filter by.
     * @param _status The status to filter by.
     * @return A list of Orders objects based on the search and filter criteria.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Orders> getOrdersBySearchAndFilterWithRenterID(String renterID, String nameSearch, int _villageID, int _hamletID, int _status , int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        int indexOfVillageID = 0;
        int indexOfHamletID = 0;
        int indexOfStatus = 0;
        int lastIndex = 2;
        // SQL query to retrieve orders based on search and filter criteria
        String sql = "SELECT * FROM Orders a INNER JOIN Houses b ON a.HouserID = b.HouseID\n"
                + "                      WHERE a.UserID = ? AND b.HouseName LIKE ?";
        if (_villageID != 0) {
            sql += " AND b.VillageID = ?";
            indexOfVillageID = ++lastIndex;
            if (_hamletID != 0) {
                sql += " AND b.HamletID = ?";
                indexOfHamletID = ++lastIndex;
            }
        }

        if (_status != -1) {
            sql += " AND a.Status = ?";
            indexOfStatus = ++lastIndex;
        }
        sql += PAGING_SQL;

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setString(1, renterID);
            ps.setNString(2, "%" + nameSearch + "%");

            if (indexOfVillageID != 0) {
                ps.setInt(indexOfVillageID, _villageID);
                if (indexOfHamletID != 0) {
                    ps.setInt(indexOfHamletID, _hamletID);
                }
            }

            if (indexOfStatus != 0) {
                ps.setInt(indexOfStatus, _status);
            }
            ps.setInt(++lastIndex, pageIndex);

            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Orders
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listOrders;
    }

    /**
     * Retrieves a list of orders based on search and filter criteria for an
     * admin user.
     *
     * @param renterSearch The search term for renter names.
     * @param nameSearch The search term for house names.
     * @param _villageID The ID of the village to filter by.
     * @param _hamletID The ID of the hamlet to filter by.
     * @param _status The status to filter by.
     * @return A list of Orders objects based on the search and filter criteria.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Orders> getOrdersBySearchAndFilterWithAdmin(String renterSearch, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        int indexOfVillageID = 0;
        int indexOfHamletID = 0;
        int indexOfStatus = 0;
        int lastIndex = 3;
        // SQL query to retrieve orders based on search and filter criteria for admin
        String sql = "SELECT * FROM Orders a INNER JOIN Houses b ON a.HouserID = b.HouseID\n"
                + "		             INNER JOIN Accounts c ON a.UserID = c.UserID\n"
                + "                WHERE a.Status <>3 AND b.HouseName LIKE ? AND (c.FirstName LIKE ? OR c.LastName LIKE ? ) ";
        if (_villageID != 0) {
            sql += " AND b.VillageID = ?";
            indexOfVillageID = ++lastIndex;
            if (_hamletID != 0) {
                sql += " AND b.HamletID = ?";
                indexOfHamletID = ++lastIndex;
            }
        }

        if (_status != -1) {
            sql += " AND a.Status = ?";
            indexOfStatus = ++lastIndex;
        }
        
        sql += PAGING_SQL;
        
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setNString(1, "%" + nameSearch + "%");
            ps.setNString(2, "%" + renterSearch + "%");
            ps.setNString(3, "%" + renterSearch + "%");

            if (indexOfVillageID != 0) {
                ps.setInt(indexOfVillageID, _villageID);
                if (indexOfHamletID != 0) {
                    ps.setInt(indexOfHamletID, _hamletID);
                }
            }

            if (indexOfStatus != 0) {
                ps.setInt(indexOfStatus, _status);
            }
            ps.setInt(++lastIndex, pageIndex);

            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Orders
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listOrders;
    }

    /**
     * Changes the status of an order.
     *
     * @param orderID The ID of the order to update.
     * @param status The new status value to set.
     * @throws Exception If an error occurs while accessing the database.
     */
    public void changeOrderStatus(int orderID, int status) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setInt(1, status);
            ps.setInt(2, orderID);
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
    }

    /**
     * Retrieves an order by owner ID and order ID.
     *
     * @param userID The owner's user ID.
     * @param orderID The ID of the order to retrieve.
     * @return An Orders object corresponding to the given owner ID and order
     * ID, or null if not found.
     * @throws Exception If an error occurs while accessing the database.
     */
    public Orders getOrdeByOwnerID(int userID, int orderID) throws Exception {
        Connection connection = getConnection();
        // SQL query to select order details based on owner ID and order ID
        String sql = "SELECT * FROM Orders o INNER JOIN Houses h ON o.HouserID = h.HouseID\n"
                + "					  INNER JOIN Accounts a ON o.UserID = a.UserID\n"
                + "					  WHERE h.UserID = ? AND o.OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setInt(1, userID);
            ps.setInt(2, orderID);
            ResultSet rs = ps.executeQuery();                                   // Execute query
            if (rs.next()) {                                                    // Extract data from result set
                int houseID = rs.getInt("HouseID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                return new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate);    // Create and return Orders object
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return null;                                                            // Return null if order not found
    }

    // Retrieves an order by order ID and user ID.
    public Orders getOrdeByID(String orderID, String userID) throws Exception {
        Connection connection = getConnection();
        // SQL query to select order details based on order ID and user ID
        String sql = "SELECT * FROM Orders o INNER JOIN Houses h ON o.HouserID = h.HouseID\n"
                + "					  INNER JOIN Accounts a ON o.UserID = a.UserID\n"
                + "					  WHERE o.UserID = ? AND o.OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setString(2, orderID);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();                                   // Execute query
            if (rs.next()) {                                                    // Extract data from result set
                int houseID = rs.getInt("HouseID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                return new Orders(houseID, houseID, houseID, bookingDate, status, checkInDate, checkOutDate);   // Create and return Orders object
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return null;                                                            // Return null if order not found
    }

    // Retrieves order details by order ID.
    public Orders getOrdeDetail(int orderID) throws Exception {
        Connection connection = getConnection();
        // SQL query to select order details based on order ID
        String sql = "SELECT * FROM Orders o INNER JOIN Houses h ON o.HouserID = h.HouseID\n"
                + "					  INNER JOIN Accounts a ON o.UserID = a.UserID\n"
                + "					  WHERE  o.OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();                                   // Execute query
            if (rs.next()) {                                                    // Extract data from result set
                int houseID = rs.getInt("HouseID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                return new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate);    // Create and return Orders object
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return null;                                                            // Return null if order not found
    }

    // Retrieves the current room count for a house by its house ID.
    public int getCurrentRoom(int houseID) throws Exception {
        Connection connection = getConnection();
        int currentRoom = 0;
        // SQL query to select the current room count for a house
        String sql = "SELECT b.NoOfRoom FROM Orders a INNER JOIN Houses b ON a.HouserID = b.HouseID WHERE a.HouserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setInt(1, houseID);
            ResultSet rs = ps.executeQuery();                                   // Execute query
            if (rs.next()) {                                                    // Get the current room count from the result set
                currentRoom = rs.getInt("NoOfRoom");
                return currentRoom;                                             // Return the current room count
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex); // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return currentRoom;                                                     // Return 0 if house not found
    }

    // Retrieves the house ID associated with an order ID.
    public int getHouseIDbyOrderID(int orderID) throws Exception {
        Connection connection = getConnection();
        int currentRoom = 0;
        // SQL query to select the house ID from Orders based on order ID
        String sql = "SELECT HouserID FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();                                   // Execute query   
            if (rs.next()) {
                currentRoom = rs.getInt("HouserID");                            // Get the house ID from the result set
                return currentRoom;                                             // Return the house ID
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return currentRoom;                                                     // Return 0 if order ID not found
    }

    // Adds a booking to the Orders table
    public void addBooking(int houseID, int userID, Date dateBooking, int status) throws Exception {
        Connection connection = getConnection();
        // SQL query to insert booking details into Orders table
        String sql = "INSERT INTO Orders(HouserID,UserID,DateBooking,Status) Values (?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setInt(1, houseID);
            ps.setInt(2, userID);
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(dateBooking));
            ps.setInt(4, status);
            ps.executeUpdate();                                                 // Execute update
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
    }

    // Updates the check-in date of an order.
    public void checkIn(int orderID, Date dateCheckIn) throws Exception {
        Connection connection = getConnection();
        // SQL query to update the check-in date in Orders table
        String sql = "UPDATE Orders SET DateCheckIn = ?  WHERE OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(dateCheckIn));
            ps.setInt(2, orderID);
            ps.executeUpdate();                                                 // Execute update
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.close();                                            // Close database connection
        }
    }

    // Updates the check-out date of an order.
    public void checkOut(int orderID, Date dateCheckOut) throws Exception {
        Connection connection = getConnection();
        // SQL query to update the check-out date in Orders table
        String sql = "UPDATE Orders SET DateCheckOut = ?  WHERE OrderID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(dateCheckOut));
            ps.setInt(2, orderID);
            ps.executeUpdate();                                                 // Execute update
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
    }

    // Retrieves a list of orders associated with a renter ID.
    public List<Orders> getOrderByRenterID(String renterID,int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Orders> listOrders = new ArrayList<>();
        // SQL query to select orders based on renter ID and order by booking date  
        String sql = "SELECT * FROM Orders WHERE UserID = ? " + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare SQL statement
            ps.setString(1, renterID);
             ps.setInt(2, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute query
            while (rs.next()) {                                                 // Extract data from result set
                int orderID = rs.getInt("OrderID");
                int houseID = rs.getInt("HouserID");
                int userID = rs.getInt("UserID");
                Date bookingDate = rs.getDate("DateBooking");
                int status = rs.getInt("Status");
                Date checkInDate = rs.getDate("DateCheckIn");
                Date checkOutDate = rs.getDate("DateCheckOut");
                listOrders.add(new Orders(orderID, houseID, userID, bookingDate, status, checkInDate, checkOutDate));   // Create Orders object and add to list
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);  // Log error
        } finally {
            connection.close();                                            // Close database connection
        }
        return listOrders;                                                      // Return list of orders
    }
}
