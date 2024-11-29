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
                                <h3 class="page-title">View User Detail</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="">View User</a></li>
                                    <li class="breadcrumb-item active">View User Detail</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="" method="post">
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Account Details</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>First Name:</label>
                                                    <label>${account.firstName}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Last Name:</label>
                                                    <label>${account.lastName}</label>
                                                </div>
                                            </div>


                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Date of Birth:</label>
                                                    <label>${account.dob}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Email:</label>
                                                    <label>${account.email}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Mobile:</label>
                                                    <label>${account.phone}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6">
                                                <div class="form-group">
                                                    <label>Address:</label>
                                                    <label>${account.address}</label>
                                                </div>
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
        <div id="snackbar">Update Successful!</div>                                        


        <script src="assets/js/jquery-3.6.0.min.js"></script>

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
                }, 3000);
            }

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
    </body>

</html>
