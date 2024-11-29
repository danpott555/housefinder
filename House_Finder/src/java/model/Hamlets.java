package model;

/**
 * The Hamlets class represents a hamlet within a village. It includes details
 * such as the hamlet ID, hamlet name, and village ID.
 *
 * The class provides constructors and getter/setter methods for accessing and
 * modifying the attributes. It also overrides the `toString` method for
 * generating a string representation of the object.
 *
 */
public class Hamlets {

    private int hamletID;           // Unique ID for the hamlet
    private String hamletName;      // Name of the hamlet
    private int villageID;          // ID of the village to which the hamlet belongs

    /**
     * Default constructor for the Hamlets class.
     */
    public Hamlets() {
    }

    /**
     * Parameterized constructor for the Hamlets class.
     *
     * @param hamletID Unique ID for the hamlet
     * @param hamletName Name of the hamlet
     * @param villageID ID of the village to which the hamlet belongs
     */
    public Hamlets(int hamletID, String hamletName, int villageID) {
        this.hamletID = hamletID;
        this.hamletName = hamletName;
        this.villageID = villageID;
    }

    // Getters and setters for class attributes
    public int getHamletID() {
        return hamletID;
    }

    public void setHamletID(int hamletID) {
        this.hamletID = hamletID;
    }

    public String getHamletName() {
        return hamletName;
    }

    public void setHamletName(String hamletName) {
        this.hamletName = hamletName;
    }

    public int getVillageID() {
        return villageID;
    }

    public void setVillageID(int villageID) {
        this.villageID = villageID;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Hamlets object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Hamlets{" + "hamletID=" + hamletID + ", hamletName=" + hamletName + ", villageID=" + villageID + '}';
    }

}
