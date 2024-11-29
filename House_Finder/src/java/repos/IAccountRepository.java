/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import java.util.Date;
import java.util.List;
import model.Accounts;

/**
 *
 * @author Admin
 */
public interface IAccountRepository {

    //Retrieve a list of all user accounts.
    List<Accounts> getAllPaging(int pageIndex);
    
    //Retrieve a list of all user accounts.
    List<Accounts> getAll();

    //Retrieve an account by its user ID.
    Accounts getAccountsByID(int userID);

    //Update account information for a specific user.
    void updateAccount(int userID, String email, String lastName, String phone, String address,
            Date dob, String firstName);

    //Update the password of an account.
    void updateAccountPassword(String email, String password);

    //Retrieve an account by email and password.
    Accounts getAccountsByEmailAndPass(String email, String password);

    //Retrieve an account by email.
    Accounts getAccountsByEmail(String email);

    //Deactivate an account based on email and status.
    void deactivateAccount(String email, int status);

    //Retrieve a list of accounts associated with house owners.
    List<Accounts> getAccountsByHouseowner(int pageIndex);

    //Add a new account with specified details.
    void addAccount(String email, String passWord, String firstName, String lastName, String phone, String address, Date dob, int role, int status);

    //Retrieve a list of accounts associated with search by name and role.
    List<Accounts> getAccountBySearchAndFilter(String name, int role,int pageIndex);

    //Update the status of account
    void changeAccountStatus(int userID, int status);
}
