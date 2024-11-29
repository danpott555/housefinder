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
import model.Accounts;
import repos.AccountRepository;
import repos.IAccountRepository;

/**
 *
 * @author ADMIN
 */
public class ChangePasswordController extends HttpServlet {

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
            int UserID = ac.getUserID();                                        // Retrieve the UserID from the "ac" object                 
            Accounts acc = accRepository.getAccountsByID(UserID);               // Retrieve account details using the AccountRepository based on the UserID

            request.setAttribute("acc", acc);                                   // Set the "acc" attribute in the request, making it available for the JSP page

            request.getRequestDispatcher("/changepassword.jsp").forward(request, response); // Forward the request and response to the "/changepassword.jsp" JSP page for rendering
        } catch (Exception e) {
            e.printStackTrace();                                                 // Print the stack trace in case of an exception
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
    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");                            // Create an instance of MessageDigest using SHA-256 algorithm
        byte[] hashBytes = md.digest(password.getBytes());                                  // Compute the hash of the password bytes

        StringBuilder sb = new StringBuilder();                                             // Create a StringBuilder to store the hexadecimal hash representation
        for (byte hashByte : hashBytes) {                                                   // Iterate through each byte in the hashBytes array
            sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));        // Convert the byte to an integer and perform bitwise operations to get a positive value
        }                                                                                   // Then convert it to a hexadecimal representation and append it to the StringBuilder

        return sb.toString();                                                               // Convert the StringBuilder to a string and return the resulting hash
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();                         // Get the HttpSession associated with the request

            String email = request.getParameter("email");                       // Retrieve values from request parameters
            String password = request.getParameter("password");
            String newPass = request.getParameter("newPass");
            String confrimPass = request.getParameter("confrimPass");
            String newhashedPassword = hashPassword(newPass);                   // Hash the new password for storage and comparison
            String hashedPassword = hashPassword(password);                     // Hash the provided password for comparison with stored hashed password
            IAccountRepository accountRepository = new AccountRepository();     // Create an instance of IAccountRepository      
            Accounts a = accountRepository.getAccountsByEmailAndPass(email, hashedPassword);    // Retrieve an account using the provided email and hashed password
            if (a == null) {                                                    // Password provided does not match stored password
                request.setAttribute("alertMess", "Wrong password!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else if (newPass.equals(confrimPass) == false) {                  // New password and confirm password do not match
                request.setAttribute("alertMess", "New password and confrim password must be the same!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else if (!newPass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,50}$")) {    // New password does not meet complexity requirements
                request.setAttribute("alertMess", "New password must be 6-50 characters and contain both letters and numbers.");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else {
                accountRepository.updateAccountPassword(email, newhashedPassword);  // Update the account's password with the new hashed password
                request.setAttribute("updateStatus", "yes");                        // Set attribute to indicate successful update
                session.invalidate();                                               // Invalidate the current session
                request.setAttribute("passwordChanged", true);
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);      // Redirect to the changepassword.jsp page with the updateStatus parameter
            }

        } catch (Exception e) {
            e.printStackTrace();                                                    // Print the stack trace in case of an exception
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
