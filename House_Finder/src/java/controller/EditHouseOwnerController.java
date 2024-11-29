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
 * @author SMILY
 */
public class EditHouseOwnerController extends HttpServlet {

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
            IHouseRepository houseRepository = new HouseRepository();           // Create an instance of IHouseRepository 
            String houseID = request.getParameter("id");                        // Retrieve the "id" parameter from the request
            Houses house = houseRepository.getHouseByHouseIdAndUserId(houseID, String.valueOf(acc.getUserID()));    // Retrieve a Houses object using the houseID and UserID from the account

            if (house != null) {                                                // If the retrieved house object is not null
                IVillageRepository villageRepository = new VillageRepository(); // Create instances of IVillageRepository and IHamletRepository
                List<Villages> listVillages = villageRepository.getAll();       // Retrieve a list of all villages and hamlets
                IHamletRepository hamletRepository = new HamletRepository();
                List<Hamlets> listHamlets = hamletRepository.getAll();
                request.setAttribute("house", house);                           // Set attributes in the request for use in the JSP page
                request.setAttribute("villages", listVillages);
                request.setAttribute("hamlets", listHamlets);
                request.getRequestDispatcher("/houseownerdetail.jsp").forward(request, response);   // Forward the request and response to the "/houseownerdetail.jsp" JSP page for rendering
            }
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
            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request
            Accounts acc = null;                                                // Initialize an Accounts object as null
            if (session.getAttribute("account") != null) {                      // Check if the "account" attribute exists in the session
                acc = (Accounts) session.getAttribute("account");               // If it exists, retrieve the "account" attribute and cast it to an Accounts object
            }

            if (acc != null && acc.getRole() == 2) {                            // Check if the user is logged in and has the role of 2
                IHouseRepository houseRepository = new HouseRepository();       // Create an instance of IHouseRepository
                String houseID = request.getParameter("id");                    // Retrieve the "id" parameter from the request
                Houses house = houseRepository.getHouseByHouseIdAndUserId(houseID, String.valueOf(acc.getUserID()));    // Retrieve a Houses object using the houseID and UserID from the account

                if (house != null) {
                    String houseName = request.getParameter("house_name").trim().replaceAll("\\s+", " ");   // Retrieve values from the request parameters
                    String address = request.getParameter("address").trim().replaceAll("\\s+", " ");
                    int villageId = Integer.parseInt(request.getParameter("village"));
                    int hamletId = Integer.parseInt(request.getParameter("hamlet"));
                    float distance = Float.parseFloat(request.getParameter("distance_to_campus"));
                    float powerPrice = Float.parseFloat(request.getParameter("power_price"));
                    float waterPrice = Float.parseFloat(request.getParameter("water_price"));
                    float monthlyPrice = Float.parseFloat(request.getParameter("monthly_price"));
                    boolean fingerPrintLock = request.getParameter("finger").equals("Yes") ? true : false;
                    boolean camera = request.getParameter("camera").equals("Yes") ? true : false;
                    int numberOfRooms = Integer.parseInt(request.getParameter("number_of_rooms"));
                    int currentRooms = Integer.parseInt(request.getParameter("current_rooms"));
                    String note = request.getParameter("note").trim().replaceAll("\\s+", " ");

                    houseRepository.updateHouse(Integer.parseInt(houseID), houseName, address, distance, // Update the house details in the repository
                            powerPrice, waterPrice, monthlyPrice, fingerPrintLock, camera, numberOfRooms,
                            house.getImage(), note, house.getStatus(), villageId, hamletId, currentRooms);
                }
                request.setAttribute("updateStatus", "yes");                    // Set attribute to indicate successful update
                doGet(request, response);                                       // Call the doGet method to display the updated house details
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
