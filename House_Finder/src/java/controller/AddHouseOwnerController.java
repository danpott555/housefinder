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
 * @author ADMIN
 */
public class AddHouseOwnerController extends HttpServlet {

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
        IVillageRepository villageRepository = new VillageRepository();         // Create an instance of the village repository
        List<Villages> listVillages = villageRepository.getAll();               // Get all villages
        IHamletRepository hamletRepository = new HamletRepository();            // Create an instance of the hamlet repository
        List<Hamlets> listHamlets = hamletRepository.getAll();                  // Get all hamlets
        request.setAttribute("villages", listVillages);                         // Set list villages to request   
        request.setAttribute("hamlets", listHamlets);                           // Set list hamlets to request  
        request.getRequestDispatcher("/addhouseowner.jsp").forward(request, response);  // Send data to addhouseowner.jsp
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
            IHouseRepository houseRepository = new HouseRepository();                                   // Create an instance of the hamlet repository
            HttpSession session = request.getSession();                                                 // Create session
            Accounts ac = (Accounts) session.getAttribute("account");                                   // Get sesion
            int userID = ac.getUserID();
            String houseName = request.getParameter("house_name").trim().replaceAll("\\s+", " ");       // Get param "houseName"
            String address = request.getParameter("address").trim().replaceAll("\\s+", " ");            // Get param "address"
            int villageId = Integer.parseInt(request.getParameter("village"));                          // Get param "villageId"
            int hamletId = Integer.parseInt(request.getParameter("hamlet"));                            // Get param "hamletId"
            float distance = Float.parseFloat(request.getParameter("distance_to_campus"));              // Get param "distance"
            float powerPrice = Float.parseFloat(request.getParameter("power_price"));                   // Get param "powerPrice"               
            float waterPrice = Float.parseFloat(request.getParameter("water_price"));                   // Get param "waterPrice"
            float monthlyPrice = Float.parseFloat(request.getParameter("monthly_price"));               // Get param "monthlyPrice"
            boolean fingerPrintLock = request.getParameter("finger").equals("Yes") ? true : false;      // Get param "fingerPrintLock"
            boolean camera = request.getParameter("camera").equals("Yes") ? true : false;               // Get param "camera"
            int numberOfRooms = Integer.parseInt(request.getParameter("number_of_rooms"));              // Get param "numberOfRooms"
            int currentRooms = Integer.parseInt(request.getParameter("current_rooms"));                 // Get param "currentRooms"
            String note = request.getParameter("note").trim().replaceAll("\\s+", " ");                  // Get param "note"
            String image = request.getParameter("image");                                               // Get param "image"
            int status = 1;

            if (currentRooms > numberOfRooms) {                                                         // Check if curren room > number room
                request.setAttribute("alertMess", "Current Rooms can not large than Number Of Rooms");  // Set data to "alertMess"
                doGet(request, response);                                                               // Refresh the page            
            } else if (houseName.isEmpty() || address.isEmpty()) {                                      // Check if houseName empty or address empty
                request.setAttribute("alertMess", "Please fill it out completely");                     // Set data to "alertMess"
                doGet(request, response);                                                               // Refresh the page   
            } else {
                Houses h = houseRepository.checkHouseExist(houseName, address, villageId, hamletId);    // Check if house already exist or not
                if (h != null) {                                                                        // If house exist
                    request.setAttribute("alertMess", "House information already exists");              // Set data to "alertMess"
                    doGet(request, response);                                                           // Refresh the page   
                } else {
                    houseRepository.addHouseOwner(houseName, address, distance, powerPrice, waterPrice, monthlyPrice, fingerPrintLock, camera, numberOfRooms, image, note, status, villageId, hamletId, userID, currentRooms);                                                                        // Add new house to database
                    request.setAttribute("updateStatus", "yes");                                        // Set data to "updateStatus"
                    doGet(request, response);                                                           // Refresh the page after add success 
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error404.jsp").forward(request, response);                   // Forward to error404.jsp in case of an exception
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
