/* 
    Created on : Aug 16, 2023, 12:03:59 PM
    Author     : SMILY
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.FeedbackOptions;
import model.Feedbacks;
import repos.AccountRepository;
import repos.FeedbackRepository;
import repos.IAccountRepository;
import repos.IFeedbackRepository;

/**
 * The controller class push data to replyfeedback.jsp
 *
 */
public class ReplyFeedbackController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IFeedbackRepository feedbackRepository = new FeedbackRepository();
        int feedbackId = Integer.parseInt(request.getParameter("id")); //get param "id"
        Feedbacks feedback = feedbackRepository.getFeedbackById(feedbackId); //get current feedback

        List<FeedbackOptions> feedbackOptions = feedbackRepository.getAllFeedbackOptions(); //get all feedback options
        request.setAttribute("option", feedbackOptions);  //set data to "option"

        IAccountRepository accountRepository = new AccountRepository();
        request.setAttribute("userSend", accountRepository.getAccountsByID(feedback.getFeedbackUserId())); //set feedback sent user

        request.setAttribute("feedback", feedback); //set data to "feedback"
        request.getRequestDispatcher("replyfeedback.jsp").forward(request, response); //send data to replyfeedback.jsp
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IFeedbackRepository feedbackRepository = new FeedbackRepository();
        int feedbackId = Integer.parseInt(request.getParameter("id")); //get param "id"
        Feedbacks feedback = feedbackRepository.getFeedbackById(feedbackId);
        if (request.getParameter("change_status") != null && feedback.isFeedbackStatus()) { // if confirm close button click
            feedbackRepository.changeStatus(feedbackId); //close feedback
            request.setAttribute("closeStatus", "yes"); //set data to "closeStatus"
        } else {
            String replyContent = request.getParameter("reply_content").trim().replaceAll("\\s+", " "); //get param "reply_content"
            if (replyContent.isEmpty()) {
                request.setAttribute("alertMess", "Please fill it out completely");
            } else {
                feedbackRepository.updateReplyFeedback(feedbackId, replyContent); //call method updateReplyFeedback
                request.setAttribute("replyStatus", "yes"); //set data to "replyStatus"
            }
        }

        doGet(request, response); //call doGet to push data
    }// </editor-fold>

}
