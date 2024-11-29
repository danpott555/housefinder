<%-- 
    Document   : housedashboard
    Created on : Aug 8, 2023, 1:57:48 PM
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
                                <h3 class="page-title">House Detail</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">List Of Available Houses</a></li>
                                    <li class="breadcrumb-item active">House Detail</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <h5 class="form-title"><span>House Details</span></h5>
                                            <h5 class="form-title"><span style="color: red">${alearMess}</span></h5>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-group">
                                                <img src="${house.image}" alt="alt" style="width: 100%; height: 500px"/>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>House Name: ${house.houseName}</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Address: ${house.address}</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${villages}" var="v">
                                                    <c:if test="${house.villageID == v.villageID}">
                                                        <label>Village: ${v.villageName}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${hamlets}" var="hl">
                                                    <c:if test="${house.hamletID == hl.hamletID && hl.villageID == house.villageID}">
                                                        <label>Hamlet: ${hl.hamletName}</label> 
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Distance To Campus: ${String.format("%.1f", house.distanceToCampus)}km</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Power Price: ${String.format("%.0f", house.powerPrice)}VND</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Water Price: ${String.format("%.0f", house.waterPrice)}VND</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Monthly Price: ${String.format("%.0f", house.price)}VND</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label style="margin-right: 30px">Finger Print Lock: </label>
                                                <c:if test="${house.fingerPrintLock == true}">
                                                    <i class="fas fa-check" style="color: green"></i>
                                                </c:if>

                                                <c:if test="${house.fingerPrintLock == false}">
                                                    <i class="fas fa-times" style="color: red"></i>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label style="margin-right: 30px">Camera: </label>
                                                <c:if test="${house.camera == true}">
                                                    <i class="fas fa-check" style="color: green"></i>
                                                </c:if>

                                                <c:if test="${house.camera == false}">
                                                    <i class="fas fa-times" style="color: red"></i>
                                                </c:if>

                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Number Of Rooms: ${house.numberOfRoom}</label>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Current Rooms: ${house.noOfRoom}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${accounts}" var="a">
                        <c:if test="${house.userID == a.userID}">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-12">
                                                    <h5 class="form-title"><span>House Owner Details</span></h5>
                                                    <h5 class="form-title"><span style="color: red">${alearMess}</span></h5>
                                                </div>
                                                <div class="col-12">
                                                    <div class="form-group">
                                                        <label>Owner Name: ${a.firstName} ${a.lastName}</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>DOB: ${a.dob}</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Email: ${a.email}</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Phone: ${a.phone}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <c:if test="${sessionScope.account.role == 1}">
                                    <div class="col-12">
                                        <form action="housedetail" method="post" style="display: inline;">
                                            <c:if test="${house.noOfRoom != 0}">
                                                <input type="hidden" name="id" value="${house.houseID}" />
                                                <button style="width: 100px" type="submit" class="btn btn-primary">Book</button>                              
                                            </c:if>
                                        </form>
                                        <a href="./viewcomment?id=${house.houseID}" style="color: white; display: inline;">
                                            <button class="btn btn-primary">View comment(s)</button>
                                        </a>
                                    </div>
                                </c:if>

                                <c:if test="${sessionScope.account == null}">
                                    <div class="col-12">
                                        <c:if test="${house.noOfRoom != 0}">
                                            <a href="./login" style="color: white"><button style="width: 100px" class="btn btn-primary">Book</button></a>           
                                        </c:if>
                                        <a href="./viewcomment?id=${house.houseID}" style="color: white"><button class="btn btn-primary">View comment(s)</button></a>
                                    </div>
                                </c:if>

                                <c:if test="${sessionScope.account != null && sessionScope.account.role != 1}">
                                    <div class="col-12">
                                        <a href="./viewcomment?id=${house.houseID}" style="color: white"><button class="btn btn-primary">View comment(s)</button></a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            <div id="snackbar">Booking Successful!</div> 

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
