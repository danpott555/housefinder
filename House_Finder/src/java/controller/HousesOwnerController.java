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
import model.Villages;
import repos.HamletRepository;
import repos.HouseRepository;
import repos.IHamletRepository;
import repos.IHouseRepository;
import repos.IVillageRepository;
import repos.VillageRepository;

/**
 *
 * @author SMILY
 */
public class HousesOwnerController extends HttpServlet {

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
            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request    
            Accounts acc = null;                                                // Initialize an Accounts object as null
            if (session.getAttribute("account") != null) {                      // Check if the "account" attribute exists in the session     
                acc = (Accounts) session.getAttribute("account");               // If it exists, retrieve the "account" attribute and cast it to an Accounts object
            }
            int pageIndex = 1;
            if (request.getParameter("pageIndex") != null) {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
                if (pageIndex <= 0) {
                    throw new Exception();
                }
            }
            IHouseRepository houseRepository = new HouseRepository();           // Create an instance of IHouseRepository
            List<Houses> listHouses;

            IVillageRepository villageRepository = new VillageRepository();     // Create instances of IVillageRepository
            List<Villages> listVillages = villageRepository.getAll();

            IHamletRepository hamletRepository = new HamletRepository();        // Create instances of IHamletRepository 
            List<Hamlets> listHamlets = hamletRepository.getAll();

            request.setAttribute("villages", listVillages);                     // Set attributes for villages and hamlets in the request
            request.setAttribute("hamlets", listHamlets);
            // Check if any search or filter parameters are provided in the request
            if (request.getParameter("search_house_name") != null || request.getParameter("village_filter") != null
                    || request.getParameter("hamlet_filter") != null || request.getParameter("status_filter") != null) {
                String nameSearch = request.getParameter("search_house_name").trim().replaceAll("\\s+", " ");   // Retrieve values from search and filter parameters
                int villageID = request.getParameter("village_filter") == null ? 0 : Integer.parseInt(request.getParameter("village_filter"));
                int hamletID = 0;
                if (villageID != 0) {                                           // Check if a village filter is selected, then retrieve hamlet filter
                    hamletID = Integer.parseInt(request.getParameter("hamlet_filter"));
                }
                int status = request.getParameter("status_filter") == null ? -1 : Integer.parseInt(request.getParameter("status_filter"));
                // Get houses based on search, filters, and owner ID using the repository
                listHouses = houseRepository.getHousesBySearchAndFilterWithOwnerID(String.valueOf(acc.getUserID()), nameSearch, villageID, hamletID, status, (pageIndex - 1)*10);
                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                if (!houseRepository.getHousesBySearchAndFilterWithOwnerID(String.valueOf(acc.getUserID()), nameSearch, villageID, hamletID, status, pageIndex*10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listHouses", listHouses);                 // Set the list of houses as an attribute in the request
            } else {
                listHouses = houseRepository.getHousesByOwnerID(String.valueOf(acc.getUserID()), (pageIndex - 1)*10);   // If no search or filter parameters provided, get houses owned by the user
                if (!houseRepository.getHousesByOwnerID(String.valueOf(acc.getUserID()), pageIndex*10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listHouses", listHouses);                 // Set the list of houses as an attribute in the request
            }
            request.getRequestDispatcher("/housedashboard.jsp").forward(request, response); // Forward the request and response to the "/housedashboard.jsp" JSP page for rendering
        } catch (Exception e) {
            e.printStackTrace();                                                // Print the stack trace in case of an exception
            request.getRequestDispatcher("/error404.jsp").forward(request, response);       // Forward the request and response to the "/error404.jsp" JSP page in case of an exception
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
            if (request.getParameter("id") != null) {                           // Check if "id" parameter is provided in the request
                int houseId = Integer.parseInt(request.getParameter("id"));     // Parse the "id" parameter to get the house ID
                int status = Integer.parseInt(request.getParameter("status"));  // Parse the "status" parameter to get the house status
                IHouseRepository houseRepository = new HouseRepository();       // Create an instance of IHouseRepository
                if (status == 0) {                                              // Change the house status based on the current status value
                    houseRepository.changeHouseStatus(houseId, 1);              // If status is 0, change it to 1
                } else {
                    houseRepository.changeHouseStatus(houseId, 0);              // If status is 1, change it to 0
                }
                request.setAttribute("updateStatus", "yes");                    // Set attribute to indicate successful update
                doGet(request, response);                                       // Call the doGet method to refresh the page with updated data
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
