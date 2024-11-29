/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Accounts;
import repos.AccountRepository;

/**
 *
 * @author Admin
 */
public class EditUserProfileController extends HttpServlet {

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
            AccountRepository accRepository = new AccountRepository();          // Create an instance of AccountRepository
            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request
            Accounts ac = (Accounts) session.getAttribute("account");           // Retrieve the "account" attribute from the session, which is expected to be an instance of "Accounts" class
            int u_userID = ac.getUserID();                                      // Retrieve the UserID from the "ac" object
            Accounts acc = accRepository.getAccountsByID(u_userID);             // Retrieve account details using the AccountRepository based on the UserID

            request.setAttribute("acc", acc);                                   // Set the "acc" attribute in the request, making it available for the JSP page

            request.getRequestDispatcher("/edituserprofile.jsp").forward(request, response);    // Forward the request and response to the "/edituserprofile.jsp" JSP page for rendering

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
            String u_email = request.getParameter("email").trim().replaceAll("\\s+", " ");          // Retrieve values from the request parameters
            String u_lastName = request.getParameter("lastName").trim().replaceAll("\\s+", " ");
            String u_phone = request.getParameter("phone").trim().replaceAll("\\s+", " ");
            String u_address = request.getParameter("address").trim().replaceAll("\\s+", " ");
            String dobString = request.getParameter("dob");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   // Create a SimpleDateFormat to parse the date string
            Date u_dob = null;
            try {
                u_dob = dateFormat.parse(dobString);                            // Parse the date string into a Date object    
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String u_firstName = request.getParameter("firstName").trim().replaceAll("\\s+", " ");
            String userIDString = request.getParameter("userID");

            Date today = new Date();                                            // Calculate the user's age based on their date of birth
            long ageInMillis = today.getTime() - u_dob.getTime();
            int ageInYears = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request
            Accounts ac = (Accounts) session.getAttribute("account");           // Retrieve the "account" attribute from the session, which is expected to be an instance of "Accounts" class
            int u_userID = ac.getUserID();                                      // Retrieve the UserID from the "ac" object
            if (userIDString != null && !userIDString.isEmpty()) {              // If userIDString is not null and not empty, update u_userID
                u_userID = Integer.parseInt(userIDString);
            }
            AccountDAO p = new AccountDAO();                                    // Create an instance of AccountDAO

            if (ageInYears < 17) {                                              // Check if the user is at least 17 years old
                request.setAttribute("alertMess", "User not less than 17 years old");
                doGet(request, response);
            } else if (!u_phone.matches("0\\d{9}")) {                               // Check if the phone number format is valid
                request.setAttribute("alertMess", "Phone number must begin with 0 and have 10 digits");
                doGet(request, response);
            } else {
                p.updateAccount(u_email, u_lastName, u_phone, u_address, u_dob, u_firstName, u_userID); // Update the user's account details using AccountDAO
                request.setAttribute("updateStatus", "yes");                    // Set attribute to indicate successful update
                doGet(request, response);                                       // Call the doGet method to display the updated profile details
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
