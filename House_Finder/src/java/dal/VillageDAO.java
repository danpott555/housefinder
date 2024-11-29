package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Villages;

/**
 * Data Access Object for interacting with the Villages table in the database.
 * Contains methods to retrieve village information. Extends the DBContext class
 * to access the database connection.
 *
 */
public class VillageDAO extends DBContext {

    /**
     * Retrieves a list of all villages.
     *
     * return A list of Villages objects representing all villages. throws
     * Exception If an error occurs while accessing the database.
     */
    public List<Villages> getAll() throws Exception {
        Connection connection = getConnection();
        List<Villages> listVillages = new ArrayList<>();
        String sql = "SELECT * FROM Villages";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Prepare the SQL statement
            ResultSet rs = ps.executeQuery();                                   // Execute the query and get the result set
            while (rs.next()) {                                                 // Iterate through the result set and populate the list of Villages
                int villageID = rs.getInt("VillageID");
                String villageName = rs.getNString("VillageName");
                listVillages.add(new Villages(villageID, villageName));
            }
        } catch (Exception e) {
            Logger.getLogger(VillageDAO.class.getName()).log(Level.SEVERE, null, e);        // Log any exceptions that occur during database access
        } finally {
            connection.close();                                             // Close the database connection regardless of success or failure
        }
        return listVillages;
    }

}
