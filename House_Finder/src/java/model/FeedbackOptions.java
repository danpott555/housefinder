package model;

/**
 * The FeedbackOptions class represents options for user feedback. It includes
 * details such as the option ID and the name of the option.
 *
 */
public class FeedbackOptions {

    private int optionId;           // Unique ID for the feedback option
    private String optionName;      // Name of the feedback option

    /**
     * Default constructor for the FeedbackOptions class.
     */
    public FeedbackOptions() {
    }

    /**
     * Parameterized constructor for the FeedbackOptions class.
     *
     * @param optionId The unique ID for the feedback option
     * @param optionName The name of the feedback option
     */
    public FeedbackOptions(int optionId, String optionName) {
        this.optionId = optionId;
        this.optionName = optionName;
    }

    // Getters and setters for class attributes
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

}
