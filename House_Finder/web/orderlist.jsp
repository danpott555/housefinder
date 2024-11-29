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

        <%@include file="navbar.jsp" %>
        <%@include file="sidebar.jsp" %>

        <div class="page-wrapper">
            <div class="content container-fluid">

                <div class="page-header">
                    <div class="row align-items-center">
                        <div class="col">
                            <h3 class="page-title">List Of Orders</h3>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item">Dashboard</a></li>
                                <li class="breadcrumb-item active">Orders</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card card-table">
                            <div class="card-body">
                                <div class="search-filter">
                                    <form action="ordercontroller">

                                        <c:if test="${param.search_house_name == null}">
                                            <div class="col-2">
                                                <label>House Name</label>
                                                <input name="search_house_name" type="text" class="form-control" placeholder="Input House Name"/>
                                            </div>
                                        </c:if>

                                        <c:if test="${param.search_house_name != null}">
                                            <div class="col-2">
                                                <label>House Name</label>
                                                <input name="search_house_name" type="text" class="form-control" placeholder="Input House Name" value="${param.search_house_name}"/>
                                            </div>
                                        </c:if>

                                        <c:if test="${param.search_renter_name == null}">
                                            <div class="col-2">
                                                <label>Renter Name</label>
                                                <input name="search_renter_name" type="text" class="form-control" placeholder="Input Renter Name"/>
                                            </div>
                                        </c:if>

                                        <c:if test="${param.search_renter_name != null}">
                                            <div class="col-2">
                                                <label>Renter Name</label>
                                                <input name="search_renter_name" type="text" class="form-control" placeholder="Input Renter Name" value="${param.search_renter_name}"/>
                                            </div>
                                        </c:if>

                                        <div class="col-2">
                                            <label>Village Name</label>
                                            <select name="village_filter" id="select-villages" class="form-control" onchange="updateHamlets()">
                                                <c:if test="${param.village_filter == null || param.village_filter == 0}">
                                                    <option value="0" selected>-- All Villages --</option>
                                                </c:if>

                                                <c:if test="${param.village_filter != null && param.village_filter != 0}">
                                                    <option value="0">-- All Villages --</option>
                                                </c:if>    

                                                <c:forEach items="${villages}" var="v">
                                                    <c:if test="${param.village_filter != v.villageID}">
                                                        <option value="${v.villageID}">${v.villageName}</option>
                                                    </c:if>

                                                    <c:if test="${param.village_filter == v.villageID}">
                                                        <option value="${v.villageID}" selected>${v.villageName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="col-2">
                                            <label>Hamlet Name</label>
                                            <select name="hamlet_filter" id="select-hamlets" class="form-control">
                                                <c:if test="${param.hamlet_filter == null || param.hamlet_filter == 0}">
                                                    <option value="0" selected>-- All Hamlets --</option>
                                                </c:if>

                                                <c:if test="${param.hamlet_filter != null && param.hamlet_filter != 0}">
                                                    <option value="0">-- All Hamlets --</option>
                                                </c:if>

                                                <c:if test="${param.village_filter != null && param.village_filter != 0}">
                                                    <c:forEach items="${hamlets}" var="hl">
                                                        <c:if test="${param.hamlet_filter == hl.hamletID && hl.villageID == param.village_filter}">
                                                            <option value="${hl.hamletID}" selected>${hl.hamletName}</option> 
                                                        </c:if>

                                                        <c:if test="${param.hamlet_filter != hl.hamletID && hl.villageID == param.village_filter}">
                                                            <option value="${hl.hamletID}">${hl.hamletName}</option> 
                                                        </c:if>
                                                    </c:forEach>   
                                                </c:if>
                                            </select>

                                        </div>

                                        <div class="col-2">
                                            <label>Status</label>     
                                            <select name="status_filter" class="form-control">
                                                <c:if test="${param.status_filter == null || param.status_filter == -1}">
                                                    <option value="-1" selected>-- All Status --</option>
                                                </c:if>

                                                <c:if test="${param.status_filter != null && param.status_filter != -1}">
                                                    <option value="-1">-- All Status --</option>
                                                </c:if>

                                                <c:if test="${param.status_filter == 0}">
                                                    <option value="0" selected>Pending</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter != 0}">
                                                    <option value="0">Pending</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter == 1}">
                                                    <option value="1" selected>Accept</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter != 1}">
                                                    <option value="1">Accept</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter == 2}">
                                                    <option value="2" selected>Reject</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter != 2}">
                                                    <option value="2">Reject</option>
                                                </c:if>  
                                                <c:if test="${param.status_filter == 4}">
                                                    <option value="4" selected>Checked in</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter != 4}">
                                                    <option value="4">Checked in</option>
                                                </c:if> 
                                                <c:if test="${param.status_filter == 5}">
                                                    <option value="5" selected>Checked out</option>
                                                </c:if>    

                                                <c:if test="${param.status_filter != 5}">
                                                    <option value="5">Checked out</option>
                                                </c:if> 
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Search</button>
                                    </form>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-hover table-center mb-0 datatable">
                                        <thead>
                                            <tr>
                                                <th>Serial</th>
                                                <th>Renter Name</th>
                                                <th>House Name</th>
                                                <th>Address</th>
                                                <th>Date Booking</th>
                                                <th>Status</th>
                                                <th class="text-right">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${listOrders.isEmpty()}">
                                                <tr>
                                                    <td colspan="6" class="text-center" style="color: red; font-size: 24px; font-weight: bold">No Data Available<td>
                                                </tr>
                                            </c:if>
                                            <c:forEach items="${listOrders}" var="o">
                                                <tr>
                                                    <td>${listOrders.indexOf(o) + 1}</td>
                                                    <c:forEach items="${accounts}" var="a">
                                                        <c:if test="${o.userID == a.userID }">
                                                            <td>${a.firstName} ${a.lastName}</td>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:forEach items="${houses}" var="h">
                                                        <c:if test="${h.houseID == o.houseID }">
                                                            <td>${h.houseName}</td>
                                                            <td>${h.address}</td>
                                                        </c:if>
                                                    </c:forEach>
                                                    <td>${o.dateBookingDate}</td>
                                                    <c:if test="${o.status == 0 }">
                                                        <td>Pending</td>
                                                    </c:if>
                                                    <c:if test="${o.status == 1 }">
                                                        <td>Accept</td>
                                                    </c:if>
                                                    <c:if test="${o.status == 2 }">
                                                        <td>Reject</td>
                                                    </c:if>
                                                    <c:if test="${o.status == 4 }">
                                                        <td>Checked in</td>
                                                    </c:if>
                                                    <c:if test="${o.status == 5 }">
                                                        <td>Checked out</td>
                                                    </c:if>
                                                    <td class="text-right">
                                                        <div class="actions" style="display: inline-flex;">
                                                            <form action="ordercontroller?id=${o.orderID}&status=${o.status}" method="post">
                                                                <c:choose>
                                                                    <c:when test="${o.status == 0}">
                                                                        <button type="submit" name="choosestatus" value="1" class="btn btn-sm btn-success">
                                                                            Accept
                                                                        </button>
                                                                        <button type="submit" name="choosestatus" value="2" class="btn btn-sm btn-danger">
                                                                            Reject
                                                                        </button>
                                                                    </c:when>
                                                                    <c:when test="${o.status == 1}">
                                                                        <button type="submit" name="choosestatus" value="2" class="btn btn-sm btn-danger">
                                                                            Reject
                                                                        </button>
                                                                    </c:when>
                                                                </c:choose>
                                                            </form>


                                                            <a href="./vieworderdetail?id=${o.orderID}" class="btn btn-sm bg-success-light mr-2" style="margin-left: 10px">
                                                                <i class="fas fa-eye"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-5"></div>
                    <div class="col-sm-12 col-md-7">
                        <div
                            class="dataTables_paginate paging_simple_numbers"
                            id="DataTables_Table_0_paginate"
                            >
                            <ul class="pagination" style="justify-content: flex-end;">
                                <li
                                    class="paginate_button page-item previous ${hasPreviousPage == null ? 'disabled' : ''}"
                                    id="DataTables_Table_0_previous"
                                    >
                                    <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=${param.pageIndex - 1}" class="page-link">Previous</a>
                                </li>
                                <c:if test="${param.pageIndex == null || param.pageIndex == 1}">
                                    <li class="paginate_button page-item active">
                                        <a href="#" class="page-link">1</a>
                                    </li>
                                    <c:if test="${hasNextPage != null}">
                                        <li class="paginate_button page-item ">
                                            <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=2" class="page-link">2</a>
                                        </li>
                                    </c:if>

                                    <li
                                        class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                        id="DataTables_Table_0_next"
                                        >
                                        <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=2" class="page-link">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${param.pageIndex != null && param.pageIndex > 1}">
                                    <li class="paginate_button page-item ">
                                        <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=${param.pageIndex - 1}" class="page-link">${param.pageIndex - 1}</a>
                                    </li>
                                    <li class="paginate_button page-item active">
                                        <a href="#" class="page-link">${param.pageIndex}</a>
                                    </li>
                                    <c:if test="${hasNextPage != null}">
                                        <li class="paginate_button page-item ">
                                            <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=${param.pageIndex + 1}" class="page-link">${param.pageIndex + 1}</a>
                                        </li>
                                    </c:if>

                                    <li
                                        class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                        id="DataTables_Table_0_next"
                                        >
                                        <a href="./ordercontroller?search_house_name=${param.search_house_name == null ? "" : param.search_house_name}&search_renter_name=${param.search_renter_name == null ? "" : param.search_renter_name}&village_filter=${param.village_filter == null ? 0 : param.village_filter}&hamlet_filter=${param.hamlet_filter == null ? 0 : param.hamlet_filter}&status_filter=${param.status_filter == null ? -1 : param.status_filter}&pageIndex=${param.pageIndex + 1}" class="page-link">Next</a>
                                    </li>
                                </c:if>    

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="snackbar">Update Successful!</div>
            <footer>
                <p>Copyright © 2020 Dreamguys.</p>
            </footer>

        </div>

    </div>


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

    </script>
</body>

</html>
