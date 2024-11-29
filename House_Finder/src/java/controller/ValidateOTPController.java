/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class ValidateOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));              // Get the OTP value entered by the user
        HttpSession session = request.getSession();                             // Get the stored OTP value from the session
        int otp = (int) session.getAttribute("otp");
        RequestDispatcher dispatcher = null;                                    // Initialize a dispatcher for forwarding the request
        if (value == otp) {                                                     // If the entered OTP matches the stored OTP, proceed to reset password
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("resetpassword.jsp");
            dispatcher.forward(request, response);

        } else {                                                                // If the entered OTP does not match, display an error message
            request.setAttribute("message", "Wrong OTP");
            dispatcher = request.getRequestDispatcher("enterotp.jsp");
            dispatcher.forward(request, response);
        }
    }
}
