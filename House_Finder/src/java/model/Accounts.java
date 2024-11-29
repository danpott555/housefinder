package model;

import java.util.Date;

/**
 * The Accounts class represents user account information. It includes details
 * such as user ID, email, password, name, phone, address, date of birth, first
 * name, role, and status.
 *
 */
public class Accounts {

    private int userID;         // User's ID
    private String email;       // User's email
    private String password;    // User's password
    private String lastName;    // User's last name
    private String phone;       // User's phone number
    private String address;     // User's address
    private Date dob;           // User's date of birth
    private String firstName;   // User's first name
    private int role;           // User's role (Admin , House Owner , Renter)
    private int status;         // User's status (Active, Inactive)

    /**
     * Default constructor for the Accounts class.
     */
    public Accounts() {
    }

    /**
     * Parameterized constructor for the Accounts class.
     *
     * @param userID The user's ID
     * @param email The user's email
     * @param password The user's password
     * @param lastName The user's last name
     * @param phone The user's phone number
     * @param address The user's address
     * @param dob The user's date of birth
     * @param firstName The user's first name
     * @param role The user's role
     * @param status The user's status
     */
    public Accounts(int userID, String email, String password, String lastName, String phone, String address, Date dob, String firstName, int role, int status) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.firstName = firstName;
        this.role = role;
        this.status = status;
    }

    // Getters and setters for class attributes
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Accounts object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Accounts{" + "userID=" + userID + ", email=" + email + ", password=" + password + ", lastName=" + lastName + ", phone=" + phone + ", address=" + address + ", dob=" + dob + ", firstName=" + firstName + ", role=" + role + ", status=" + status + '}';
    }

}
