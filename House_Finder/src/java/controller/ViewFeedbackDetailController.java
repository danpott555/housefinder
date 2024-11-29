/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedbacks;
import repos.FeedbackRepository;
import repos.IFeedbackRepository;

/**
 *
 * @author Asus
 */
public class ViewFeedbackDetailController extends HttpServlet {

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

        try {
            String feedbackIdParam = request.getParameter("feedbackId");//get parameter from request
            IFeedbackRepository feedbackRepository = new FeedbackRepository();
            if (feedbackIdParam != null && !feedbackIdParam.trim().isEmpty()) {//check parameter isvalid
                int feedbackId = Integer.parseInt(feedbackIdParam);//convert string to INT
                Feedbacks feedback = feedbackRepository.getFeedbackByFeedbackId(feedbackId);//get feedback by id
                request.setAttribute("feedback", feedback);//set feedback to request
                request.getRequestDispatcher("viewfeedbackdetail.jsp").forward(request, response);//send data to viewfeedbackdetail.jsp
            } else {
                request.getRequestDispatcher("error404.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error404.jsp").forward(request, response);
        }
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
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId")); //get param "feedbackId"
        Feedbacks feedback = feedbackRepository.getFeedbackById(feedbackId);
        if (request.getParameter("change_status") != null && feedback.isFeedbackStatus()) { // if confirm close button click
            feedbackRepository.changeStatus(feedbackId); //close feedback
            request.setAttribute("cancelStatus", "yes"); //set data to "cancelStatus"
        }
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
