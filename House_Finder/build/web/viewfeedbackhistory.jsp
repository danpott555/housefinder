<%-- 
    Document   : viewfeedbackhistory
    Created on : Aug 18, 2023, 1:37:01 PM
    Author     : Asus
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

        <link rel="stylesheet" href="assets/plugins/datatables/datatables.min.css">

        <link rel="stylesheet" href="assets/css/style.css">

    </head>
    <body>

        <div class="main-wrapper">

            <%@include file="navbar.jsp" %>
            <%@include file="sidebar.jsp" %>

            <div class="page-wrapper">
                <div class="content container-fluid">

                    <div class="page-header">
                        <div class="row align-items-center">
                            <div class="col">
                                <h3 class="page-title">Feedback History</h3>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card card-table">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-center mb-0 datatable">
                                            <thead>
                                                <tr>
                                                    <th>Serial</th>
                                                    <th>Feedback Content</th>
                                                    <th>Feedback Reply</th>
                                                    <th>Feedback Option</th>                                                                                                                               
                                                    <th class="text-right">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${feedbacks.isEmpty()}">
                                                    <tr>
                                                        <td colspan="6" class="text-center" style="color: red; font-size: 24px; font-weight: bold">No Data Available<td>
                                                    </tr>
                                                </c:if>
                                                <c:forEach items="${requestScope.feedbacks}" var="f" >
                                                    <tr>                                         
                                                        <td>${feedbacks.indexOf(f) + 1}</td>
                                                        <td>${f.feedbackContent}</td>
                                                        <td>${f.feedbackReply}</td>
                                                        <td>${f.feedbackOptionName}</td>

                                                        <td class="text-right">
                                                            <div class="actions" style="display: inline-flex;">
                                                                <a class="btn btn-sm bg-success-light mr-2" style="margin-left: 10px" 
                                                                   href="./viewfeedbackdetail?feedbackId=${f.feedbackId}" >
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
                                        <a href="./feedbackhistory?pageIndex=${param.pageIndex - 1}" class="page-link">Previous</a>
                                    </li>
                                    <c:if test="${param.pageIndex == null || param.pageIndex == 1}">
                                        <li class="paginate_button page-item active">
                                            <a href="#" class="page-link">1</a>
                                        </li>
                                        <c:if test="${hasNextPage != null}">
                                            <li class="paginate_button page-item ">
                                                <a href="./feedbackhistory?pageIndex=2" class="page-link">2</a>
                                            </li>
                                        </c:if>

                                        <li
                                            class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                            id="DataTables_Table_0_next"
                                            >
                                            <a href="./feedbackhistory?pageIndex=2" class="page-link">Next</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.pageIndex != null && param.pageIndex > 1}">
                                        <li class="paginate_button page-item ">
                                            <a href="./feedbackhistory?pageIndex=${param.pageIndex - 1}" class="page-link">${param.pageIndex - 1}</a>
                                        </li>
                                        <li class="paginate_button page-item active">
                                            <a href="#" class="page-link">${param.pageIndex}</a>
                                        </li>
                                        <c:if test="${hasNextPage != null}">
                                            <li class="paginate_button page-item ">
                                                <a href="./feedbackhistory?pageIndex=${param.pageIndex + 1}" class="page-link">${param.pageIndex + 1}</a>
                                            </li>
                                        </c:if>

                                        <li
                                            class="paginate_button page-item next ${hasNextPage == null ? 'disabled' : ''}"
                                            id="DataTables_Table_0_next"
                                            >
                                            <a href="./feedbackhistory?pageIndex=${param.pageIndex + 1}" class="page-link">Next</a>
                                        </li>
                                    </c:if>    

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

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
    </body>
</html>
