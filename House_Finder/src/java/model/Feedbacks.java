package model;

/**
 * The Feedbacks class represents user feedback. It includes details such as the
 * feedback ID, feedback option ID, user ID, feedback content, feedback reply,
 * feedback status, feedback option name, and user name.
 *
 * The class provides constructors and getter/setter methods for accessing and
 * modifying the attributes.
 *
 */
public class Feedbacks {

    private int feedbackId;                 // Unique ID for the feedback
    private int feedbackOptionId;           // ID of the selected feedback option 
    private int feedbackUserId;             // ID of the user who provided the feedback
    private String feedbackContent;         // Content of the feedback
    private String feedbackReply;           // Reply to the feedback
    private boolean feedbackStatus;         // Status of the feedback
    private String feedbackOptionName;      // Name of the feedback option
    private String userName;                // Name of the user who provided the feedback

    /**
     * Default constructor for the Feedbacks class.
     */
    public Feedbacks() {
    }

    /**
     * Parameterized constructor for the Feedbacks class.
     *
     * @param feedbackId Unique ID for the feedback
     * @param feedbackOptionId ID of the selected feedback option
     * @param feedbackUserId ID of the user who provided the feedback
     * @param feedbackContent Content of the feedback
     * @param feedbackReply Reply to the feedback
     * @param feedbackStatus Status of the feedback
     */
    public Feedbacks(int feedbackId, int feedbackOptionId, int feedbackUserId, String feedbackContent, String feedbackReply, boolean feedbackStatus) {
        this.feedbackId = feedbackId;
        this.feedbackOptionId = feedbackOptionId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackContent = feedbackContent;
        this.feedbackReply = feedbackReply;
        this.feedbackStatus = feedbackStatus;
    }

    public Feedbacks(int feedbackId, int feedbackOptionId, int feedbackUserId, String feedbackContent, String feedbackReply, String feedbackOptionName) {
        this.feedbackId = feedbackId;
        this.feedbackOptionId = feedbackOptionId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackContent = feedbackContent;
        this.feedbackReply = feedbackReply;
        this.feedbackOptionName = feedbackOptionName;
    }

    public Feedbacks(int feedbackId, int feedbackOptionId, int feedbackUserId, String feedbackContent, String feedbackReply) {
        this.feedbackId = feedbackId;
        this.feedbackOptionId = feedbackOptionId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackContent = feedbackContent;
        this.feedbackReply = feedbackReply;
    }

    public Feedbacks(int feedbackId, int feedbackOptionId, int feedbackUserId, String feedbackContent, String feedbackReply, String feedbackOptionName, String userName) {
        this.feedbackId = feedbackId;
        this.feedbackOptionId = feedbackOptionId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackContent = feedbackContent;
        this.feedbackReply = feedbackReply;
        this.feedbackOptionName = feedbackOptionName;
        this.userName = userName;
    }

    public Feedbacks(int feedbackId, int feedbackOptionId, int feedbackUserId, String feedbackContent, String feedbackReply, String feedbackOptionName, String userName, boolean status) {
        this.feedbackId = feedbackId;
        this.feedbackOptionId = feedbackOptionId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackContent = feedbackContent;
        this.feedbackReply = feedbackReply;
        this.feedbackOptionName = feedbackOptionName;
        this.userName = userName;
        this.feedbackStatus = status;
    }

    // Getters and setters for class attributes
    public String getFeedbackOptionName() {
        return feedbackOptionName;
    }

    public void setFeedbackOptionName(String feedbackOptionName) {
        this.feedbackOptionName = feedbackOptionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFeedbackOptionId() {
        return feedbackOptionId;
    }

    public void setFeedbackOptionId(int feedbackOptionId) {
        this.feedbackOptionId = feedbackOptionId;
    }

    public int getFeedbackUserId() {
        return feedbackUserId;
    }

    public void setFeedbackUserId(int feedbackUserId) {
        this.feedbackUserId = feedbackUserId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackReply() {
        return feedbackReply;
    }

    public void setFeedbackReply(String feedbackReply) {
        this.feedbackReply = feedbackReply;
    }

    public boolean isFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

}
