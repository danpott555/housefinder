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
import model.Accounts;
import repos.AccountRepository;
import repos.IAccountRepository;

/**
 *
 * @author Admin
 */
public class ViewUsersController extends HttpServlet {

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
            // Create an instance of the account repository
            IAccountRepository accRepository = new AccountRepository();

            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }
            List<Accounts> acc;

            // Check if a search name is provided
            if (request.getParameter("search_name") != null) {
                String name = request.getParameter("search_name").trim().replaceAll("\\s+", " ");
                int _role = Integer.parseInt(request.getParameter("search_role"));
                // Get accounts based on search name and role
                acc = accRepository.getAccountBySearchAndFilter(name, _role, (pageIndex - 1) * 10);

                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                if (!accRepository.getAccountBySearchAndFilter(name, _role, pageIndex * 10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }

                request.setAttribute("acc", acc);
            } else {
                // Get all accounts
                acc = accRepository.getAllPaging((pageIndex - 1) * 10);
                if (!accRepository.getAllPaging(pageIndex * 10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("acc", acc);
            }

            // Forward the request to viewusers.jsp for rendering
            request.getRequestDispatcher("/viewusers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Forward to error404.jsp in case of an exception
            request.getRequestDispatcher("/error404.jsp").forward(request, response);
        }
    }

    // This method handles the HTTP POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getParameter("id") != null) {
                int houseId = Integer.parseInt(request.getParameter("id"));
                int status = Integer.parseInt(request.getParameter("status"));
                IAccountRepository houseRepository = new AccountRepository();
                // Change the status of the account based on the given parameters
                if (status == 0) {
                    houseRepository.changeAccountStatus(houseId, 1);
                } else {
                    houseRepository.changeAccountStatus(houseId, 0);
                }
                request.setAttribute("updateStatus", "yes");
                // Refresh the page after updating the status
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Forward to error404.jsp in case of an exception
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
