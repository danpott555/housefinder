<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<!DOCTYPE html>
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
 
    <c:if test="${updateStatus != null}">
        <body onload="showSnackbar()">
        </c:if>

        <c:if test="${updateStatus == null}">
        <body>
        </c:if>
        <div class="main-wrapper login-body">
            <div class="login-wrapper">
                <div class="container">
                    <div class="loginbox">
                        <div class="login-left">
                            <img class="img-fluid" src="assets/img/logo-white.png" alt="Logo">
                        </div>
                        <div class="login-right">
                            <div class="login-right-wrap">
                                <h1>Reset Password</h1>
                                <p style="color: red">${alertMess}</p>    
                                <form action="newpassword" method="post">
                                    <div class="form-group">
                                        <input class="form-control" name="password" type="password" placeholder="New Password" minlength="6" maxlength="50" required>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" name="confPassword" type="password" placeholder="Confirm Password" minlength="6" maxlength="50"required>
                                    </div>
                                    <div class="form-group mb-0">
                                        <button class="btn btn-primary btn-block" type="submit">Reset</button>
                                    </div>
                                </form>
                                <div class="text-center dont-have">Already change your password ? <a href="./login">Login</a></div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="snackbar">Change Password Successful!</div>   

        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/js/script.js"></script>
        <script>
            function showSnackbar() {
                var x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
            }
        </script>
    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
</html>