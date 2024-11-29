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
public class OrderRenterController extends HttpServlet {

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
            HttpSession session = request.getSession();                         // Get the user's ID from the session
            Accounts ac = (Accounts) session.getAttribute("account");
            int userID = ac.getUserID();
            String userIDStr = String.valueOf(userID);

            IOrderRepository orderRepository = new OrderRepositoty();           // Create instances of repositories for Orders
            List<Orders> listOrders;
            
            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }

            IHouseRepository houseRepository = new HouseRepository();           // Create instances of repositories for Houses
            List<Houses> listHouses = houseRepository.getAllHouses();

            IVillageRepository villageRepository = new VillageRepository();     // Create instances of repositories for Villages
            List<Villages> listVillages = villageRepository.getAll();

            IHamletRepository hamletRepository = new HamletRepository();        // Create instances of repositories for Hamlets
            List<Hamlets> listHamlets = hamletRepository.getAll();

            IAccountRepository accountRepository = new AccountRepository();     // Create instances of repositories for Accounts
            List<Accounts> listAccounts = accountRepository.getAll();

            request.setAttribute("houses", listHouses);                         // Set attributes for houses, villages, hamlets, and accounts lists
            request.setAttribute("villages", listVillages);
            request.setAttribute("hamlets", listHamlets);
            request.setAttribute("accounts", listAccounts);

            if (request.getParameter("search_house_name") != null || request.getParameter("village_filter") != null
                    || request.getParameter("hamlet_filter") != null || request.getParameter("status_filter") != null) {
                String nameSearch = request.getParameter("search_house_name").trim().replaceAll("\\s+", " ");
                int villageID = request.getParameter("village_filter") == null ? 0 : Integer.parseInt(request.getParameter("village_filter"));
                int hamletID = 0;
                if (villageID != 0) {
                    hamletID = Integer.parseInt(request.getParameter("hamlet_filter"));
                }
                int status = request.getParameter("status_filter") == null ? -1 : Integer.parseInt(request.getParameter("status_filter"));
                // Get orders based on search and filter criteria with the renter's ID
                listOrders = orderRepository.getOrdersBySearchAndFilterWithRenterID(userIDStr, nameSearch, villageID, hamletID, status, (pageIndex - 1)*10);
                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                if (!orderRepository.getOrdersBySearchAndFilterWithRenterID(userIDStr, nameSearch, villageID, hamletID, status, pageIndex *10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listOrders", listOrders);
                
            } else {
                listOrders = orderRepository.getOrderByRenterID(userIDStr, (pageIndex - 1)*10);   // Get all orders associated with the renter's ID
                if (!orderRepository.getOrderByRenterID(userIDStr, pageIndex *10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listOrders", listOrders);
            }

            request.getRequestDispatcher("/orderrenterlist.jsp").forward(request, response);    // Forward the request and response to the "/orderrenterlist.jsp" JSP page
        } catch (Exception e) {
            e.printStackTrace();                                                // Print the stack trace in case of an exception
            request.getRequestDispatcher("/error404.jsp").forward(request, response);   // Forward the request and response to the "/error404.jsp" JSP page in case of an exception
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
            if (request.getParameter("id") != null) {                           // Check if "id" parameter exists in the request
                int orderId = Integer.parseInt(request.getParameter("id"));     // Parse order ID and status parameters
                int status = Integer.parseInt(request.getParameter("status"));
                IOrderRepository orderRepository = new OrderRepositoty();       // Create an instance of the order repository
                if (status == 0) {                                              // Check the current status
                    orderRepository.changeOrderStatus(orderId, 3);              // If the current status is pending, mark the order as cancel
                    request.setAttribute("updateStatus", "yes");
                    doGet(request, response);                                   // Call the doGet method to refresh the page with updated information
                }
            }
        } catch (Exception e) {
            e.printStackTrace();                                                // Print the stack trace in case of an exception
            request.getRequestDispatcher("/error404.jsp").forward(request, response);   // Forward the request and response to the "/error404.jsp" JSP page in case of an exception
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
