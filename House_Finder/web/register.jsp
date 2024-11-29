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
                                <h1>REGISTER</h1>
                                <p class="account-subtitle" style="color: red">${alertMess}</p>  
                                <p class="account-subtitle" style="color: green">${succesMess}</p> 
                                <form action="register" method="post" ;">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="text" name="email" placeholder="Email" required maxlength="50" value="<c:out value='${param.email}'/>">
                                        
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="password" name="password" placeholder="Password" minlength="6" maxlength="50" required value="<c:out value='${param.password}'/>">
                                    </div>
                                    <div class="form-group">
                                        <label>Re-Password</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="password" name="repassword" placeholder="Repassword" minlength="6" maxlength="50" required/>
                                    </div>
                                    <div class="form-group">
                                        <label>Last Name</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="text" name="lastName" placeholder="Last Name"  maxlength="50" required value="<c:out value='${param.lastName}'/>">
                                    </div>
                                    <div class="form-group">
                                         <label>First Name</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="text" name="firstName" placeholder="First Name" maxlength="50" required value="<c:out value='${param.firstName}'/>">
                                    </div>
                                    <div class="form-group">
                                         <label>Phone</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="number" name="phone" placeholder="Phone"  required value="<c:out value='${param.phone}'/>">
                                    </div>
                                    <div class="form-group">
                                         <label>Address</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="text" name="address" placeholder="Address" minlength="6" maxlength="50" required value="<c:out value='${param.address}'/>">
                                    </div>
                                    <div class="form-group">
                                         <label>Date of birth</label>
                                        <label style="color: red">*</label>
                                        <input class="form-control" type="date" name="dob" placeholder="DOB" required value="<c:out value='${param.dob}'/>">
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-block" type="Register">Register</button>
                                    </div>
                                </form>
                                <div class="text-center dont-have">Already have an account? <a
                                        href="login">Login</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="snackbar">Register Successful!</div>  

        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/js/script.js"></script>

    </body>

    <!-- Mirrored from preschool.dreamguystech.com/html-template/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:58 GMT -->
</html>