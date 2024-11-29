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
                                <h3 class="page-title">Add House</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="./housesowner">My House</a></li>
                                    <li class="breadcrumb-item active">Add House</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="addhouseowner" method="post">
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Add House</span></h5>
                                                <h5 class="form-title"><span style="color: red">${alertMess}</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>House Name</label>
                                                    <label style="color: red">*</label>
                                                    <input name="house_name" type="text" class="form-control" maxlength="50"  value="<c:out value='${param.house_name}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <label style="color: red">*</label>
                                                    <input name="address" type="text" class="form-control" maxlength="50"  value="<c:out value='${param.address}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Village</label>
                                                    <label style="color: red">*</label>
                                                    <select name="village" id="select-villages" class="form-control" onchange="updateHamlets()" required>
                                                        <c:forEach items="${villages}" var="v">
                                                            <option value="${v.villageID}">${v.villageName}</option> 
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Hamlet</label>
                                                    <label style="color: red">*</label>
                                                    <select name="hamlet" id="select-hamlets" class="form-control" required>
                                                        <c:forEach items="${hamlets}" var="hl">
                                                            <option value="${hl.hamletID}" >${hl.hamletName}</option> 
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Distance To Campus</label>
                                                    <label style="color: red">*</label>
                                                    <input name="distance_to_campus" type="number" step="0.1" min="0" class="form-control" value="<c:out value='${param.distance_to_campus}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Power Price</label>
                                                    <label style="color: red">*</label>
                                                    <input name="power_price" type="number" step="100" min="1000" class="form-control" value="<c:out value='${param.power_price}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Water Price</label>
                                                    <label style="color: red">*</label>
                                                    <input name="water_price" type="number" step="100" min="100" class="form-control" value="<c:out value='${param.water_price}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Monthly Price</label>
                                                    <label style="color: red">*</label>
                                                    <input name="monthly_price" type="number" step="100000" min="500000" class="form-control" value="<c:out value='${param.monthly_price}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label style="margin-right: 30px">Finger Print Lock</label>
                                                    <label style="color: red">*</label>
                                                    <input id="finger_yes" type="radio" name="finger" value="Yes" checked/>
                                                    <label for="finger_yes">Yes</label>
                                                    <input id="finger_no" type="radio" name="finger" value="No"/>
                                                    <label for="finger_no">No</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label style="margin-right: 30px">Camera</label>
                                                    <label style="color: red">*</label>
                                                    <input id="camera_yes" type="radio" name="camera" value="Yes" checked/>
                                                    <label for="camera_yes">Yes</label>
                                                    <input id="camera_no" type="radio" name="camera" value="No"/>
                                                    <label for="camera_no">No</label>                                                
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Number Of Rooms</label>
                                                    <label style="color: red">*</label>
                                                    <input name="number_of_rooms" type="number" step="1" class="form-control" value="<c:out value='${param.number_of_rooms}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Current Rooms</label>
                                                    <label style="color: red">*</label>
                                                    <input name="current_rooms" type="number" step="1" class="form-control" value="<c:out value='${param.current_rooms}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Image</label>
                                                    <label style="color: red">*</label>
                                                    <input name="image" type="url" class="form-control" value="<c:out value='${param.image}'/>" required>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Note</label>
                                                    <textarea name="note" class="form-control"  maxlength="1000" value="<c:out value='${param.note}'/>"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-warning">Save</button>
                                                <button type="reset" class="btn btn-warning" >Reset</button>
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
        <div id="snackbar">Add Successful!</div>   

        <script src="assets/js/jquery-3.6.0.min.js"></script>

        <script src="assets/js/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="assets/js/script.js"></script>

        <script>
                                                        function updateHamlets() {
                                                            var x = document.getElementById('select-villages').value;
                                                            var y = document.getElementById('select-hamlets');
                                                            y.innerHTML = '';
            <c:forEach items="${hamlets}" var="h">
                                                            if (x === '${h.villageID}') {
                                                                var option = document.createElement('option');
                                                                option.value = ${h.hamletID};
                                                                option.text = '${h.hamletName}';
                                                                y.add(option);
                                                            }
            </c:forEach>
                                                            y.value = '';
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

        </script>
    </body>

</html>
