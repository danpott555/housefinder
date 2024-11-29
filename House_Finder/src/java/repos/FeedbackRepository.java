package repos;

import dal.FeedbackDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FeedbackOptions;
import model.Feedbacks;

/**
 * The FeedbackRepository class acts as an intermediary between the business
 * logic and the data access layer for managing feedback and comments.
 *
 * This class provides methods to perform various operations on feedback and
 * comments, such as retrieving feedback, replying to feedback, searching for
 * feedback by content, inserting new feedback, updating reply content, changing
 * feedback status, and more.
 *
 * It implements the IFeedbackRepository interface to define the contract for
 * feedback-related operations.
 *
 */
public class FeedbackRepository implements IFeedbackRepository{

    @Override
    public List<Feedbacks> getAllPaging() {
        try {
            return new FeedbackDAO().getAllPaging();
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Feedbacks> getAll() {
        try {
            return new FeedbackDAO().getAll();
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Feedbacks> replyFeedback(String content, int feedbackId) {
        try {
            FeedbackDAO dao = new FeedbackDAO();
            boolean isSuccess = dao.replyFeedback(content, feedbackId);
            if (isSuccess) {
                return dao.getAll();
            }
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Feedbacks> getFeedbacksByUserId(int userId,int pageIndex) {
        try {
            return new FeedbackDAO().getFeedbacksByUserId(userId, pageIndex);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Feedbacks> searchFeedbackByContent(String searchParam) {
        try {
            return new FeedbackDAO().searchFeedbackByContent(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Feedbacks getFeedbackByFeedbackId(int feedbackId) {
        try {
            return new FeedbackDAO().getFeedbacksByFeedbackId(feedbackId);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insertFeedback(int optionId, int userId, String content) {
        try {
            new FeedbackDAO().addFeedback(optionId, userId, content);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateReplyFeedback(int feedbackId, String replyContent) {
        try {
            new FeedbackDAO().updateReplyFeedback(feedbackId, replyContent);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<FeedbackOptions> getAllFeedbackOptions() {
        try {
            return new FeedbackDAO().getAllFeedbackOptions();
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Feedbacks getFeedbackById(int id) {
        try {
            return new FeedbackDAO().getFeedbackById(id);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void changeStatus(int feedbackId) {
        try {
            new FeedbackDAO().changeStatus(feedbackId);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Feedbacks> searchFeedbackByContent(String search_param, String search_by, String option_value) {
        try {
            return new FeedbackDAO().searchFeedbackByContent(search_param, search_by, option_value);
        } catch (Exception ex) {
            Logger.getLogger(FeedbackRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
