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

public class ViewUserProfileController extends HttpServlet {

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
            AccountRepository accRepository = new AccountRepository();          // Create an instance of the AccountRepository
            HttpSession session = request.getSession();                         // Get the current user's account from the session
            Accounts ac = (Accounts) session.getAttribute("account");
            int u_userID = ac.getUserID();                                      // Get the user ID from the account
            Accounts acc = accRepository.getAccountsByID(u_userID);             // Retrieve the user's account details using the user ID

            request.setAttribute("acc", acc);                                   // Set the retrieved account details as an attribute in the request

            request.getRequestDispatcher("/userprofile.jsp").forward(request, response);    // Forward the request to the JSP page for displaying the user profile

        } catch (Exception e) {
            e.printStackTrace();                                                // Print the exception stack trace
            request.getRequestDispatcher("/error404.jsp").forward(request, response);   // Forward to the error page if an exception occurs
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
            String u_email = request.getParameter("email").trim().replaceAll("\\s+", " ");          // Retrieve user input data from the request
            String u_lastName = request.getParameter("lastName").trim().replaceAll("\\s+", " ");
            String u_phone = request.getParameter("phone").trim().replaceAll("\\s+", " ");
            String u_address = request.getParameter("address").trim().replaceAll("\\s+", " ");
            String dobString = request.getParameter("dob");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date u_dob = null;
            try {
                u_dob = dateFormat.parse(dobString);                            // Parse the date of birth string into a Date object
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String u_firstName = request.getParameter("firstName").trim().replaceAll("\\s+", " ");
            String userIDString = request.getParameter("userID");

            Date today = new Date();                                            // Calculate the user's age based on the date of birth
            long ageInMillis = today.getTime() - u_dob.getTime();
            int ageInYears = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

            HttpSession session = request.getSession();                         // Get the current user's account from the session
            Accounts ac = (Accounts) session.getAttribute("account");
            int u_userID = ac.getUserID();
            if (userIDString != null && !userIDString.isEmpty()) {              // If userIDString is provided, update u_userID accordingly
                u_userID = Integer.parseInt(userIDString);
            }
            AccountDAO p = new AccountDAO();                                    // Create an instance of the AccountDAO

            if (ageInYears < 17) {
                request.setAttribute("alertMess", "User not less than 17 years old");   // Set an alert message if the user's age is less than 17
                doGet(request, response);                                       // Forward the request to the doGet method for displaying user profile
            } else if (!u_phone.matches("0\\d{9}")) {
                request.setAttribute("alertMess", "Phone number must begin with 0 and have 10 digits");     // Set an alert message if the phone number format is invalid
                doGet(request, response);                                       // Forward the request to the doGet method for displaying user profile
            } else {
                p.updateAccount(u_email, u_lastName, u_phone, u_address, u_dob, u_firstName, u_userID);     // Update the user account details in the database
                request.setAttribute("updateStatus", "yes");                    // Set a success message and update status attribute
                doGet(request, response);                                       // Forward the request to the doGet method for displaying user profile
            }
        } catch (Exception e) {
            e.printStackTrace();                                                // Print the exception stack trace
            request.getRequestDispatcher("/error404.jsp").forward(request, response);   // Forward to the error page if an exception occurs
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
