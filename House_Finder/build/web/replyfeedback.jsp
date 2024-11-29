<%-- 
    Document   : replyfeedback
    Created on : Aug 16, 2023, 12:03:59 PM
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

    <c:if test="${replyStatus != null}">
        <body onload="showSnackbar()">
        </c:if>
        <c:if test="${replyStatus == null}">
        <body>
        </c:if>
        <c:if test="${closeStatus != null}">
        <body onload="showSnackbarClose()">
        </c:if>
        <c:if test="${closeStatus == null}">
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
                                <h3 class="page-title">Feedback</h3>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Feedback</a></li>
                                    <li class="breadcrumb-item active">Reply Feedback</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="replyfeedback?id=${feedback.feedbackId}" method="post">
                                        <div class="row">
                                            <div class="col-12">
                                                <h5 class="form-title"><span>Reply Feedback</span></h5>
                                                <h5 class="form-title"><span style="color: red">${alertMess}</span></h5>
                                            </div>
                                            <div class="col-12 col-sm-10">
                                                <div class="form-group">
                                                    <label>Email</label>
                                                    <input type="text" class="form-control" value="${userSend.email}" disabled>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-10">
                                                <div class="form-group">
                                                    <label>Option</label>
                                                    <c:forEach items="${option}" var="o">
                                                        <c:if test="${o.optionId == feedback.feedbackOptionId}">
                                                            <input type="text" class="form-control" value="${o.optionName}" disabled>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <label>Description</label>
                                                    <textarea name="content" class="form-control" maxlength="1000" style="height: 250px" disabled>${feedback.feedbackContent}</textarea>
                                                </div>
                                            </div>

                                            <c:if test="${feedback.feedbackReply == null && feedback.feedbackStatus}">
                                                <div class="col-12">
                                                    <div class="form-group">
                                                        <label>Reply</label>
                                                        <label style="color: red">*</label>
                                                        <textarea name="reply_content" class="form-control" maxlength="1000" style="height: 250px" required></textarea>
                                                        <c:if test="${validateContent != null}">
                                                            <span style="color: red">Content can not contains only spaces</span>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <button type="submit" class="btn btn-primary">Reply</button>
                                                </div>
                                            </c:if>

                                            <c:if test="${feedback.feedbackReply != null}">
                                                <div class="col-12">
                                                    <div class="form-group">
                                                        <label>Reply</label>
                                                        <label style="color: red">*</label>
                                                        <textarea name="reply_content" class="form-control" maxlength="1000" style="height: 250px" disabled>${feedback.feedbackReply}</textarea>
                                                    </div>
                                                </div>
                                            </c:if>

                                        </div>
                                    </form>
                                    <c:if test="${feedback.feedbackStatus}">
                                        <button class="btn btn-danger" onclick="confirmClose()" style="margin-top: 10px">Close</button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>              
                </div>
                <div class="modal fade none-border" id="close_confirm" style="display: none; background-color: hsl(0deg 4.19% 42.16% / 60%);">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Close reply</h4>
                            </div>
                            <div class="modal-footer justify-content-center">
                                <form id="close_form" action="replyfeedback?id=${feedback.feedbackId}&change_status=true" method="post">
                                    <button type="submit" class="btn btn-success">Yes</button>
                                </form>
                                <button type="button" class="btn btn-light" onclick="cancelClose()">Cancel</button>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="snackbar">Reply Successful!</div> 
        <div id="snackbar_close">Close Successful!</div> 

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
                                    function showSnackbarClose() {
                                        var x = document.getElementById("snackbar_close");
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
