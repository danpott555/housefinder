/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Accounts;
import model.Comments;
import model.Houses;
import repos.AccountRepository;
import repos.CommentRepository;
import repos.HouseRepository;
import repos.IAccountRepository;
import repos.ICommentRepository;
import repos.IHouseRepository;

/**
 *
 * @author ADMIN
 */
public class ViewCommentController extends HttpServlet {

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
            ICommentRepository commentRepository = new CommentRepository();
            int houseID = Integer.parseInt(request.getParameter("id"));         // Get the house ID from the request parameter
            HttpSession session = request.getSession();                         // Set the house ID in the session for future reference
            session.setAttribute("houseID", houseID);

            List<Comments> comments = commentRepository.getCommentByHouseID(houseID);   // Retrieve the comments for the specified house ID
            List<Accounts> accounts = new ArrayList<>();                        // Create lists to store associated accounts 
            IAccountRepository accountRepository = new AccountRepository();
            List<Houses> houses = new ArrayList<>();                            // Create lists to store associated houses
            IHouseRepository houseRepository = new HouseRepository();
            for (Comments comment : comments) {                                 // Retrieve account information for each comment's user
                int userID = comment.getUserID();
                Accounts account = accountRepository.getAccountsByID(userID);
                accounts.add(account);
            }
            request.setAttribute("comments", comments);                         // Set attributes for comments, accounts, and houses
            request.setAttribute("accounts", accounts);
            request.setAttribute("houses", houses);
            request.getRequestDispatcher("/listcomments.jsp").forward(request, response);   // Forward the request to the "listcomments.jsp" page

        } catch (Exception e) {
            e.printStackTrace();                                                // Forward to the error page if an exception occurs
            request.getRequestDispatcher("/error404.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession();                         // Get the user's account information from the session
            Accounts ac = (Accounts) session.getAttribute("account");
            int userID = ac.getUserID();
            ICommentRepository commentRepository = new CommentRepository();     // Retrieve comment data from the request parameters
            String comment = request.getParameter("comment").trim().replaceAll("\\s+", " ");
            double rate = Double.parseDouble(request.getParameter("rate"));
            Date today = new Date(System.currentTimeMillis());
            int houseID = Integer.parseInt(request.getParameter("houseID"));
            commentRepository.addCommenr(comment, rate, today, userID, houseID);// Add the comment to the repository    

            response.sendRedirect(request.getContextPath() + "/viewcomment?id=" + houseID); // Redirect to the "viewcomment" page for the specific house
        } catch (Exception e) {                                                 // Forward to the error page if an exception occurs
            e.printStackTrace();
            request.getRequestDispatcher("/error404.jsp").forward(request, response);
        }
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
