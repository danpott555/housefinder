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
import java.util.List;
import model.Accounts;
import repos.AccountRepository;
import repos.IAccountRepository;

/**
 *
 * @author ADMIN
 */
public class ViewHouseOwnerController extends HttpServlet {

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
            IAccountRepository accRepository = new AccountRepository();
            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }
            List<Accounts> acc = accRepository.getAccountsByHouseowner((pageIndex - 1) * 10);       // Retrieve a list of accounts associated with house owners
            if (pageIndex > 1) {
                request.setAttribute("hasPreviousPage", "yes");
            }
            if (!accRepository.getAccountsByHouseowner(pageIndex * 10).isEmpty()) {
                request.setAttribute("hasNextPage", "yes");
            }
            request.setAttribute("acc", acc);                                   // Set the retrieved list as an attribute in the request
            request.getRequestDispatcher("/viewhouseowner.jsp").forward(request, response); // Forward the request to the JSP page for displaying the list
        } catch (Exception e) {                                                 // Forward to the error page if an exception occurs
            e.printStackTrace();
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
