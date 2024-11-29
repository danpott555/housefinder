package repos;

import dal.VillageDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Villages;

/**
 * The VillageRepository class acts as an intermediary between the business logic
 * and the data access layer for managing villages.
 *
 * This class provides methods to perform various operations on villages, such as
 * retrieving all villages.
 *
 * It implements the IVillageRepository interface to define the contract for
 * village-related operations.
 *
 */
public class VillageRepository implements IVillageRepository{

    @Override
    public List<Villages> getAll() {
        try {
            return new VillageDAO().getAll();
        } catch (Exception ex) {
            Logger.getLogger(VillageRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
