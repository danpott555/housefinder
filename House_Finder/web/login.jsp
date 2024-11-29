<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<html lang="en">

    <!-- Mirrored from preschool.dreamguystech.com/html-template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Preskool - Forgot Password</title>

        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>

        <div class="main-wrapper login-body">
            <div class="login-wrapper">
                <div class="container">
                    <div class="loginbox">
                        <div class="login-left">

                        </div>
                        <div class="login-right">
                            <div class="login-right-wrap">
                                <h1>LOGIN</h1>
                                <p class="account-subtitle" style="color: red">${alertMess}</p>   
                                <form action="login" method="post">
                                    <div class="form-group">
                                        <input class="form-control" type="text" name="email" placeholder="Email" value="<c:out value='${param.email}'/>">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="password" name="password" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-block" type="submit">Login</button>
                                    </div>
                                    <div class="login-or">
                                        <span class="or-line"></span>
                                        <span class="span-or">or</span>
                                    </div>
                                </form>
                                <div class="text-center dont-have"> <a
                                        href="forgotpassword">Forgot Password ?</a></div>
                                <div class="text-center dont-have">Don't have an account? <a
                                        href="register">Register</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/js/script.js"></script>
    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
</html>