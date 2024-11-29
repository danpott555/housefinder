<%-- 
    Document   : viewfeedback
    Created on : Aug 18, 2023, 2:03:03 PM
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
                                <h3 class="page-title">List Of Feedback</h3>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card card-table">
                                <div class="card-body">
                                    <div class="search-filter">
                                        <form action="searchfeedback" method="GET">
                                            <c:if test="${param.search_feedback == null}">
                                                <div class="col-4">
                                                    <label>Feedback Content</label>
                                                    <input name="search_feedback" type="text" class="form-control" placeholder="Input value"/>
                                                </div>
                                            </c:if>

                                            <c:if test="${param.search_feedback != null}">
                                                <div class="col-4">
                                                    <label>Content</label>
                                                    <input name="search_feedback" type="text" class="form-control" placeholder="Input value" value="${param.search_feedback}"/>
                                                </div>
                                            </c:if>

                                            <div class="col-2">
                                                <label>Search By</label><br>
                                                <select name="searchBy" style="padding: 6px; width: fit-content">
                                                    <option value="user_name"
                                                            <c:if test="${param.searchBy eq 'user_name'}">selected</c:if>>
                                                                User Name
                                                            </option>
                                                            <option value="reply_feedback"
                                                            <c:if test="${param.searchBy eq 'reply_feedback'}">selected</c:if>>
                                                                Replied                               
                                                            </option>
                                                    </select>
                                                </div>

                                                <div class="col-2">
                                                    <label>Option Name</label>
                                                    <select name="optionValue" style="padding: 6px; width: fit-content">
                                                        <option value="0" selected>--- Option Name ---</option>
                                                    <c:forEach items="${requestScope.feedbackOptions}" var="fo">
                                                        <option value="${fo.optionId}"
                                                                <c:if test="${param.optionValue eq fo.optionId}">selected=""</c:if>    
                                                                >${fo.optionName}</option>
                                                    </c:forEach>
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
                                                    <th>Feedback Option</th>
                                                    <th>Feedback Reply</th>                                     
                                                    <th>User Name</th>                                                   
                                                    <th class="text-right">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.feedbacks}" var="f" >
                                                    <tr>                                         
                                                        <td>${feedbacks.indexOf(f) + 1}</td>
                                                        <td>${f.feedbackOptionName}</td>
                                                        <c:if test="${f.feedbackReply != null}">
                                                            <td><i class="fas fa-check" style="color: green"></i></td>
                                                            </c:if>
                                                            <c:if test="${f.feedbackReply == null}">
                                                            <td><i class="fas fa-times" style="color: red"></i></td>
                                                            </c:if>
                                                        <td>${f.userName}</td>
                                                        <td class="text-right">
                                                            <!--onclick="openViewFeedback('${f.feedbackContent}', '${f.feedbackReply}', '${f.feedbackOptionName}', '${f.userName}')"-->
                                                            <c:if test="${f.feedbackReply == null}">
                                                                <div class="actions" style="display: inline-flex;">
                                                                    <a class="btn btn-sm bg-warning-light mr-2" 
                                                                       href="./replyfeedback?id=${f.feedbackId}">
                                                                        <i class="fas fa-comment"></i>
                                                                    </a>
                                                                </div>
                                                            </c:if>
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
        <script src="assets/plugins/datatables/datatables.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>