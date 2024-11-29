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
public class OrderController extends HttpServlet {

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
            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }
            IOrderRepository orderRepository = new OrderRepositoty();           // Create an instance of IOrderRepository
            List<Orders> listOrders;                                            // Create lists to store orders

            IHouseRepository houseRepository = new HouseRepository();           // Create an instance of IHouseRepository
            List<Houses> listHouses = houseRepository.getAllHouses();           // Retrieve data from repositories

            IVillageRepository villageRepository = new VillageRepository();     // Create an instance of IVillageRepository
            List<Villages> listVillages = villageRepository.getAll();           // Retrieve data from repositories

            IHamletRepository hamletRepository = new HamletRepository();        // Create an instance of IHamletRepository
            List<Hamlets> listHamlets = hamletRepository.getAll();              // Retrieve data from repositories

            IAccountRepository accountRepository = new AccountRepository();     // Create an instance of IAccountRepository
            List<Accounts> listAccounts = accountRepository.getAll();           // Retrieve data from repositories

            request.setAttribute("houses", listHouses);                         // Set attributes for the retrieved data
            request.setAttribute("villages", listVillages);
            request.setAttribute("hamlets", listHamlets);
            request.setAttribute("accounts", listAccounts);

            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request
            Accounts ac = (Accounts) session.getAttribute("account");           // Retrieve the account from the session
            int userID = ac.getUserID();
            String userIDStr = String.valueOf(userID);

            if (request.getParameter("search_house_name") != null || request.getParameter("search_renter_name") != null 
                    || request.getParameter("village_filter") != null
                    || request.getParameter("hamlet_filter") != null || request.getParameter("status_filter") != null) {
                String nameSearch = request.getParameter("search_house_name").trim().replaceAll("\\s+", " ");
                String renterSearch = request.getParameter("search_renter_name").trim().replaceAll("\\s+", " ");
                int villageID = request.getParameter("village_filter") == null ? 0 : Integer.parseInt(request.getParameter("village_filter"));
                int hamletID = 0;
                if (villageID != 0) {
                    hamletID = Integer.parseInt(request.getParameter("hamlet_filter"));
                }
                int status = request.getParameter("status_filter") == null ? -1 : Integer.parseInt(request.getParameter("status_filter"));
                // Retrieve orders based on search and filter parameters
                listOrders = orderRepository.getOrdersBySearchAndFilterWithOwnerID(userIDStr, nameSearch, renterSearch, villageID, hamletID, status, (pageIndex - 1)*10);
                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                if (!orderRepository.getOrdersBySearchAndFilterWithOwnerID(userIDStr, nameSearch, renterSearch, villageID, hamletID, status, pageIndex *10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }      
                request.setAttribute("listOrders", listOrders);                 // Set orders as an attribute
            } else {
               listOrders = orderRepository.getOrderByOwnerID(userIDStr, (pageIndex - 1)*10);    // If no search or filter parameters, retrieve all orders for the owner's user ID
               if (!orderRepository.getOrderByOwnerID(userIDStr, pageIndex *10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listOrders", listOrders);                 // Set orders as an attribute
            }

            request.getRequestDispatcher("/orderlist.jsp").forward(request, response);  // Forward the request and response to the "/orderlist.jsp" JSP page
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
                int orderId = Integer.parseInt(request.getParameter("id"));     // Parse order ID, status, and choosestatus parameters
                int status = Integer.parseInt(request.getParameter("status"));
                int choosestatus = Integer.parseInt(request.getParameter("choosestatus"));
                IHouseRepository houseRepository = new HouseRepository();       // Create instances of repositories for houses and orders
                IOrderRepository orderRepository = new OrderRepositoty();
                int houseID = orderRepository.getHouseIDbyOrderID(orderId);     // Get house ID associated with the order
                int currentRoom = orderRepository.getCurrentRoom(houseID);      // Get the current room count of the house

                if (status == 0) {                                              // Check the current status and the chosen status
                    if (choosestatus == 1) {                                    // If changing to accepted status, update order status and decrement current room
                        orderRepository.changeOrderStatus(orderId, 1);
                        houseRepository.changeHouseCurentRoom(houseID, currentRoom - 1);
                        request.setAttribute("updateStatus", "yes");
                    } else if (choosestatus == 2) {                             // If changing to rejected status, update order status
                        orderRepository.changeOrderStatus(orderId, 2);
                        request.setAttribute("updateStatus", "yes");
                    }
                } else if (status == 1) {
                    if (choosestatus == 2) {                                    // If changing accepted status to rejected, update order status and increment current room
                        orderRepository.changeOrderStatus(orderId, 2);
                        houseRepository.changeHouseCurentRoom(houseID, currentRoom + 1);
                        request.setAttribute("updateStatus", "yes");
                    }
                }
                doGet(request, response);                                       // Call the doGet method to refresh the page with updated information
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
