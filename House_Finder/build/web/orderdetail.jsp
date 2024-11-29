<%-- 
    Document   : housedashboard
    Created on : Aug 8, 2023, 1:57:48 PM
    Author     : SMILY
--%>

<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <title>FU House Finder - Owner Houses</title>

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
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Order Detail</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Order Detail</li>
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
                                            <h5 class="form-title"><span>Order Details</span></h5>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:if test="${order.status == 0 }">
                                                    <label>Status : Pending</label>
                                                </c:if>
                                                <c:if test="${order.status == 1 }">
                                                    Status :  <label style="color: green">Accepted</label>
                                                </c:if>
                                                <c:if test="${order.status == 2 }">
                                                    Status :  <label style="color: red">Rejected</label>
                                                </c:if>
                                                <c:if test="${order.status == 4 }">
                                                     Status :  <label style="color: green">Checked in</label>
                                                </c:if>
                                                <c:if test="${order.status == 5 }">
                                                   Status :  <label style="color: red">Checked out</label>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label>Date Booking : ${order.dateBookingDate}</label>
                                            </div>
                                        </div>
                                        <c:if test="${order.status == 4 || order.status == 5}">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">         
                                                    <label>Date Check in : ${order.dateCheckIn}</label>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${order.status == 4 || order.status == 5}">
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">         
                                                    <label>Date Check out : ${order.dateCheckOut}</label>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <h5 class="form-title"><span>House Details</span></h5>
                                        </div>
                                       
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>House Name : ${h.houseName}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Address : ${h.address}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${village}" var="v">
                                                    <c:forEach items="${house}" var="h">
                                                        <c:if test="${v.villageID == h.villageID && order.houseID == h.houseID}">
                                                            <label>Village : ${v.villageName}</label>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${hamlet}" var="ha">
                                                    <c:forEach items="${house}" var="h">
                                                        <c:if test="${ha.hamletID == h.hamletID && order.houseID == h.houseID}">
                                                            <label>Hamlet : ${ha.hamletName}</label>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Distance To Campus: ${String.format("%.1f", h.distanceToCampus)} KM</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Monthly Price: ${String.format("%.0f", h.price)} VND</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Water Price: ${String.format("%.0f", h.waterPrice)} VND</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Power Price: ${String.format("%.0f", h.powerPrice)} VND</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label style="margin-right: 30px">Finger Print Lock: </label>
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <c:if test="${h.fingerPrintLock == true}">
                                                            <i class="fas fa-check" style="color: green"></i>
                                                        </c:if>

                                                        <c:if test="${h.fingerPrintLock == false}">
                                                            <i class="fas fa-times" style="color: red"></i>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <label style="margin-right: 30px">Finger Print Lock: </label>
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <c:if test="${h.camera == true}">
                                                            <i class="fas fa-check" style="color: green"></i>
                                                        </c:if>

                                                        <c:if test="${h.camera == false}">
                                                            <i class="fas fa-times" style="color: red"></i>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Number Of Room : ${h.numberOfRoom}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${house}" var="h">
                                                    <c:if test="${order.houseID == h.houseID}">
                                                        <label>Note : ${h.note}</label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <c:choose>
                                                <c:when test="${order.status == 0 || order.status == 1}">
                                                    <button type="submit" onclick="confirmDeactivate(${order.orderID},${order.status})" class="btn btn-sm btn-success">
                                                        Check in
                                                    </button>
                                                </c:when>
                                                <c:when test="${order.status == 4}">
                                                    <button type="submit" onclick="confirmActivate(${order.orderID},${order.status})" class="btn btn-sm btn-success">
                                                        Check out
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="modal fade none-border" id="deactivate_confirm" style="display: none; background-color: hsl(0deg 4.19% 42.16% / 60%)">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Check in house</h4>
                    </div>
                    <div class="modal-footer justify-content-center">
                        <form id="deactivate_form" action="#" method="post">
                            <button type="submit" class="btn btn-success">Check in</button>
                        </form>
                        <button type="button" class="btn btn-light" onclick="cancelDeactivate()">Cancel</button>
                    </div>  
                </div>
            </div>
        </div>  
        <div class="modal fade none-border" id="activate_confirm" style="display: none; background-color: hsl(0deg 4.19% 42.16% / 60%);">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Check out house</h4>
                    </div>
                    <div class="modal-footer justify-content-center">
                        <form id="activate_form" action="#" method="post">
                            <button type="submit" class="btn btn-success" onclick="updateStatus()">Check out</button>
                        </form>
                        <button type="button" class="btn btn-light" onclick="cancelActivate()">Cancel</button>
                    </div>  
                </div>
            </div>
        </div>
        <div id="snackbar">Update Successful!</div>

        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="assets/js/script.js"></script>
        <script>
                            function updateHamlets() {
                                var x = document.getElementById('select-villages').value;
                                var y = document.getElementById('select-hamlets');
                                y.innerHTML = '<option value="0" selected>-- All Hamlets --</option>';
            <c:forEach items="${hamlets}" var="h">
                                if (x === '${h.villageID}') {
                                    var option = document.createElement('option');
                                    option.value = ${h.hamletID};
                                    option.text = '${h.hamletName}';
                                    y.add(option);
                                }
            </c:forEach>
                                y.value = 0;
                            }
        </script>
        <script>
            function showSnackbar() {
                var x = document.getElementById("snackbar");
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
            }
            function updateStatus() {
                var x = document.getElementById('deactivate_confirm');
                x.classList.remove('show');
                x.style.display = 'block';
                var z = document.getElementById('update_status');
                z.classList.add('show');
                z.style.display = 'block';
            }
            function confirmDeactivate(a, b) {
                var x = document.getElementById('deactivate_confirm');
                var y = document.getElementById('deactivate_form');
                y.action = 'vieworderdetail?id=' + a + '&status=' + b;
                x.classList.add('show');
                x.style.display = 'block';

            }

            function confirmActivate(a, b) {
                var x = document.getElementById('activate_confirm');
                var y = document.getElementById('activate_form');
                y.action = 'vieworderdetail?id=' + a + '&status=' + b;
                x.classList.add('show');
                x.style.display = 'block';
            }

            function cancelDeactivate() {
                var x = document.getElementById('deactivate_confirm');
                x.classList.remove('show');
                x.style.display = 'none';
            }

            function cancelActivate() {
                var x = document.getElementById('activate_confirm');
                x.classList.remove('show');
                x.style.display = 'none';
            }
        </script>
    </body>

</html>
