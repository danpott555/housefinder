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
                                <h3 class="page-title">Comments</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">House Detail</a></li>
                                    <li class="breadcrumb-item active">Comments</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.account.role == 1}">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="viewcomment" method="post">

                                            <div class="row">
                                                <div class="col-12">
                                                    <h5 class="form-title"><span>Comment for house</span></h5>
                                                    <div class="col-12 col-sm-6">
                                                        <input name="houseID" type="hidden" class="form-control" value="${sessionScope.houseID}" readonly>
                                                        <div class="form-group">
                                                            <label>Rate</label>
                                                            <input type="hidden" name="rate" id="selectedRate" value="5">
                                                            <div class="star-rating">
                                                                <span class="fa fa-star" onclick="setRating(1)"></span>
                                                                <span class="fa fa-star" onclick="setRating(2)"></span>
                                                                <span class="fa fa-star" onclick="setRating(3)"></span>
                                                                <span class="fa fa-star" onclick="setRating(4)"></span>
                                                                <span class="fa fa-star" onclick="setRating(5)"></span>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Comment</label>
                                                            <textarea name="comment" type="text" class="form-control" maxlength="1000" ></textarea>
                                                        </div>
                                                        <div class="col-12">
                                                            <button type="submit" class="btn btn-warning">Submit</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12 col-sm-6">
                                            <div class="form-group">
                                                <c:forEach items="${comments}" var="comment" varStatus="status">
                                                    <div class="comment-box" style="margin-bottom: 20px;  padding: 10px; border-radius: 5px;">
                                                        <div class="comment-header">
                                                            <c:if test="${sessionScope.account.userID == accounts[status.index].userID}">
                                                                <span class="comment-name">Your comment</span>
                                                            </c:if> 
                                                            <c:if test="${sessionScope.account.userID != accounts[status.index].userID}">
                                                                <span class="comment-name">${accounts[status.index].firstName} ${accounts[status.index].lastName}</span>
                                                            </c:if> 

                                                            <span class="comment-date">(${comment.dateComment})</span>
                                                        </div>
                                                        <div class="comment-content">
                                                            <div class="comment-rate">
                                                                <c:forEach begin="1" end="5" var="i">
                                                                    <span class="fa fa-star" style="color: ${i <= comment.rate ? '#ff9900' : '#ccc'};"></span>
                                                                </c:forEach>
                                                            </div>
                                                            <div class="comment-text">${comment.comment}</div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="col-12">
                                                <a href="./housedetail?id=${sessionScope.houseID}" style="color: white"><button class="btn btn-danger">Back</button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                            </div>
                        </div>
            <script>
                function setRating(rating) {
                    document.getElementById("selectedRate").value = rating;
                    var stars = document.querySelectorAll(".star-rating span");
                    for (var i = 1; i <= 5; i++) {
                        if (i <= rating) {
                            stars[i - 1].style.color = "#ff9900";
                        } else {
                            stars[i - 1].style.color = "#ccc";
                        }
                    }
                }

                window.onload = function () {
                    setRating(5);
                };
            </script>
            <script src="assets/js/jquery-3.6.0.min.js"></script>
            <script src="assets/js/popper.min.js"></script>
            <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
            <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
            <script src="assets/plugins/datatables/datatables.min.js"></script>
            <script src="assets/js/script.js"></script>
    </body>

</html>