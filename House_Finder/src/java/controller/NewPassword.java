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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import repos.AccountRepository;
import repos.IAccountRepository;

/**
 *
 * @author ADMIN
 */
public class NewPassword extends HttpServlet {

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
    }
    
    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");                            // Create an instance of MessageDigest using SHA-256 algorithm
        byte[] hashBytes = md.digest(password.getBytes());                                  // Compute the hash of the password bytes

        StringBuilder sb = new StringBuilder();                                             // Create a StringBuilder to store the hexadecimal hash representation
        for (byte hashByte : hashBytes) {                                                   // Iterate through each byte in the hashBytes array
            sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));        // Convert the byte to an integer and perform bitwise operations to get a positive value
        }                                                                                   // Then convert it to a hexadecimal representation and append it to the StringBuilder

        return sb.toString();                                                               // Convert the StringBuilder to a string and return the resulting hash
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
            String newPassword = request.getParameter("password");              // Retrieve the new password and confirm password from request parameters
            String confPassword = request.getParameter("confPassword");
            IAccountRepository accountRepository = new AccountRepository();     // Create an instance of IAccountRepository
            if (newPassword.equals(confPassword) == false) {                    // Check if the new password and confirm password are the same
                request.setAttribute("alertMess", "New password and confrim password must be the same!");   
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);   // If not the same, set an attribute with an alert message and forward to "resetpassword.jsp"
            } 
            else if (!newPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,50}$")) {      // Check if the new password meets the specified criteria
                request.setAttribute("alertMess", "New password must be 6-50 characters and contain both letters and numbers.");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);   // If criteria not met, set an attribute with an alert message and forward to "resetpassword.jsp"
            }
            else {
                String newhashedPassword = hashPassword(newPassword);    
                // If criteria met, update the account password using the stored email in the session
                accountRepository.updateAccountPassword((String) session.getAttribute("email"), newhashedPassword);   
                request.setAttribute("updateStatus", "yes");                    // Set an attribute to indicate successful update and forward to "resetpassword.jsp"
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
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
