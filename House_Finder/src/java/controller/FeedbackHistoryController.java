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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Accounts;
import model.Feedbacks;
import repos.FeedbackRepository;
import repos.IFeedbackRepository;

/**
 *
 * @author Asus
 */
public class FeedbackHistoryController extends HttpServlet {

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
            HttpSession session = request.getSession();//get session
            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }

            if (session != null) {
                Accounts acc = (Accounts) session.getAttribute("account");//get account from session

                if (acc != null) {//check account exist
                    IFeedbackRepository feedbackRepository = new FeedbackRepository();

                    List<Feedbacks> feedbacks = feedbackRepository.getFeedbacksByUserId(acc.getUserID(), (pageIndex - 1) * 10);//get feedback by account
                    if (pageIndex > 1) {
                        request.setAttribute("hasPreviousPage", "yes");
                    }
                    if (!feedbackRepository.getFeedbacksByUserId(acc.getUserID(), pageIndex * 10).isEmpty()) {
                        request.setAttribute("hasNextPage", "yes");
                    }
                    request.setAttribute("feedbacks", feedbacks);//set feedback to request
                    request.getRequestDispatcher("viewfeedbackhistory.jsp").forward(request, response);//send data to viewfeedbackhistory.jsp

                }
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
