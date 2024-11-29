package repos;

import dal.HamletDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Hamlets;

/**
 * The HamletRepository class acts as an intermediary between the business logic
 * and the data access layer for managing hamlets.
 *
 * This class provides methods to perform various operations on hamlets, such as
 * retrieving all hamlets, retrieving hamlets by village ID, and more.
 *
 * It implements the IHamletRepository interface to define the contract for
 * hamlet-related operations.
 *
 */
public class HamletRepository implements IHamletRepository{

    @Override
    public List<Hamlets> getAll() {
        try {
            return new HamletDAO().getAll();
        } catch (Exception ex) {
            Logger.getLogger(HamletRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Hamlets> getHamletsByVillageID(int villageID) {
        try {
            return new HamletDAO().getHamletsByVillageID(villageID);
        } catch (Exception ex) {
            Logger.getLogger(HamletRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
