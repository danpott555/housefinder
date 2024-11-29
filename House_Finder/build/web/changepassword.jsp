
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <!-- Mirrored from preschool.dreamguystech.com/html-template/profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:38 GMT -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>Profile</title>
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
        <div class="main-wrapper">

            <%@include file="navbar.jsp" %>
            <%@include file="sidebar.jsp" %>
            <div class="page-wrapper">
                <div class="content container-fluid">
                    <div class="page-header">
                        <div class="row">
                            <div class="col">
                                <h3 class="page-title">Change Password</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Authentication</a></li>
                                    <li class="breadcrumb-item active">Change Password</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div >
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Change Password</h5>
                                <h5 class="card-title" style="color: red">${alertMess}</h5>
                                <div class="row">
                                    <div class="col-md-10 col-lg-6">
                                        <form action="changepassword" method="post">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <label style="color: red">*</label>
                                                <input name="email" type="text" class="form-control" value="${sessionScope.account.email}" readonly="true">
                                            </div>
                                            <div class="form-group">
                                                <label>Old Password</label>
                                                <label style="color: red">*</label>
                                                <input name="password" type="password" class="form-control" required minlength="6" maxlength="50">
                                            </div>
                                            <div class="form-group">
                                                <label>New Password</label>
                                                <label style="color: red">*</label>
                                                <input name="newPass" type="password" class="form-control" required minlength="6" maxlength="50">
                                            </div>
                                            <div class="form-group">
                                                <label>Confirm Password</label>
                                                <label style="color: red">*</label>
                                                <input name="confrimPass"type="password" class="form-control" required  minlength="6" maxlength="50">
                                            </div>
                                            <button class="btn btn-primary" type="submit">Save Changes</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div id="snackbar">Change Successful!</div>
<script data-cfasync="false" src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="assets/js/jquery-3.6.0.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/js/script.js"></script>
<script>
            function showSnackbar() {
                var x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 5000);
            }
</script>
<c:if test="${passwordChanged}">
    <script>
        // Display an alert
        alert("Password changed successfully! You will be redirected to the login page.");
        // Redirect to the login page after showing the alert
        window.location.href = "./login"; // Replace with the actual URL of your login page
    </script>
</c:if>
</body>
<!-- Mirrored from preschool.dreamguystech.com/html-template/profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:39 GMT -->
</html>
