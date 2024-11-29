package repos;

import dal.HouseDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Houses;

/**
 * The HouseRepository class acts as an intermediary between the business logic
 * and the data access layer for managing houses.
 *
 * This class provides methods to perform various operations on houses, such as
 * retrieving houses by ID, retrieving all houses, updating house information,
 * and more.
 *
 * It implements the IHouseRepository interface to define the contract for
 * house-related operations.
 *
 */
public class HouseRepository implements IHouseRepository {

    @Override
    public Houses getHouseByHouseIdAndUserId(String houseID, String userID) {
        try {
            return new HouseDAO().getHouseByHouseIdAndUserId(houseID, userID);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Houses> getAllHousesPaging(int pageIndex) {
        try {
            return new HouseDAO().getAllHousesPaging(pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Houses> getAllHouses() {
        try {
            return new HouseDAO().getAllHouses();
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Houses> getHousesByOwnerID(String ownerID, int pageIndex) {
        try {
            return new HouseDAO().getHousesByOwnerID(ownerID, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Houses> getHousesBySearchAndFilterWithOwnerID(String ownerID, String nameSearch, int villageID, int hamletID, int status, int pageIndex) {
        try {
            return new HouseDAO().getHousesBySearchAndFilterWithOwnerID(ownerID, nameSearch, villageID, hamletID, status, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changeHouseStatus(int houseID, int status) {
        try {
            new HouseDAO().changeHouseStatus(houseID, status);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateHouse(int houseID, String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom,
            String image, String note, int status, int villageID, int hamletID, int noOfRoom) {
        try {
            new HouseDAO().updateHouseOwner(houseID, houseName, address, distanceToCampus, powerPrice, waterPrice, price, fingerPrintLock, camera, numberOfRoom, image, note, status, villageID, hamletID, noOfRoom);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addHouseOwner(String houseName, String address, float distanceToCampus, float powerPrice,
            float waterPrice, float price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image,
            String note, int status, int villageID, int hamletID , int UserID, int noOfRoom) {
        try {
            new HouseDAO().addHouseOwner(houseName, address, distanceToCampus, powerPrice, waterPrice, price, fingerPrintLock, camera, numberOfRoom, image, note, status, villageID, hamletID, UserID, noOfRoom);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Houses> getHousesBySearchAndFilter(String nameSearch, int _villageID, int _hamletID, int _status, int pageIndex) {
        try {
            return new HouseDAO().getHousesBySearchAndFilter(nameSearch, _villageID, _hamletID, _status, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Houses> getAllAvailableHouses(int pageIndex) {
        try {
            return new HouseDAO().getAllAvailableHouses(pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Houses getHouseByHouseId(String houseID) {
        try {
            return new HouseDAO().getHouseByHouseId(houseID);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changeHouseCurentRoom(int houseID, int currenRoom) {
        try {
            new HouseDAO().changeHouseCurentRoom(houseID, currenRoom);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Houses checkHouseExist(String houseName, String address, int villageID, int hamletID) {
         try {
            return new HouseDAO().checkHouseExist(houseName, address, villageID, hamletID);
        } catch (Exception ex) {
            Logger.getLogger(HouseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
