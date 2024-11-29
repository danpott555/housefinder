
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

            <div class="header">

                <%@include file="navbar.jsp" %>
                <%@include file="sidebar.jsp" %>
                <div class="page-wrapper">
                    <div class="content container-fluid">
                        <div class="page-header">
                            <div class="row">
                                <div class="col">
                                    <h3 class="page-title">Edit Profile</h3>
                                    <ul class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Edit Profile</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="profile-header">
                                    <div class="row align-items-center">
                                        <div class="col-auto profile-image">
                                        </div>
                                        <div class="col ml-md-n2 profile-user-info">
                                            <h4 class="user-name mb-0">${acc.lastName} ${acc.firstName}</h4>
                                            <div class="user-Email"><i class="fas fa-envelope"></i> ${acc.email}</div>
                                            <div class="user-Phone"><i class="fas fa-phone"></i> ${acc.phone}</div>
                                            <div class="user-Location"><i class="fas fa-map-marker-alt"></i> ${acc.address}</div>  
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-content profile-tab-cont">
                                    <div class="tab-pane fade show active" id="per_details_tab">
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5 class="card-title d-flex justify-content-between">
                                                            <span>Personal Details</span>
                                                            <a class="edit-link" data-toggle="modal" href="#edit_personal_details"><i class="far fa-edit mr-1"></i>Edit</a>
                                                        </h5>
                                                        <p class="account-subtitle" style="color: red">${alertMess}</p> 
                                                        <form action="edituserprofile" method="post">
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Email:</p>
                                                                <p class="col-sm-9"><input type="text" class="form-control"  name="email" value="${acc.email}" readonly="true"></p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">First Name:</p>
                                                                <p class="col-sm-9"> <input type="text" class="form-control"  name="firstName" value="${acc.firstName} " maxlength="50" required></p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Last Name:</p>
                                                                <p class="col-sm-9"> <input type="text" class="form-control"  name="lastName" value="${acc.lastName} " maxlength="50" required></p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Date of Birth:</p>
                                                                <p class="col-sm-9"> <input type="date" class="form-control"  name="dob" value="${acc.dob}" required></p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Mobile:</p>
                                                                <p class="col-sm-9"><input type="number" class="form-control"  name="phone" value="${acc.phone}" max="9999999999" required></p>
                                                            </div>
                                                            <div class="row">
                                                                <p class="col-sm-3 text-muted text-sm-right mb-0 mb-sm-3">Address:</p>
                                                                <p class="col-sm-9"><input type="text" class="form-control"  name="address" value="${acc.address}" maxlength="50" required></p>
                                                            </div>
                                                            <div class="col-12">
                                                                <button type="submit" class="btn btn-warning">Submit</button>
                                                                <button type="reset" class="btn btn-danger" >Reset</button>
                                                            </div>

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
            <div id="snackbar">Update Successful!</div> 

            <script src="assets/js/jquery-3.6.0.min.js"></script>
            <script src="assets/js/popper.min.js"></script>
            <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
            <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
            <script src="assets/plugins/datatables/datatables.min.js"></script>
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
    <!-- Mirrored from preschool.dreamguystech.com/html-template/profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 28 Oct 2021 11:11:39 GMT -->
</html>
