package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
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
public class HomepageController extends HttpServlet {

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
            IHouseRepository houseRepository = new HouseRepository();           // Create an instance of IHouseRepository
            List<Houses> listHouses;

            IVillageRepository villageRepository = new VillageRepository();     // Create an instance of IVillageRepository
            List<Villages> listVillages = villageRepository.getAll();

            IHamletRepository hamletRepository = new HamletRepository();        // Create an instance of IHamletRepository
            List<Hamlets> listHamlets = hamletRepository.getAll();

            request.setAttribute("villages", listVillages);                     // Set attributes for villages and hamlets in the request
            request.setAttribute("hamlets", listHamlets);
            // Check if any search or filter parameters are provided in the request
            if (request.getParameter("search_house_name") != null || request.getParameter("village_filter") != null
                    || request.getParameter("hamlet_filter") != null || request.getParameter("status_filter") != null) {
                String nameSearch = request.getParameter("search_house_name").trim().replaceAll("\\s+", " ");           // Retrieve values from search and filter parameters
                int villageID = request.getParameter("village_filter") == null ? 0 : Integer.parseInt(request.getParameter("village_filter"));
                int hamletID = 0;
                if (villageID != 0) {                                           // Check if a village filter is selected, then retrieve hamlet filter
                    hamletID = Integer.parseInt(request.getParameter("hamlet_filter"));
                }
                listHouses = houseRepository.getHousesBySearchAndFilter(nameSearch, villageID, hamletID, 1, (pageIndex - 1)*10);    // Get houses based on search and filters using the repository
                if (pageIndex > 1) {
                    request.setAttribute("hasPreviousPage", "yes");
                }
                
                if (!houseRepository.getHousesBySearchAndFilter(nameSearch, villageID, hamletID, 1, pageIndex *10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listHouses", listHouses);                 // Set the list of houses as an attribute in the request
            } else {
                listHouses = houseRepository.getAllAvailableHouses((pageIndex - 1)*10);           // If no search or filter parameters provided, get all available houses
                if (!houseRepository.getAllAvailableHouses(pageIndex * 10).isEmpty()) {
                    request.setAttribute("hasNextPage", "yes");
                }
                request.setAttribute("listHouses", listHouses);                 // Set the list of houses as an attribute in the request
            }
            request.getRequestDispatcher("/homepage.jsp").forward(request, response);   // Forward the request and response to the "/homepage.jsp" JSP page for rendering
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
