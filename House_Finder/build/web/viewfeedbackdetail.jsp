<%-- 
    Document   : viewfeedbackdetail
    Created on : Aug 18, 2023, 1:49:50 PM
    Author     : Asus
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
    <c:if test="${cancelStatus != null}">
        <body onload="showSnackbar()">
        </c:if>
        <c:if test="${cancelStatus == null}">
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
                                <h3 class="page-title">Feedback Detail</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="./viewfeedback">List Of Feedbacks</a></li>
                                    <li class="breadcrumb-item active">Feedback Detail</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <c:set value="${requestScope.feedback}" var="f"/>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <h5 class="form-title"><span>Feedback Details</span></h5>
                                            <h5 class="form-title"><span style="color: red">${alearMess}</span></h5>
                                        </div>                      
                                        <c:if test="${f != null}">
                                            <div class="col-12 ">
                                                <div class="form-group">
                                                    <label><strong>Feedback Content: </strong>${f.feedbackContent}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 ">
                                                <div class="form-group">
                                                    <label><strong>Feedback Reply: </strong>${f.feedbackReply}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 ">
                                                <div class="form-group">
                                                    <label><strong>Option Name: </strong>${f.feedbackOptionName}</label>
                                                </div>
                                            </div>
                                            <div class="col-12 ">
                                                <div class="form-group">
                                                    <label><strong>User Name: </strong>${f.userName}</label>
                                                </div>
                                            </div>

                                            <c:if test="${f.feedbackStatus}">
                                                <div class="col-12 ">
                                                    <div class="form-group">
                                                        <button class="btn btn-danger" onclick="confirmClose()" style="margin-top: 10px">Cancel</button>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${f ==null}">
                                            <div class="col-12 text-center ">
                                                <div class="form-group">
                                                    <label style="font-size: 40px ; color: red"><strong>NO DATA AVAILABLE </strong></label>
                                                </div>
                                            </div>

                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade none-border" id="close_confirm" style="display: none; background-color: hsl(0deg 4.19% 42.16% / 60%);">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Cancel feedback</h4>
                            </div>
                            <div class="modal-footer justify-content-center">
                                <form id="close_form" action="viewfeedbackdetail?feedbackId=${f.feedbackId}&change_status=true" method="post">
                                    <button type="submit" class="btn btn-success">Yes</button>
                                </form>
                                <button type="button" class="btn btn-light" onclick="cancelClose()">Cancel</button>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
            <div id="snackbar">Cancel Successful!</div> 

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
                                    function cancelClose() {
                                        var x = document.getElementById('close_confirm');
                                        x.classList.remove('show');
                                        x.style.display = 'none';
                                    }
                                    function confirmClose() {
                                        var x = document.getElementById('close_confirm');
                                        x.classList.add('show');
                                        x.style.display = 'block';

                                    }
            </script>


    </body>

</html>