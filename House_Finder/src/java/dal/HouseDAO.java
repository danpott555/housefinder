package dal;

import java.sql.Connection;
import model.Houses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for managing House-related database operations.
 */
public class HouseDAO extends DBContext {
    private static final String PAGING_SQL = " ORDER BY HouseID Offset ? rows fetch next 10 rows only";

    public Houses getHouseByHouseId(String houseID) throws Exception {
        Connection connection = getConnection();
        // SQL query to select a house based on its HouseID
        String sql = "SELECT * FROM Houses WHERE HouseID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setString(1, houseID);                                           // Set the HouseID parameter in the query
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            if (rs.next()) {                                                    // If a house with the provided HouseID exists in the result set
                String houseName = rs.getNString("HouseName");                  // Extract various attributes of the house from the result set
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                return new Houses(Integer.parseInt(houseID), houseName, address, // Create a new Houses object with the extracted attributes
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom);
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during the process
        } finally {
            connection.close();                                             // Close the database connection in all cases (success or failure)
        }
        return null;                                                            // If no matching house is found, return null
    }

    public Houses getHouseByHouseIdAndUserId(String houseID, String userID) throws Exception {
        Connection connection = getConnection();
        // SQL query to select a house based on its HouseID and UserID
        String sql = "SELECT * FROM Houses WHERE HouseID = ? AND UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);   // Prepare the SQL statement
            ps.setString(1, houseID);                                       // Set the HouseID and UserID parameters in the query
            ps.setString(2, userID);
            ResultSet rs = ps.executeQuery();                               // Execute the query and get the result set    
            if (rs.next()) {                                                // If a house with the provided HouseID and UserID exists in the result set
                String houseName = rs.getNString("HouseName");              // Extract various attributes of the house from the result set
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int noOfRoom = rs.getInt("NoOfRoom");
                return new Houses(Integer.parseInt(houseID), houseName, address, // Create a new Houses object with the extracted attributes
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, Integer.parseInt(userID), noOfRoom);
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur during the process
        } finally {
            connection.close();                                            // Close the database connection in all cases (success or failure)
        }
        return null;                                                            // If no matching house is found, return null
    }

    public List<Houses> getAllHousesPaging(int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        // SQL query to select all houses
        String sql = "SELECT * FROM Houses" + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and create Houses objects for each row
                int houseID = rs.getInt("HouseID");
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Add the created Houses object to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);   // Log any exceptions that occur during the process
        } finally {
            connection.close();                                             // Close the database connection in all cases (success or failure)
        }
        return listHouses;                                                      // Return the list of Houses
    }
    
        public List<Houses> getAllHouses() throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        // SQL query to select all houses
        String sql = "SELECT * FROM Houses";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and create Houses objects for each row
                int houseID = rs.getInt("HouseID");
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Add the created Houses object to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);   // Log any exceptions that occur during the process
        } finally {
            connection.close();                                             // Close the database connection in all cases (success or failure)
        }
        return listHouses;                                                      // Return the list of Houses
    }

    public List<Houses> getAllAvailableHouses(int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        String sql = "SELECT * FROM Houses WHERE Status = 1" + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setInt(1, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute the query
            while (rs.next()) {
                int houseID = rs.getInt("HouseID");                             // Retrieve data from the result set
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Create a Houses object and add to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur
        } finally {
            connection.close();                                             // Close the database connection
        }
        return listHouses;
    }

    public List<Houses> getHousesByOwnerID(String ownerID, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        String sql = "SELECT * FROM Houses WHERE UserID = ?" + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setString(1, ownerID);                                           // Set the parameter for UserID in the prepared statement
            ps.setInt(2, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Execute the query
            while (rs.next()) {
                int houseID = rs.getInt("HouseID");                             // Retrieve data from the result set
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Create a Houses object and add to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listHouses;
    }

    public List<Houses> getHousesBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        int indexOfVillageID = 0;
        int indexOfHamletID = 0;
        int indexOfStatus = 0;
        int lastIndex = 2;
        String sql = "SELECT * FROM Houses WHERE UserID = ? AND HouseName LIKE ?";  // Start building the SQL query
        if (_villageID != 0) {                                                  // Check for filters and modify the SQL query accordingly
            sql += " AND VillageID = ?";
            indexOfVillageID = ++lastIndex;
            if (_hamletID != 0) {
                sql += " AND HamletID = ?";
                indexOfHamletID = ++lastIndex;
            }
        }

        if (_status != -1) {
            sql += " AND Status = ?";
            indexOfStatus = ++lastIndex;
        }
        
        sql += PAGING_SQL;

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, ownerID);
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

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Retrieve data from the result set
                int houseID = rs.getInt("HouseID");
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Create a Houses object and add to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listHouses;
    }

    public List<Houses> getHousesBySearchAndFilter(String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Houses> listHouses = new ArrayList<>();
        int indexOfVillageID = 0;
        int indexOfHamletID = 0;
        int indexOfStatus = 0;
        int lastIndex = 1;
        // Start building the SQL query
        String sql = "SELECT * FROM Houses WHERE HouseName LIKE ?";
        // Check for filters and modify the SQL query accordingly
        if (_villageID != 0) {
            sql += " AND VillageID = ?";
            indexOfVillageID = ++lastIndex;
            if (_hamletID != 0) {
                sql += " AND HamletID = ?";
                indexOfHamletID = ++lastIndex;
            }
        }

        if (_status != -1) {
            sql += " AND Status = ?";
            indexOfStatus = ++lastIndex;
        }
        
        sql += PAGING_SQL;

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setNString(1, "%" + nameSearch + "%");

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
                int houseID = rs.getInt("HouseID");                             // Retrieve data from the result set
                String houseName = rs.getNString("HouseName");
                String address = rs.getNString("Address");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int villageID = rs.getInt("VillageID");
                int hamletID = rs.getInt("HamletID");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                listHouses.add(new Houses(houseID, houseName, address, // Create a Houses object and add to the list
                        distanceToCampus, powerPrice, waterPrice, price,
                        fingerPrintLock, camera, numberOfRoom, image, note,
                        status, villageID, hamletID, userID, noOfRoom));
            }
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // Log any exceptions that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listHouses;
    }

    public void changeHouseStatus(int houseID, int status) throws Exception {
        Connection connection = getConnection();
        // Define the SQL update query to change the house status
        String sql = "UPDATE Houses SET Status = ? WHERE HouseID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement with the SQL query
            ps.setInt(1, status);                                               // Set the values for the placeholders in the SQL query
            ps.setInt(2, houseID);
            ps.execute();                                                       // Execute the update query
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // If an exception occurs, log the details for debugging
        } finally {
            connection.close();                                            // Regardless of success or failure, ensure the database connection is closed
        }
    }

    public void changeHouseCurentRoom(int houseID, int currenRoom) throws Exception {
        Connection connection = getConnection();
        // Define the SQL update query to change the current room count of the house
        String sql = "UPDATE Houses SET NoOfRoom = ? WHERE HouseID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);        // Create a PreparedStatement with the SQL query
            ps.setInt(1, currenRoom);                                            // Set the values for the placeholders in the SQL query
            ps.setInt(2, houseID);
            ps.execute();                                                       // Execute the update query
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // If an exception occurs, log the details for debugging
        } finally {
            connection.close();                                            // Regardless of success or failure, ensure the database connection is closed
        }
    }

    public void updateHouseOwner(int houseID, String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image,
            String note, int status, int villageID, int hamletID, int noOfRoom) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Houses SET HouseName = ?, Address = ?, DistanceToCampus = ?, PowerPrice = ?, WaterPrice = ?, "
                + "Price = ?, FingerPrintLock = ?, Camera = ?, NumberOfRoom = ?, Image = ?, Note = ?, Status = ?, "
                + "VillageID = ?, HamletID = ?, NoOfRoom = ? "
                + "WHERE HouseID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setNString(1, houseName);
            ps.setNString(2, address);
            ps.setFloat(3, distanceToCampus);
            ps.setFloat(4, powerPrice);
            ps.setFloat(5, waterPrice);
            ps.setFloat(6, price);
            ps.setBoolean(7, fingerPrintLock);
            ps.setBoolean(8, camera);
            ps.setInt(9, numberOfRoom);
            ps.setNString(10, image);
            ps.setNString(11, note);
            ps.setInt(12, status);
            ps.setInt(13, villageID);
            ps.setInt(14, hamletID);
            ps.setInt(15, noOfRoom);
            ps.setInt(16, houseID);
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.close();
        }
    }

    public void addHouseOwner(String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image,
            String note, int status, int villageID, int hamletID, int UserID, int noOfRoom) throws Exception {
        Connection connection = getConnection();
        // Define the SQL update query to update the house's owner and properties
        String sql = "INSERT INTO Houses(HouseName,Address,DistanceToCampus,PowerPrice,WaterPrice,"
                + "Price,FingerPrintLock,Camera,NumberOfRoom,Image,Note,Status,VillageID,HamletID,UserID,NoOfRoom)\n"
                + "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement with the SQL query
            ps.setNString(1, houseName);                                        // Set the values for the placeholders in the SQL query
            ps.setNString(2, address);
            ps.setFloat(3, distanceToCampus);
            ps.setFloat(4, powerPrice);
            ps.setFloat(5, waterPrice);
            ps.setFloat(6, price);
            ps.setBoolean(7, fingerPrintLock);
            ps.setBoolean(8, camera);
            ps.setInt(9, numberOfRoom);
            ps.setNString(10, image);
            ps.setNString(11, note);
            ps.setInt(12, status);
            ps.setInt(13, villageID);
            ps.setInt(14, hamletID);
            ps.setInt(15, UserID);
            ps.setInt(16, noOfRoom);
            ps.executeUpdate();                                                 // Execute the update query
        } catch (Exception e) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, e);  // If an exception occurs, log the details for debugging
        } finally {
            connection.close();                                            // Regardless of success or failure, ensure the database connection is closed
        }
    }

    public Houses checkHouseExist(String houseName, String address, int villageID, int hamletID) throws Exception {
        Connection connection = getConnection();
        String sql = "SELECT * FROM Houses WHERE HouseName=? AND Address = ? AND VillageID = ? AND HamletID = ? ";

        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement with the SQL query
            ps.setString(1, houseName);
            ps.setString(2, address);
            ps.setInt(3, villageID);
            ps.setInt(4, hamletID);
            ResultSet rs = ps.executeQuery();                                   // Execute the query
            while (rs.next()) {
                int houseID = rs.getInt("HouseID");
                float distanceToCampus = rs.getFloat("DistanceToCampus");
                float powerPrice = rs.getFloat("PowerPrice");
                float waterPrice = rs.getFloat("WaterPrice");
                float price = rs.getFloat("Price");
                boolean fingerPrintLock = rs.getBoolean("FingerPrintLock");
                boolean camera = rs.getBoolean("Camera");
                int numberOfRoom = rs.getInt("NumberOfRoom");
                String image = rs.getNString("Image");
                String note = rs.getNString("Note");
                int status = rs.getInt("Status");
                int userID = rs.getInt("UserID");
                int noOfRoom = rs.getInt("NoOfRoom");
                // Create a Houses object with the retrieved attributes
                Houses houses = new Houses(houseID, houseName, address, distanceToCampus, powerPrice, waterPrice, price, fingerPrintLock, camera, numberOfRoom, image, note, status, hamletID, villageID, userID, noOfRoom);
                return houses;                                                  // Return the existing house
            }
        } catch (Exception ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex); // If an exception occurs, log the details for debugging
        } finally {
            connection.close();                                            // Regardless of success or failure, ensure the database connection is closed
        }
        return null;                                                            // Return null if the house doesn't exist
    }

}
