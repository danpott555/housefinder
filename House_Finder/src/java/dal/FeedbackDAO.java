package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FeedbackOptions;
import model.Feedbacks;

/**
 * Data Access Object (DAO) for managing Feedbacks and FeedbackOptions. This
 * class provides methods to interact with the database and perform various
 * operations related to feedbacks.
 */
public class FeedbackDAO extends DBContext {

    /**
     * Retrieves a list of feedbacks along with related information.
     *
     * @return A list of Feedbacks objects with additional information.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    private static final String PAGING_SQL = " ORDER BY FeedbackId Offset ? rows fetch next 10 rows only";
    public List<Feedbacks> getAllPaging() throws Exception {
        Connection connection = getConnection();
        List<Feedbacks> listFeedback = new ArrayList<>();
        // SQL query to retrieve feedbacks with related information
        String sql = "select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, fo.OptionName, a.LastName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "left join Accounts a on f.FeedbackUserId = a.UserID";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Extract data from the result set
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                String userName = rs.getNString("LastName");
                // Create and add Feedbacks objects to the list
                listFeedback.add(new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName, userName));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listFeedback;                                                    // Return the list of feedbacks
    }
    
        /**
     * Retrieves a list of feedbacks along with related information.
     *
     * @return A list of Feedbacks objects with additional information.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public List<Feedbacks> getAll() throws Exception {
        Connection connection = getConnection();
        List<Feedbacks> listFeedback = new ArrayList<>();
        // SQL query to retrieve feedbacks with related information
        String sql = "select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, fo.OptionName, a.LastName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "left join Accounts a on f.FeedbackUserId = a.UserID";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Extract data from the result set
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                String userName = rs.getNString("LastName");
                // Create and add Feedbacks objects to the list
                listFeedback.add(new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName, userName));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listFeedback;                                                    // Return the list of feedbacks
    }

    /**
     * Retrieves a list of feedbacks associated with a specific user ID.
     *
     * @param userId The ID of the user to retrieve feedbacks for.
     * @return A list of Feedbacks objects for the given user ID.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public List<Feedbacks> getFeedbacksByUserId(int userId, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Feedbacks> listFeedback = new ArrayList<>();
        // SQL query to retrieve feedbacks associated with the given user ID
        String sql = "select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, fo.OptionName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "where f.FeedbackUserId = ?" + PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, userId);                                               // Set the user ID parameter in the prepared statement
            ps.setInt(2, pageIndex);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Extract data from the result set
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                // Create a new Feedbacks object and add it to the list
                listFeedback.add(new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listFeedback;                                                    // Return the list of feedbacks
    }

    /**
     * Retrieves a Feedbacks object associated with a specific feedback ID.
     *
     * @param feedbackIdParam The ID of the feedback to retrieve.
     * @return A Feedbacks object for the given feedback ID, or null if not
     * found.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public Feedbacks getFeedbacksByFeedbackId(int feedbackIdParam) throws Exception {
        Connection connection = getConnection();
        String sql = "select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, f.FeedbackStatus, fo.OptionName, a.LastName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "left join Accounts a on f.FeedbackUserId = a.UserID\n"
                + "where f.FeedbackId = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, feedbackIdParam);                                      // Set the feedback ID parameter in the prepared statement
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                                                    // Extract data from the result set
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                String userName = rs.getNString("LastName");
                boolean status = rs.getBoolean("FeedbackStatus");
                // Create a new Feedbacks object and return it
                return new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName, userName, status);
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e); // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return null;                                                            // If feedback not found, return null
    }

    /**
     * Replies to a feedback by updating the feedback's reply content.
     *
     * @param content The reply content to be added.
     * @param feedbackId The ID of the feedback to reply to.
     * @return true if the reply was successful, false otherwise.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public boolean replyFeedback(String content, int feedbackId) throws Exception {
        Connection connection = getConnection();
        String sql = "update Feedbacks\n"
                + "set FeedbackReply = ? \n"
                + "where FeedbackId = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, content);                                           // Set the content and feedback ID parameters in the prepared statement
            ps.setInt(2, feedbackId);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;                                                    // Reply was successful
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return false;                                                           // Reply was not successful
    }

    /**
     * Searches for feedbacks by their content using a search parameter.
     *
     * @param searchParam The search parameter used to find matching feedbacks.
     * @return A list of Feedbacks objects that match the search criteria.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public List<Feedbacks> searchFeedbackByContent(String searchParam) throws Exception {
        Connection connection = getConnection();
        List<Feedbacks> listFeedback = new ArrayList<>();

        String sql = "select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, fo.OptionName, a.LastName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "left join Accounts a on f.FeedbackUserId = a.UserID\n"
                + "where f.FeedbackContent like ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + searchParam + "%");                           // Set the search parameter in the prepared statement
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Extract data from the result set
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                String userName = rs.getNString("LastName");
                // Create a new Feedbacks object and add it to the list
                listFeedback.add(new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName, userName));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);     // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return listFeedback;
    }

    /**
     * Adds a new feedback to the database.
     *
     * @param optionId The ID of the feedback option.
     * @param userId The ID of the user submitting the feedback.
     * @param content The content of the feedback.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public void addFeedback(int optionId, int userId, String content) throws Exception {
        Connection connection = getConnection();
        String sql = "INSERT INTO Feedbacks(FeedbackOptionId, FeedbackUserId, FeedbackContent, FeedbackStatus) VALUES(?,?,?,1)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, optionId);                                             // Set the option ID, user ID, and content parameters in the prepared statement
            ps.setInt(2, userId);
            ps.setNString(3, content);
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);       // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
    }

    /**
     * Updates the reply content of a feedback.
     *
     * @param feedbackId The ID of the feedback to update.
     * @param replyContent The new reply content to set for the feedback.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public void updateReplyFeedback(int feedbackId, String replyContent) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Feedbacks SET FeedbackReply = ? WHERE FeedbackId = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setNString(1, replyContent);                                     // Set the reply content and feedback ID in the prepared statement
            ps.setInt(2, feedbackId);
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);   // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
    }

    /**
     * Retrieves a list of all available feedback options.
     *
     * @return A list of FeedbackOptions objects representing available options.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public List<FeedbackOptions> getAllFeedbackOptions() throws Exception {
        Connection connection = getConnection();
        List<FeedbackOptions> feedbackOptions = new ArrayList<>();
        String sql = "SELECT * FROM FeedbackOptions";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Extract feedback option data from the result set
                feedbackOptions.add(new FeedbackOptions(rs.getInt("OptionID"), rs.getNString("OptionName")));
            }
        } catch (Exception e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);       // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
        return feedbackOptions;
    }

    /**
     * Retrieves a feedback by its ID.
     *
     * @param id The ID of the feedback to retrieve.
     * @return A Feedbacks object representing the retrieved feedback, or null
     * if not found.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public Feedbacks getFeedbackById(int id) throws Exception {
        Connection connection = getConnection();
        Feedbacks feedback = null;
        String sql = "SELECT * FROM Feedbacks WHERE FeedbackId = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);                                                    // Set the feedback ID in the prepared statement
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Extract feedback data from the result set
                feedback = new Feedbacks(id, rs.getInt("FeedbackOptionId"), rs.getInt("FeedbackUserId"),
                        rs.getNString("FeedbackContent"), rs.getNString("FeedbackReply"), rs.getBoolean("FeedbackStatus"));
            }
        } catch (Exception e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);       // Log any SQL errors that occur
        } finally {
            connection.close();                                             // Close the database connection
        }
        return feedback;
    }

    /**
     * Changes the status of a feedback to inactive.
     *
     * @param feedbackId The ID of the feedback to change the status for.
     * @throws SQLException If an SQL error occurs while accessing the database.
     */
    public void changeStatus(int feedbackId) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Feedbacks SET FeedbackStatus = 0 WHERE FeedbackId = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, feedbackId);                                           // Set the feedback ID in the prepared statement
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, e);       // Log any SQL errors that occur
        } finally {
            connection.close();                                            // Close the database connection
        }
    }

    public List<Feedbacks> searchFeedbackByContent(String search_param, String search_by, String option_value) throws Exception {
        Connection connection = getConnection();
        List<Feedbacks> listFeedback = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        sb.append("select f.FeedbackId, f.FeedbackContent, f.FeedbackOptionId, f.FeedbackReply, f.FeedbackUserId, fo.OptionName, a.LastName\n"
                + "from Feedbacks f inner join FeedbackOptions fo on f.FeedbackOptionId = fo.OptionID\n"
                + "left join Accounts a on f.FeedbackUserId = a.UserID");

        if ("feedback_content".equals(search_by)) {
            sb.append(" where f.FeedbackContent like ?");
        }

        if ("reply_feedback".equals(search_by)) {
            sb.append(" where f.FeedbackReply like ?");
        }

        if ("user_name".equals(search_by)) {
            sb.append(" where a.LastName like ?");
        }

        if (!option_value.isEmpty()) {
            sb.append(" and fo.OptionID = ?");
        }

        try {
            PreparedStatement ps = getConnection().prepareStatement(sb.toString());
            ps.setString(1, "%" + search_param + "%");
            if (!option_value.isEmpty()) {
                ps.setInt(2, Integer.parseInt(option_value));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("FeedbackId");
                String feedbackContent = rs.getNString("FeedbackContent");
                String feedbackReply = rs.getNString("FeedbackReply");
                int feedbacOptionId = rs.getInt("FeedbackOptionId");
                int feedbacdUserId = rs.getInt("FeedbackOptionId");
                String optionName = rs.getNString("OptionName");
                String userName = rs.getNString("LastName");

                listFeedback.add(new Feedbacks(feedbackId, feedbacOptionId, feedbacdUserId, feedbackContent, feedbackReply, optionName, userName));
            }
        } catch (Exception e) {
            Logger.getLogger(HamletDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            connection.close();
        }
        return listFeedback;
    }
}
