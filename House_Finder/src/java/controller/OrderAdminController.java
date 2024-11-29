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
public class OrderAdminController extends HttpServlet {

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

            // Check for search and filter parameters in the request
            if (request.getParameter("search_house_name") != null || request.getParameter("search_renter_name") != null || request.getParameter("village_filter") != null
                    || request.getParameter("hamlet_filter") != null || request.getParameter("status_filter") != null) {
                String nameSearch = request.getParameter("search_house_name").trim().replaceAll("\\s+", " ");       // Retrieve and trim search parameters
                String renterSearch = request.getParameter("search_renter_name").trim().replaceAll("\\s+", " ");    // Retrieve and trim search parameters    
                int villageID = request.getParameter("village_filter") == null ? 0 : Integer.parseInt(request.getParameter("village_filter"));
                int hamletID = 0;
                if (villageID != 0) {                                           // If village filter is not 0, parse hamlet filter parameter
                    hamletID = Integer.parseInt(request.getParameter("hamlet_filter"));
                }
                int status = request.getParameter("status_filter") == null ? -1 : Integer.parseInt(request.getParameter("status_filter"));
                // Retrieve orders based on search and filter parameters
                listOrders = orderRepository.getOrdersBySearchAndFilterWithAdmin(renterSearch, nameSearch, villageID, hamletID, status, (pageIndex - 1) * 10);
                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                if (!orderRepository.getOrdersBySearchAndFilterWithAdmin(renterSearch, nameSearch, villageID, hamletID, status, pageIndex * 10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listOrders", listOrders);                 // Set orders as an attribute
            } else {
                listOrders = orderRepository.getallPaging((pageIndex - 1) * 10);                          // If no search or filter parameters, retrieve all orders
                if (!orderRepository.getallPaging(pageIndex * 10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listOrders", listOrders);                 // Set orders as an attribute
            }
            request.getRequestDispatcher("/orderadminlist.jsp").forward(request, response); // Forward the request and response to the "/orderadminlist.jsp" JSP page
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
