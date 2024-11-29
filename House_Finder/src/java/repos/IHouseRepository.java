package repos;

import java.util.List;
import model.Houses;

/**
 *
 * @author SMILY
 */
public interface IHouseRepository {

    //Retrieve a house based on its ID.
    Houses getHouseByHouseId(String houseID);

    //Retrieve a house based on its ID and user ID.
    Houses getHouseByHouseIdAndUserId(String houseID, String userID);

    //Retrieve a list of all houses.
    List<Houses> getAllHousesPaging(int pageIndex);
    
    //Retrieve a list of all houses.
    List<Houses> getAllHouses();

    //Retrieve a list of all available houses.
    List<Houses> getAllAvailableHouses(int pageIndex);

    //Retrieve a list of houses owned by a specific owner.
    List<Houses> getHousesByOwnerID(String ownerID, int pageIndex);

    //Retrieve houses based on search and filter criteria, specific to an owner.
    List<Houses> getHousesBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, int villageID, int hamletID, int status, int pageIndex);

    //Retrieve houses based on general search and filter criteria.
    List<Houses> getHousesBySearchAndFilter(String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex);

    //Change the status of a house.
    void changeHouseStatus(int houseID, int status);

    //Update house details.
    void updateHouse(int houseID, String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image,
            String note, int status, int villageID, int hamletID, int noOfRoom);

    // Add a new house with owner details.
    void addHouseOwner(String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image,
            String note, int status, int villageID, int hamletID, int UserID, int noOfRoom);

    //Change the current room count of a house.
    void changeHouseCurentRoom(int houseID, int currenRoom);

    //Check if a house with a specific name and address exists in a village and hamlet.
    Houses checkHouseExist(String houseName, String address, int villageID, int hamletID);
}
