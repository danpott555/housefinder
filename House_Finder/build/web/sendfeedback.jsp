<%-- 
    Document   : sendfeedback
    Created on : Aug 16, 2023, 8:05:51 AM
    Author     : SMILY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>FU House Finder</title>
        <link rel="shortcut icon" href="assets/img/favicon.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">
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
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Feedback</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Feedback</a></li>
                                    <li class="breadcrumb-item active">Send Feedback</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="sendfeedback" method="post">
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Send Feedback</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-10">
                                                <div class="form-group">
                                                    <label>Option</label>
                                                    <label style="color: red">*</label>
                                                    <select name="option_feedback" class="form-control" required>
                                                        <c:forEach items="${options}" var="o">
                                                            <option value="${o.optionId}">${o.optionName}</option>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                    <label style="color: red">*</label>
                                                    <textarea name="content" class="form-control" maxlength="1000" style="height: 250px" required></textarea>
                                                    <c:if test="${validateContent != null}">
                                                        <span style="color: red">Content can not contains only spaces</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary">Send</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>              
                </div>
            </div>
        </div>
            <div id="snackbar">Send Successful!</div> 

            <script src="assets/js/jquery-3.6.0.min.js"></script>
            <script src="assets/js/popper.min.js"></script>
            <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
            <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
            <script src="assets/plugins/datatables/datatables.min.js"></script>
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

</html>