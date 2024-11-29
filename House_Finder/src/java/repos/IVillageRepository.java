package repos;

import java.util.List;
import model.Villages;

/**
 *
 * @author SMILY
 */
public interface IVillageRepository {
    //Retrieve a list of all villages.
    List<Villages> getAll();
}
