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
import model.Comments;

/**
 * Data Access Object (DAO) class for managing comments related database operations.
 * This class provides methods to retrieve and add comments to the database.
 * @author ADMIN
 */
public class CommentDAO extends DBContext {

    /**
     * Retrieves a list of comments associated with a specific house.
     *
     * @param houseID The ID of the house to retrieve comments for.
     * @return A list of Comments objects for the given house.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Comments> getCommentByHouseID(int houseID) throws Exception {
        Connection connection = getConnection();
        List<Comments> listComments = new ArrayList<>();
        String sql = "SELECT * FROM Comments WHERE HouseID = ? ORDER BY DateComment DESC";      // SQL query to retrieve comments for a specific house, ordered by date in descending order
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setInt(1, houseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and populate the list of comments
                int commentID = rs.getInt("CommentID");
                String commnet = rs.getString("Comment");
                double rate = rs.getDouble("Rate");
                Date dateComment = rs.getDate("DateComment");
                int userID = rs.getInt("UserID");
                listComments.add(new Comments(commentID, commnet, rate, dateComment, userID, houseID));
            }
        } catch (Exception e) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, e);            // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return listComments;
    }

    /**
     * Adds a new comment to the database.
     *
     * @param comment The comment text.
     * @param rate The rating given by the user.
     * @param dateComment The date when the comment was posted.
     * @param userID The ID of the user who posted the comment.
     * @param houseID The ID of the house the comment is associated with.
     * @throws Exception If an error occurs while adding the comment to the database.
     */
    public void addCommenr(String comment, double rate, Date dateComment, int userID, int houseID) throws Exception {
        Connection connection = getConnection();
        String sql = "INSERT INTO Comments (Comment,Rate,DateComment,UserID,HouseID) VALUES (?,?,?,?,?)";   // SQL query to insert a new comment into the database
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the insert query
            ps.setNString(1, comment);
            ps.setDouble(2, rate);
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(dateComment));
            ps.setInt(4, userID);
            ps.setInt(5, houseID);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, e);        // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }
}
