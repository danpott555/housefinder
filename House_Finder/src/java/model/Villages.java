package model;

/**
 * The Villages class represents a village in a geographic area. It includes
 * details such as the village ID and name.
 *
 * The class provides constructors and getter/setter methods for accessing and
 * modifying the attributes. It also overrides the `toString` method for
 * generating a string representation of the object.
 *
 */
public class Villages {

    private int villageID;     // Unique ID for the village
    private String villageName; // Name of the village

    /**
     * Default constructor for the Villages class.
     */
    public Villages() {
    }

    // Getters and setters for class attributes
    public Villages(int villageID) {
        this.villageID = villageID;
    }

    public Villages(int villageID, String villageName) {
        this.villageID = villageID;
        this.villageName = villageName;
    }

    public int getVillageID() {
        return villageID;
    }

    public void setVillageID(int villageID) {
        this.villageID = villageID;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    /**
     * Overrides the toString method to generate a string representation of the
     * Villages object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Villages{" + "villageID=" + villageID + ", villageName=" + villageName + '}';
    }

}
