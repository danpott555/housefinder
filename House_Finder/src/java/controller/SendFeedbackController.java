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
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import repos.FeedbackRepository;
import repos.IFeedbackRepository;

/**
 * The controller class push data to sendfeedback.jsp
 *
 */
public class SendFeedbackController extends HttpServlet {

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
        request.setAttribute("options", feedbackRepository.getAllFeedbackOptions()); //set data to "options"
        request.getRequestDispatcher("sendfeedback.jsp").forward(request, response); //send data to sendfeedback.jsp
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
        HttpSession session = request.getSession(); //get session
        if (session.getAttribute("account") != null) {
            Accounts acc = (Accounts) session.getAttribute("account");
            int optionId = Integer.parseInt(request.getParameter("option_feedback")); //get param "option_feedback"
            String content = request.getParameter("content").trim().replaceAll("\\s+", " "); //get param "content"
            if (content.length() != 0) {
                IFeedbackRepository feedbackRepository = new FeedbackRepository();
                feedbackRepository.insertFeedback(optionId, acc.getUserID(), content); //call method insertFeedback() to send feedback
                request.setAttribute("updateStatus", "yes"); //set data to "updateStatus"
            } else {
                request.setAttribute("validateContent", "no"); //set data to "validateContent"
            }
        }
        doGet(request, response); //call doGet to push data
    }// </editor-fold>

}
