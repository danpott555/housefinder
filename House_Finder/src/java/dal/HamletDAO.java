package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Hamlets;

/**
 * Data Access Object for interacting with the Hamlets table in the database.
 * Contains methods to retrieve hamlet information.
 * Extends the DBContext class to access the database connection.
 *
 */
public class HamletDAO extends DBContext{
    
    /**
     * Retrieves a list of all hamlets.
     *
     * @return A list of Hamlets objects representing all hamlets.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Hamlets> getAll() throws Exception{
        Connection connection = getConnection();
        List<Hamlets> listHamlets = new ArrayList<>();
        String sql = "SELECT * FROM Hamlets";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Hamlets
                int hamletID = rs.getInt("HamletID");
                String hamletName = rs.getNString("HamletName");
                int villageID = rs.getInt("VillageID");
                listHamlets.add(new Hamlets(hamletID, hamletName, villageID));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e); // Log any exceptions that occur during database access
        } finally {
            connection.close();                                           // Close the database connection regardless of success or failure
        }
        return listHamlets;
    }
    
        /**
     * Retrieves a list of hamlets belonging to a specific village.
     *
     * param villageID The ID of the village for which to retrieve hamlets.
     * return A list of Hamlets objects representing hamlets in the village.
     * throws Exception If an error occurs while accessing the database.
     */
    public List<Hamlets> getHamletsByVillageID(int villageID) throws Exception{
        Connection connection = getConnection();
        List<Hamlets> listHamlets = new ArrayList<>();
        String sql = "SELECT * FROM Hamlets WHERE VillageID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ps.setInt(1, villageID);                                            // Set the parameter value for the prepared statement
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Hamlets  
                int hamletID = rs.getInt("HamletID");
                String hamletName = rs.getNString("HamletName");
                listHamlets.add(new Hamlets(hamletID, hamletName, villageID));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any exceptions that occur during database access
        } finally {
            connection.close();                                            // Close the database connection regardless of success or failure
        }
        return listHamlets;
    }
   
}
