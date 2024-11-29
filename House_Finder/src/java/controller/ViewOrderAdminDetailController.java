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
import model.Hamlets;
import model.Houses;
import model.Orders;
import model.Villages;
import repos.AccountRepository;
import repos.HamletRepository;
import repos.HouseRepository;
import repos.IAccountRepository;
import repos.IHamletRepository;
import repos.IHouseRepository;
import repos.IOrderRepository;
import repos.IVillageRepository;
import repos.OrderRepositoty;
import repos.VillageRepository;

/**
 *
 * @author ADMIN
 */
public class ViewOrderAdminDetailController extends HttpServlet {

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
            IOrderRepository orderRepository = new OrderRepositoty();           // Create an instance of the order repository
            int orderID = Integer.parseInt(request.getParameter("id"));         // Get the order ID from the request parameter
            Orders orders = orderRepository.getOrdeDetail(orderID);             // Retrieve the order details using the specified orderID
            if (orders != null) {                                               // Check if the retrieved orders is not null
                int pageIndex = 1;
                if (request.getParameter("pageIndex") != null) {
                    pageIndex = Integer.parseInt(request.getParameter("pageIndex")) <= 1 ? 1 : Integer.parseInt(request.getParameter("pageIndex"));
                }
                IVillageRepository villageRepository = new VillageRepository(); // Create instances of repository classes
                List<Villages> listVillages = villageRepository.getAll();       // Retrieve additional information
                IHamletRepository hamletRepository = new HamletRepository();    // Create instances of repository classes    
                List<Hamlets> listHamlets = hamletRepository.getAll();          // Retrieve additional information
                IHouseRepository houseRepository = new HouseRepository();       // Create instances of repository classes
                List<Houses> listHouseses = houseRepository.getAllHouses();     // Retrieve additional information
                IAccountRepository accountRepository = new AccountRepository(); // Create instances of repository classes
                List<Accounts> listAccounts = accountRepository.getAll();       // Retrieve additional information    

                request.setAttribute("order", orders);                          // Set the retrieved details as attributes in the request
                request.setAttribute("account", listAccounts);
                request.setAttribute("house", listHouseses);
                request.setAttribute("village", listVillages);
                request.setAttribute("hamlet", listHamlets);
                request.getRequestDispatcher("/orderadmindetail.jsp").forward(request, response);   // Forward the request to the JSP page for displaying the order details
            } else {
                request.getRequestDispatcher("/error404.jsp").forward(request, response);
            }
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
