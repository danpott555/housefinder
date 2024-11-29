package model;

/**
 * The Houses class represents a rental house listing. It includes details such
 * as the house ID, name, address, distance to campus, prices for power and
 * water, rental price, fingerprint lock availability, camera availability,
 * number of rooms, image, note, status, village ID, hamlet ID, user ID, and the
 * number of available rooms.
 *
 * The class provides constructors and getter/setter methods for accessing and
 * modifying the attributes. It also overrides the `toString` method for
 * generating a string representation of the object.
 *
 * @author SMILY
 */
public class Houses {

    private int houseID;             // Unique ID for the house
    private String houseName;        // Name of the house
    private String address;          // Address of the house
    private double distanceToCampus; // Distance to the campus
    private double powerPrice;       // Price for power
    private double waterPrice;       // Price for water
    private double price;            // Rental price
    private boolean fingerPrintLock; // Availability of fingerprint lock
    private boolean camera;          // Availability of camera
    private int numberOfRoom;        // Number of rooms in the house
    private String image;            // Image of the house
    private String note;             // Additional note or description
    private int status;              // Status of the house
    private int villageID;           // ID of the village where the house is located
    private int hamletID;            // ID of the hamlet where the house is located
    private int userID;              // ID of the user who owns the house
    private int noOfRoom;            // Number of available rooms

    /**
     * Default constructor for the Houses class.
     */
    public Houses() {
    }

    /**
     * Parameterized constructor for the Houses class.
     *
     * @param houseID Unique ID for the house
     * @param houseName Name of the house
     * @param address Address of the house
     * @param distanceToCampus Distance to the campus
     * @param powerPrice Price for power
     * @param waterPrice Price for water
     * @param price Rental price
     * @param fingerPrintLock Availability of fingerprint lock
     * @param camera Availability of camera
     * @param numberOfRoom Number of rooms in the house
     * @param image Image of the house
     * @param note Additional note or description
     * @param status Status of the house
     * @param villageID ID of the village
     * @param hamletID ID of the hamlet
     * @param userID ID of the user
     * @param noOfRoom Number of available rooms
     */
    public Houses(int houseID, String houseName, String address, double distanceToCampus, double powerPrice, double waterPrice, double price, boolean fingerPrintLock, boolean camera, int numberOfRoom, String image, String note, int status, int villageID, int hamletID, int userID, int noOfRoom) {
        this.houseID = houseID;
        this.houseName = houseName;
        this.address = address;
        this.distanceToCampus = distanceToCampus;
        this.powerPrice = powerPrice;
        this.waterPrice = waterPrice;
        this.price = price;
        this.fingerPrintLock = fingerPrintLock;
        this.camera = camera;
        this.numberOfRoom = numberOfRoom;
        this.image = image;
        this.note = note;
        this.status = status;
        this.villageID = villageID;
        this.hamletID = hamletID;
        this.userID = userID;
        this.noOfRoom = noOfRoom;
    }

    // Getters and setters for class attributes
    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistanceToCampus() {
        return distanceToCampus;
    }

    public void setDistanceToCampus(double distanceToCampus) {
        this.distanceToCampus = distanceToCampus;
    }

    public double getPowerPrice() {
        return powerPrice;
    }

    public void setPowerPrice(double powerPrice) {
        this.powerPrice = powerPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFingerPrintLock() {
        return fingerPrintLock;
    }

    public void setFingerPrintLock(boolean fingerPrintLock) {
        this.fingerPrintLock = fingerPrintLock;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHamletID() {
        return hamletID;
    }

    public void setHamletID(int hamletID) {
        this.hamletID = hamletID;
    }

    public int getVillageID() {
        return villageID;
    }

    public void setVillageID(int villageID) {
        this.villageID = villageID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNoOfRoom() {
        return noOfRoom;
    }

    public void setNoOfRoom(int noOfRoom) {
        this.noOfRoom = noOfRoom;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Houses object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Houses{" + "houseID=" + houseID + ", houseName=" + houseName + ", address=" + address + ", distanceToCampus=" + distanceToCampus + ", powerPrice=" + powerPrice + ", waterPrice=" + waterPrice + ", price=" + price + ", fingerPrintLock=" + fingerPrintLock + ", camera=" + camera + ", numberOfRoom=" + numberOfRoom + ", image=" + image + ", note=" + note + ", status=" + status + ", hamletID=" + hamletID + ", villageID=" + villageID + ", userID=" + userID + ", noOfRoom=" + noOfRoom + '}';
    }

}
