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
import model.Accounts;

/**
 * This is the class definition for AccountDAO. It appears to be responsible for
 * interacting with the database and performing operations on user accounts.
 */
public class AccountDAO extends DBContext {

    /**
     * Retrieves a list of all accounts from the database.
     *
     * @return A list of Accounts objects.
     * @throws Exception If an error occurs while accessing the database.
     */
    private static final String PAGING_SQL = " ORDER BY UserID Offset ? rows fetch next 10 rows only";

    public List<Accounts> getAllPaging(int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Accounts> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts" + PAGING_SQL;                                  // SQL query to retrieve all accounts from the Accounts table
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement and execute the query
            ps.setInt(1, pageIndex);
            ResultSet rs = ps.executeQuery();                                   // Process the result set and populate the list of accounts
            while (rs.next()) {
                int userID = rs.getInt("UserID");                               // Extract account details from the result set
                String email = rs.getNString("Email");
                String password = rs.getNString("Password");
                String lastName = rs.getNString("LastName");
                String phone = rs.getNString("Phone");
                String address = rs.getNString("Address");
                Date dob = rs.getDate("Dob");
                String firstName = rs.getNString("FirstName");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");

                list.add(new Accounts(userID, email, password, lastName, phone, address, dob, firstName, role, status));         // Create an Accounts object and add it to the list
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);                   // Log any exceptions that occur during database operations
        } finally {
            connection.close();
        }
        return list;
    }

    public List<Accounts> getAll() throws Exception {
        Connection connection = getConnection();
        List<Accounts> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts";                                  // SQL query to retrieve all accounts from the Accounts table
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement and execute the query
            ResultSet rs = ps.executeQuery();                                   // Process the result set and populate the list of accounts
            while (rs.next()) {
                int userID = rs.getInt("UserID");                               // Extract account details from the result set
                String email = rs.getNString("Email");
                String password = rs.getNString("Password");
                String lastName = rs.getNString("LastName");
                String phone = rs.getNString("Phone");
                String address = rs.getNString("Address");
                Date dob = rs.getDate("Dob");
                String firstName = rs.getNString("FirstName");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");

                list.add(new Accounts(userID, email, password, lastName, phone, address, dob, firstName, role, status));         // Create an Accounts object and add it to the list
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);                   // Log any exceptions that occur during database operations
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Retrieves an account from the database by the given userID.
     *
     * @param userID The ID of the account to retrieve.
     * @return An Accounts object representing the retrieved account, or null if
     * not found.
     * @throws Exception If an error occurs while accessing the database.
     */
    public Accounts getAccountsByID(int userID) throws Exception {
        Connection connection = getConnection();
        String sql = "SELECT * FROM Accounts WHERE UserID=?";                   // SQL query to retrieve an account by the provided userID
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and retrieve the account (if found)
                Accounts account = extractAccountFromResultSet(rs);             // Extract account details from the result set using the extractAccountFromResultSet method
                return account;                                                 // Return the retrieved account
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return null;                                                            // Return null if no account with the provided userID was found
    }

    /**
     * Extracts account details from a ResultSet and constructs an Accounts
     * object.
     *
     * @param rs The ResultSet containing account data.
     * @return An Accounts object representing the extracted account.
     * @throws Exception If an error occurs while extracting data from the
     * ResultSet.
     */
    private Accounts extractAccountFromResultSet(ResultSet rs) throws Exception {
        int userID = rs.getInt("UserID");                                       // Extract individual fields from the result set
        String email = rs.getNString("Email");
        String password = rs.getNString("Password");
        String lastName = rs.getNString("LastName");
        String phone = rs.getNString("Phone");
        String address = rs.getNString("Address");
        Date dob = rs.getDate("Dob");
        String firstName = rs.getNString("FirstName");
        int role = rs.getInt("Role");
        int status = rs.getInt("Status");

        return new Accounts(userID, email, password, lastName, phone, address, dob, firstName, role, status);   // Create and return a new Accounts object using the extracted data
    }

    /**
     * Updates account information in the database for the given userID.
     *
     * @param email The new email for the account.
     * @param lastName The new last name for the account.
     * @param phone The new phone number for the account.
     * @param address The new address for the account.
     * @param dob The new date of birth for the account.
     * @param firstName The new first name for the account.
     * @param userID The userID of the account to update.
     * @throws Exception If an error occurs while updating the account in the
     * database.
     */
    public void updateAccount(String email, String lastName, String phone, String address, Date dob, String firstName, int userID) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Accounts SET email=?, lastName=?, phone=?, address=?, " // SQL query to update account information in the database
                + "dob=?, firstName=? WHERE userID=?";
        try {
            Connection conn = new DBContext().getConnection();                  // Get a database connection
            PreparedStatement ps = conn.prepareStatement(sql);                  // Create a PreparedStatement to execute the update query
            ps.setString(1, email);                                             // Set parameters for the update query
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(dob));
            ps.setString(6, firstName);
            ps.setInt(7, userID);

            ps.executeUpdate();                                                 // Execute the update query
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }

    /**
     * Updates the password of an account in the database.
     *
     * @param email The email of the account to update.
     * @param password The new password for the account.
     * @throws Exception If an error occurs while updating the password in the
     * database.
     */
    public void updateAccountPassword(String email, String password) throws Exception {
        Connection connection = getConnection();
        // SQL query to update the password of an account in the database
        String sql = "UPDATE Accounts SET password=? WHERE email = ?";
        try {
            Connection conn = new DBContext().getConnection();                  // Get a database connection
            PreparedStatement ps = conn.prepareStatement(sql);                  // Create a PreparedStatement to execute the update query
            ps.setString(1, password);                                          // Set parameters for the update query
            ps.setString(2, email);
            ps.executeUpdate();                                                 // Execute the update query
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }

    /**
     * Retrieves an account by email and password from the database.
     *
     * @param email The email of the account to retrieve.
     * @param password The password of the account to retrieve.
     * @return An Accounts object representing the retrieved account, or null if
     * not found.
     * @throws Exception If an error occurs while accessing the database.
     */
    public Accounts getAccountsByEmailAndPass(String email, String password) throws Exception {
        Connection connection = getConnection();
        String sql = "SELECT * FROM Accounts WHERE Email=? and Password=?";     // SQL query to retrieve an account by email and password

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and retrieve the account (if found)
                Accounts account = extractAccountFromResultSet(rs);             // Extract account details from the result set using the extractAccountFromResultSet method
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return null;                                                            // Return null if no account with the provided email and password was found
    }

    /**
     * Retrieves an account by email from the database.
     *
     * @param email The email of the account to retrieve.
     * @return An Accounts object representing the retrieved account, or null if
     * not found.
     * @throws Exception If an error occurs while accessing the database.
     */
    public Accounts getAccountsByEmail(String email) throws Exception {
        Connection connection = getConnection();
        String sql = "SELECT * FROM Accounts WHERE Email=? ";                   // SQL query to retrieve an account by email

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and retrieve the account (if found)
                Accounts account = extractAccountFromResultSet(rs);             // Extract account details from the result set using the extractAccountFromResultSet method
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return null;                                                            // Return null if no account with the provided email was found
    }

    /**
     * Deactivates an account by updating its status in the database.
     *
     * @param email The email of the account to deactivate.
     * @param status The new status to set for the account.
     * @throws Exception If an error occurs while updating the account status in
     * the database.
     */
    public void deactivateAccount(String email, int status) throws Exception {
        Connection connection = getConnection();
        // SQL query to update the status of an account by email
        String sql = "UPDATE Accounts SET Status=? WHERE Email=?";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the update query
            ps.setInt(1, status);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();                               // Execute the update query and get the number of rows updated
            if (rowsUpdated > 0) {
                System.out.println("Account deactivated successfully.");
            } else {
                System.out.println("No account found with the given email.");
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);   // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }

    /**
     * Retrieves a list of accounts with the role of house owners from the
     * database.
     *
     * @return A list of Accounts objects representing house owners.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Accounts> getAccountsByHouseowner(int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Accounts> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts where role=2 " + PAGING_SQL;                     // SQL query to retrieve accounts with the role of house owners

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setInt(1, pageIndex);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and populate the list of house owner accounts
                int userID = rs.getInt("UserID");
                String email = rs.getNString("Email");
                String password = rs.getNString("Password");
                String lastName = rs.getNString("LastName");
                String phone = rs.getNString("Phone");
                String address = rs.getNString("Address");
                Date dob = rs.getDate("Dob");
                String firstName = rs.getNString("FirstName");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");

                list.add(new Accounts(userID, email, password, lastName, phone, address, dob, firstName, role, status));        // Add the retrieved account to the list
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);       // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return list;
    }

    /**
     * Adds a new account to the database.
     *
     * @param email The email for the new account.
     * @param passWord The password for the new account.
     * @param firstName The first name for the new account.
     * @param lastName The last name for the new account.
     * @param phone The phone number for the new account.
     * @param address The address for the new account.
     * @param dob The date of birth for the new account.
     * @param role The role for the new account.
     * @param status The status for the new account.
     * @throws Exception If an error occurs while adding the account to the
     * database.
     */
    public void addAccount(String email, String passWord, String firstName, String lastName, String phone, String address, Date dob, int role, int status) throws Exception {
        Connection connection = getConnection();
        // SQL query to insert a new account into the database
        String sql = "INSERT INTO Accounts(Email ,Password ,LastName, Phone , Address , DOB , FirstName , Role , Status) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the insert query
            ps.setString(1, email);                                             // Set parameters for the insert query
            ps.setString(2, passWord);
            ps.setString(3, lastName);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(dob));
            ps.setString(7, firstName);
            ps.setInt(8, role);
            ps.setInt(9, status);
            ps.executeUpdate();                                                 // Execute the insert query
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);    // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }

    /**
     * Retrieves a list of accounts based on a search name and an optional role
     * filter.
     *
     * @param name The name to search for in account first name or last name.
     * @param _role The role to filter by (0 to ignore filtering).
     * @return A list of Accounts objects matching the search criteria.
     * @throws Exception If an error occurs while accessing the database.
     */
    public List<Accounts> getAccountBySearchAndFilter(String name, int _role, int pageIndex) throws Exception {
        Connection connection = getConnection();
        List<Accounts> listaccounts = new ArrayList<>();
        String sql = "SELECT * FROM Accounts WHERE (FirstName LIKE ? OR LastName LIKE ?)";  // Construct the base SQL query for searching by name
        if (_role != 0) {                                                        // If a role filter is provided, add it to the SQL query
            sql += " And role = ?";
        }
        sql += PAGING_SQL;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the query
            ps.setNString(1, "%" + name + "%");
            ps.setNString(2, "%" + name + "%");
            if (_role != 0) {                                                   // If a role filter is provided, set its value in the PreparedStatement
                ps.setInt(3, _role);
                ps.setInt(4, pageIndex);
            } else {
                ps.setInt(3, pageIndex);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                                                 // Process the result set and populate the list of matching accounts  
                listaccounts.add(extractAccountFromResultSet(rs));              // Add the retrieved account to the list
            }
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);         // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
        return listaccounts;
    }

    /**
     * Changes the status of an account by updating its status in the database.
     *
     * @param userID The userID of the account to update.
     * @param status The new status to set for the account.
     * @throws Exception If an error occurs while updating the account status in
     * the database.
     */
    public void changeAccountStatus(int userID, int status) throws Exception {
        Connection connection = getConnection();
        String sql = "UPDATE Accounts SET Status = ? WHERE userID = ?";         // SQL query to update the status of an account by userID
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);       // Create a PreparedStatement to execute the update query
            ps.setInt(1, status);
            ps.setInt(2, userID);
            ps.execute();                                                       // Execute the update query
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);    // Log any exceptions that occur during database operations
        } finally {
            connection.close();                                            // Close the database connection in the finally block to ensure it's closed even in case of an exception
        }
    }
}
