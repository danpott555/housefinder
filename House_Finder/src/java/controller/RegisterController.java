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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Accounts;
import repos.AccountRepository;
import repos.IAccountRepository;

/**
 *
 * @author ADMIN
 */
public class RegisterController extends HttpServlet {

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        MessageDigest md = MessageDigest.getInstance("SHA-256");                // Create a MessageDigest instance with the "SHA-256" algorithm
        byte[] hashBytes = md.digest(password.getBytes());                      // Compute the hash value of the password's byte representation

        StringBuilder sb = new StringBuilder();                                 // Create a StringBuilder to hold the hexadecimal hash representation
        for (byte hashByte : hashBytes) {                                       // Convert each byte of the hash to its hexadecimal representation
            sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();                                                   // Return the final hashed password as a string
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email").trim().replaceAll("\\s+", " ");            // Retrieve user input parameters from the request
            String lastName = request.getParameter("lastName").trim().replaceAll("\\s+", " ");
            String phone = request.getParameter("phone").trim().replaceAll("\\s+", " ");
            String address = request.getParameter("address").trim().replaceAll("\\s+", " ");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            String dobString = request.getParameter("dob");
            String firstName = request.getParameter("firstName").trim().replaceAll("\\s+", " ");
            int status = 1;
            int role = 1;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   // Parse date of birth string into a Date object
            Date dob = null;
            try {
                dob = dateFormat.parse(dobString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date today = new Date();                                            // Calculate user's age in years
            long ageInMillis = today.getTime() - dob.getTime();
            int ageInYears = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

            String newhashedPassword = hashPassword(repassword);                // Hash the provided passwords for secure storage
            String hashedPassword = hashPassword(password);

            if (!email.endsWith("@fpt.edu.vn")) {                               // Validate user input    
                request.setAttribute("alertMess", "Email must end with @fpt.edu.vn");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (password.length() < 6 || password.length() > 50) {
                request.setAttribute("alertMess", "Password must be between 6 and 50 characters");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                request.setAttribute("alertMess", "Password must contain both letters and numbers");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (!phone.matches("0\\d{9}")) {
                request.setAttribute("alertMess", "Phone number must begin with 0 and have 10 digits");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            } else if (!password.equals(repassword)) {
                request.setAttribute("alertMess", "Passwords and Repassword do not match");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            } else if (ageInYears < 17) {
                request.setAttribute("alertMess", "User not less than 17 years old");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            } else {
                IAccountRepository accountRepository = new AccountRepository(); // Proceed with registration
                Accounts a = accountRepository.getAccountsByEmail(email);
                if (a != null) {                                                // Account already exists
                    request.setAttribute("alertMess", "Account already exists.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {                                                        // Register the new account
                    accountRepository.addAccount(email, hashedPassword, firstName, lastName, phone, address, dob, role, status);
                    request.setAttribute("succesMess", "Register success");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
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
