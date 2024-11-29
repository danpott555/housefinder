<%-- 
    Document   : housedashboard
    Created on : Aug 8, 2023, 1:57:48 PM
    Author     : SMILY
--%>

<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <h3 class="page-title">View User</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="">View User</a></li>
                                    <li class="breadcrumb-item active">View User </li>
                                </ul>
                                </div>
                          <div class="col-auto text-right float-right ml-auto">
                            
                           <button class="btn btn-outline-primary mr-2" onclick="exportToExcel()"><i class="fas fa-download"></i>Export to Excel</button>
                        </div>  
                            </div>

                        
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card card-table">
                                <div class="card-body">
                                    <div class="search-filter">
                                        <form action="viewusers">
                                            <c:if test="${param.search_name == null}">
                                                <input name="search_name" type="text" class="col-4 form-control" placeholder="Input Name"/>
                                            </c:if>

                                            <c:if test="${param.search_name != null}">
                                                <input name="search_name" type="text" class="col-4 form-control" placeholder="Input Name" value="${param.search_name}"/>
                                            </c:if>


                                            <select name="search_role" class="col-2 form-control">
                                                <c:if test="${param.search_role == 0 }">
                                                    <option value="0" selected>-- All Role --</option>
                                                </c:if>

                                                <c:if test="${param.search_role != 0}">
                                                    <option value="0">-- All Role --</option>
                                                </c:if>

                                                <c:if test="${param.search_role == 1}">
                                                    <option value="1" selected>Users</option>
                                                </c:if>    

                                                <c:if test="${param.search_role != 1}">
                                                    <option value="1">Users</option>
                                                </c:if>    

                                                <c:if test="${param.search_role == 2}">
                                                    <option value="2" selected>House Onwer</option>
                                                </c:if>    

                                                <c:if test="${param.search_role != 2}">
                                                    <option value="2">House Onwer</option>
                                                </c:if>    
                                                <c:if test="${param.search_role == 3}">
                                                    <option value="3" selected>Admin</option>
                                                </c:if>    

                                                <c:if test="${param.search_role != 3}">
                                                    <option value="3">Admin</option>
                                                </c:if>    
                                            </select>
                                                
                                            <button type="submit" class="btn btn-primary">Search</button>
                                             
                                        </form>
                                    </div>
                                    <div class="table-responsive">
                                        <table id="yourTableId" class="table table-hover table-center mb-0 datatable">
                                            <thead>
                                                <tr>
                                                    <th>Serial</th>
                                                    <th>First Name</th>
                                                    <th>Last Name</th>
                                                    <th>Phone</th>
                                                    <th>Address</th>
                                                    <th>Email</th>

                                                    <th class="text-right">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${acc.isEmpty()}">
                                                    <tr>
                                                        <td colspan="6" class="text-center" style="color: red; font-size: 24px; font-weight: bold">No Data Available<td>
                                                    </tr>
                                                </c:if>
                                                <c:forEach items="${acc}" var="a">
                                                    <tr>
                                                        <td>${acc.indexOf(a) + 1}</td>
                                                        <td>${a.firstName}</td>
                                                        <td>${a.lastName}</td>
                                                        <td>${a.phone}</td>
                                                        <td>${a.address}</td>
                                                        <td>${a.email}</td>
                                                        <td class="text-right">
                                                            <div class="actions" style="display: inline-flex;">
                                                                <c:if test="${a.status == 1}">
                                                                    <button type="submit" class="btn btn-sm btn-success" onclick="confirmDeactivate(${a.userID},${a.status}, '${a.email}')">
                                                                        Activate
                                                                    </button>
                                                                </c:if>

                                                                <c:if test="${a.status == 0}">
                                                                    <button type="submit" class="btn btn-sm btn-danger" onclick="confirmActivate(${a.userID},${a.status}, '${a.email}')">
                                                                        Deactivate
                                                                    </button>
                                                                </c:if>
                                                                <a href="./viewusersdetail?id=${a.userID}" class="btn btn-sm bg-success-light mr-2" style="margin-left: 10px">
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
                                        <a href="./viewhouseowner?pageIndex=${param.pageIndex - 1}" class="page-link">Previous</a>
                                    </li>
                                    <c:if test="${param.pageIndex == null || param.pageIndex == 1}">
                                        <li class="paginate_button page-item active">
                                            <a href="#" class="page-link">1</a>
                                        </li>
                                        <c:if test="${hasNextPage != null}">
                                            <li class="paginate_button page-item ">
                                                <a href="./viewusers?pageIndex=2" class="page-link">2</a>
                                            </li>
                                        </c:if>

                                        <li
                                            class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                            id="DataTables_Table_0_next"
                                            >
                                            <a href="./viewusers?pageIndex=2" class="page-link">Next</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.pageIndex != null && param.pageIndex > 1}">
                                        <li class="paginate_button page-item ">
                                            <a href="./viewusers?pageIndex=${param.pageIndex - 1}" class="page-link">${param.pageIndex - 1}</a>
                                        </li>
                                        <li class="paginate_button page-item active">
                                            <a href="#" class="page-link">${param.pageIndex}</a>
                                        </li>
                                        <c:if test="${hasNextPage != null}">
                                            <li class="paginate_button page-item ">
                                                <a href="./viewhouseowner?pageIndex=${param.pageIndex + 1}" class="page-link">${param.pageIndex + 1}</a>
                                            </li>
                                        </c:if>

                                        <li
                                            class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                            id="DataTables_Table_0_next"
                                            >
                                            <a href="./viewusers?pageIndex=${param.pageIndex + 1}" class="page-link">Next</a>
                                        </li>
                                    </c:if>    

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade none-border" id="deactivate_confirm" style="display: none; background-color: hsl(0deg 4.19% 42.16% / 60%)">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Deactivate Account</h4>
                            </div>
                            <div class="modal-footer justify-content-center">
                                <form id="deactivate_form" action="#" method="post">
                                    <button type="submit" class="btn btn-danger">Deactivate</button>
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
                                <h4 class="modal-title">Activate Account</h4>
                            </div>
                            <div class="modal-footer justify-content-center">
                                <form id="activate_form" action="#" method="post">
                                    <button type="submit" class="btn btn-success" onclick="updateStatus()">Activate</button>
                                </form>
                                <button type="button" class="btn btn-light" onclick="cancelActivate()">Cancel</button>
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

                                    function confirmDeactivate(a, b, c) {
                                        var x = document.getElementById('deactivate_confirm');
                                        var y = document.getElementById('deactivate_form');
                                        y.action = 'viewusers?id=' + a + '&status=' + b;
                                        var modalTitle = document.querySelector('#deactivate_confirm .modal-title');
                                        modalTitle.innerHTML = 'Deactive Account: ' + c;
                                        x.classList.add('show');
                                        x.style.display = 'block';

                                    }

                                    function confirmActivate(a, b, c) {
                                        var x = document.getElementById('activate_confirm');
                                        var y = document.getElementById('activate_form');
                                        y.action = 'viewusers?id=' + a + '&status=' + b;
                                        var modalTitle = document.querySelector('#activate_confirm .modal-title');
                                        modalTitle.innerHTML = 'Active Accounts: ' + c;
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

                                    function updateStatus() {
                                        var x = document.getElementById('deactivate_confirm');
                                        x.classList.remove('show');
                                        x.style.display = 'none';
                                        var z = document.getElementById('update_status');
                                        z.classList.add('show');
                                        z.style.display = 'block';
                                    }
        </script>
 <script>
                                                function exportToExcel() {
                                                    const table = document.getElementById("yourTableId"); // Replace with your table ID
                                                    const rows = table.getElementsByTagName("tr");

                                                    let data = [];
                                                    data.push("Serial,First Name,Last Name,Phone,Address,Email,Status");
                                                    for (let i = 1; i < rows.length; i++) {
                                                        const row = rows[i];
                                                        const cells = row.getElementsByTagName("td");
                                                        let rowData = [];

                                                        for (let j = 0; j < cells.length; j++) {
                                                            let cellText = cells[j].innerText;
                                                            cells[j].style.fontFamily = "Arial, sans-serif";


                                                            if (j === 3) {
                                                                // Assuming column 4 is the phone number column
                                                                cellText = "'" + cellText; // Adding single quote to phone number column
                                                                rowData.push(cellText);
                                                            } else {
                                                                rowData.push(cellText);
                                                            }


                                                        }
                                                        data.push(rowData.join(","));
                                                    }

                                                    const csvContent = data.join("\n");
                                                    const encodedUri = encodeURI(csvContent);
                                                    const link = document.createElement("a");
                                                    //        link.setAttribute("href", encodedUri);
                                                    link.setAttribute("href", "data:text/csv;charset=utf-8,%EF%BB%BF" + encodedUri); // Add UTF-8 BOM to handle special characters
                                                    link.setAttribute("download", "users.csv");


                                                    document.body.appendChild(link);
                                                    link.click();
                                                }
                                            </script>
    </body>

</html>
