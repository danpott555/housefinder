package repos;

import java.util.List;
import model.FeedbackOptions;
import model.Feedbacks;

/**
 *
 * @author SMILY
 */
public interface IFeedbackRepository {

    //Retrieve a list of all feedbacks.
    List<Feedbacks> getAllPaging();

    //Retrieve a list of all feedbacks.
    List<Feedbacks> getAll();

    //Reply to a specific feedback by providing content and the feedback ID.
    List<Feedbacks> replyFeedback(String content, int feedbackId);

    //Retrieve a list of feedbacks associated with a specific user ID.
    List<Feedbacks> getFeedbacksByUserId(int userId, int pageIndex);

    //Retrieve a specific feedback based on its feedback ID.
    Feedbacks getFeedbackByFeedbackId(int feedbackId);

    //Search for feedbacks based on a content search parameter.
    List<Feedbacks> searchFeedbackByContent(String searchParam);

    //Retrieve a feedback based on its ID.
    Feedbacks getFeedbackById(int id);

    //Insert a new feedback by providing the option ID, user ID, and content.
    void insertFeedback(int optionId, int userId, String content);

    //Update the reply content of a feedback.
    void updateReplyFeedback(int feedbackId, String replyContent);

    //Retrieve a list of all available feedback options.
    List<FeedbackOptions> getAllFeedbackOptions();

    //Change the status of a feedback.
    void changeStatus(int feedbackId);

    List<Feedbacks> searchFeedbackByContent(String search_param, String search_by, String option_value);
}
