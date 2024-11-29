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
import java.util.List;
import model.FeedbackOptions;
import model.Feedbacks;
import repos.FeedbackRepository;
import repos.IFeedbackRepository;

/**
 *
 * @author Asus
 */
public class ViewFeedbackController extends HttpServlet {

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
            IFeedbackRepository feedbackRepository = new FeedbackRepository();
            List<Feedbacks> listFeedback = feedbackRepository.getAll();//get all feedback
            List<FeedbackOptions> feedbackOptions = feedbackRepository.getAllFeedbackOptions();//get all feedback option

            request.setAttribute("feedbacks", listFeedback);//set feedback to request
            request.setAttribute("feedbackOptions", feedbackOptions);//set feedback option to request
            request.getRequestDispatcher("viewfeedback.jsp").forward(request, response);//send data to viewfeedback.jsp
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
