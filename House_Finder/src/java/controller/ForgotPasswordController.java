/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Accounts;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordController extends HttpServlet {

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
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
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
            String email = request.getParameter("email");                       // Retrieve the "email" parameter from the request

            AccountDAO dao = new AccountDAO();                                  // Create an instance of AccountDAO
            Accounts a = dao.getAccountsByEmail(email);                         // Retrieve an account using the provided email
            if (a == null) {
                request.setAttribute("alertMess", "Email has not been created account");    // If the retrieved account is null, display a message and forward to "forgotpassword.jsp"
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            } else {
                RequestDispatcher dispatcher = null;
                int otpvalue = 0;
                HttpSession mySession = request.getSession();                   // Get the HttpSession associated with the request
                if (email != null || !email.equals("")) {
                    Random rand = new Random();                                 // Generate a random OTP value
                    otpvalue = rand.nextInt(1255650);
                    String to = email;
                    Properties props = new Properties();                        // Email configuration properties
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {        // Create a session for sending the email
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("quangnguyen2105@gmail.com", "lusnkghyshisfjtf");
                        }
                    });
                    try {
                        // Create the email message
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(email));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        message.setSubject("Chane Password");
                        message.setText("Your OTP to reset password is: " + otpvalue);
                        // Send the email
                        Transport.send(message);
                        System.out.println("message sent successfully");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                    dispatcher = request.getRequestDispatcher("enterotp.jsp");  // Forward to "enterotp.jsp" after sending the email
                    mySession.setAttribute("otp", otpvalue);                    // Set attributes in the session for OTP and email
                    mySession.setAttribute("email", email);
                    dispatcher.forward(request, response);
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
