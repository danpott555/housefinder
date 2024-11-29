package repos;

import java.util.List;
import model.Hamlets;

/**
 *
 * @author SMILY
 */
public interface IHamletRepository {

    //Retrieve a list of all hamlets.
    List<Hamlets> getAll();

    //Retrieve a list of hamlets associated with a specific village ID.
    List<Hamlets> getHamletsByVillageID(int villageID);
}
