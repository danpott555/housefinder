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
public class LoginController extends HttpServlet {

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
        HttpSession session = request.getSession();                             // Get the HttpSession associated with the request
        session.invalidate();                                                   // Invalidate the session
        request.getRequestDispatcher("/login.jsp").forward(request, response);  // Forward the request and response to the "/login.jsp" JSP page for rendering
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
        MessageDigest md = MessageDigest.getInstance("SHA-256");                // Create an instance of the MessageDigest class with the SHA-256 algorithm
        byte[] hashBytes = md.digest(password.getBytes());                      // Compute the hash value of the password as an array of bytes

        StringBuilder sb = new StringBuilder();                                 // Create a StringBuilder to build the final hashed password as a string
        for (byte hashByte : hashBytes) {                                       // Iterate through each byte in the hashBytes array
            sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();                                                   // Return the final hashed password as a string
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email").trim().replaceAll("\\s+", " ");        // Retrieve the "email" parameter from the request and trim any leading/trailing spaces
            String password = request.getParameter("password");                                 // Retrieve the "password" parameter from the request
            String hashedPassword = hashPassword(password);                                     // Hash the password using the hashPassword method
            IAccountRepository accountRepository = new AccountRepository();                     // Create an instance of IAccountRepository
            Accounts a = accountRepository.getAccountsByEmailAndPass(email, hashedPassword);    // Get an account using the provided email and hashed password

            if (a == null) {
                request.setAttribute("alertMess", "Wrong Password or Email !");                 // If the retrieved account is null, display a message and forward to "login.jsp"
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();                                     // If the account is not null, create a session and store the account in it
                session.setAttribute("account", a);
                response.sendRedirect("home");                                                  // Redirect to the "home" page
            }
        } catch (Exception e) {
            e.printStackTrace();                                                                // Print the stack trace in case of an exception
            request.getRequestDispatcher("/error404.jsp").forward(request, response);           // Forward the request and response to the "/error404.jsp" JSP page in case of an exception
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
