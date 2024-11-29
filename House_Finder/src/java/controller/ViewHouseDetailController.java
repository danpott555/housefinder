package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Accounts;
import model.Hamlets;
import model.Houses;
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
 * @author SMILY
 */
public class ViewHouseDetailController extends HttpServlet {

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
            IHouseRepository houseRepository = new HouseRepository();
            String houseID = request.getParameter("id");                        // Get the house ID from the request parameter
            Houses house = houseRepository.getHouseByHouseId(houseID);          // Retrieve the house information using the house ID

            if (house != null) {                                                // Retrieve other related data (villages, hamlets, accounts) for display
                IVillageRepository villageRepository = new VillageRepository();
                List<Villages> listVillages = villageRepository.getAll();
                IHamletRepository hamletRepository = new HamletRepository();
                List<Hamlets> listHamlets = hamletRepository.getAll();
                IAccountRepository accountRepository = new AccountRepository();
                List<Accounts> listAccounts = accountRepository.getAll();
                request.setAttribute("house", house);                           // Set attributes for the request to be used in JSP
                request.setAttribute("villages", listVillages);
                request.setAttribute("hamlets", listHamlets);
                request.setAttribute("accounts", listAccounts);
                request.getRequestDispatcher("/housedetail.jsp").forward(request, response);    // Forward the request to the "housedetail.jsp" page for rendering
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
        try {
            IOrderRepository orderRepository = new OrderRepositoty();
            HttpSession session = request.getSession();                         // Retrieve the current user's account information from the session    
            Accounts ac = (Accounts) session.getAttribute("account");
            int userID = ac.getUserID();
            int houseID = Integer.parseInt(request.getParameter("id"));         // Get the house ID from the request parameter
            int status = 0;                                                     // Set the initial status for the booking
            Date today = new Date(System.currentTimeMillis());                  // Get the current date/time for the booking
            orderRepository.addBooking(houseID, userID, today, status);         // Add a booking using the retrieved data
            request.setAttribute("updateStatus", "yes");
            doGet(request, response);                                           // Redirect to the doGet method to refresh the house details    
        } catch (Exception e) {                                                 // Forward to the error page if an exception occurs
            e.printStackTrace();
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
