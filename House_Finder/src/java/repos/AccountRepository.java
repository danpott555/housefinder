/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repos;

import dal.AccountDAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accounts;

/**
 * The AccountRepository class acts as an intermediary between the business
 * logic and the data access layer for managing accounts.
 *
 * This class provides methods to perform various operations on accounts, such
 * as retrieving, updating, deactivating, and adding accounts.
 *
 * It implements the IAccountRepository interface to define the contract for
 * account-related operations.
 *
 */
public class AccountRepository implements IAccountRepository {

    @Override
    public List<Accounts> getAllPaging(int pageIndex) {
        try {
            return new AccountDAO().getAllPaging(pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Accounts> getAll() {
        try {
            return new AccountDAO().getAll();
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Accounts getAccountsByID(int userID) {
        try {
            return new AccountDAO().getAccountsByID(userID);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deactivateAccount(String email, int status) {
        try {
            new AccountDAO().deactivateAccount(email, status);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAccount(int userID, String email, String lastName, String phone, String address, Date dob, String firstName) {
        try {
            new AccountDAO().updateAccount(email, lastName, phone, address, dob, firstName, userID);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAccountPassword(String email, String password) {
        try {
            new AccountDAO().updateAccountPassword(email, password);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Accounts getAccountsByEmailAndPass(String email, String password) {
        try {
            return new AccountDAO().getAccountsByEmailAndPass(email, password);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Accounts getAccountsByEmail(String email) {
        try {
            return new AccountDAO().getAccountsByEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Accounts> getAccountsByHouseowner(int pageIndex) {
        try {
            return new AccountDAO().getAccountsByHouseowner(pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void addAccount(String email, String passWord, String firstName, String lastName, String phone, String address, Date dob, int role, int status) {
        try {
            new AccountDAO().addAccount(email, passWord, firstName, lastName, phone, address, dob, role, status);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Accounts> getAccountBySearchAndFilter(String name, int role,int pageIndex) {
        try {
            return new AccountDAO().getAccountBySearchAndFilter(name, role, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changeAccountStatus(int userID, int status) {
        try {
            new AccountDAO().changeAccountStatus(userID, status);
        } catch (Exception ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
