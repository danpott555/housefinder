<%-- 
    Document   : navbar
    Created on : Aug 18, 2023, 2:48:24 PM
    Author     : SMILY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<div class="header">

    <div class="header-left">
        <a href="./home" class="logo">
            <img src="assets/img/fpt.png" alt="Logo">
        </a>
        <a href="./home" class="logo logo-small">
            <img src="assets/img/logo.jpg" alt="Logo" width="30" height="30">
        </a>
    </div>

    <a href="javascript:void(0);" id="toggle_btn">
        <i class="fas fa-align-left"></i>
    </a>

    <ul class="nav user-menu">
        <c:choose>
            <c:when test="${sessionScope.account==null}">

                <button><a href="./login">Log in</a></button>
            </c:when>

            <c:when test="${sessionScope.account!=null}">
                <li class="nav-item dropdown has-arrow">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                        <span class="user-img"><img class="rounded-circle" src="assets/img/10__2850_29.jpg" width="50" ></span>
                    </a>
                    <div class="dropdown-menu">
                        <div class="user-header">
                            <div class="user-text">
                                <h6>${sessionScope.account.firstName} ${sessionScope.account.lastName}</h6>
                                <c:if test="${sessionScope.account.role == 3}">
                                    <p class="text-muted mb-0">Administrator</p>
                                </c:if>
                                <c:if test="${sessionScope.account.role == 2}">
                                    <p class="text-muted mb-0">House Owner</p>
                                </c:if> 
                                <c:if test="${sessionScope.account.role == 1}">
                                    <p class="text-muted mb-0">Renter</p>
                                </c:if> 
                            </div>
                        </div>
                        <a class="dropdown-item" href="viewuserprofile">My Profile</a>
                        <a class="dropdown-item" href="changepassword">Change Password</a>
                        <a class="dropdown-item" href="./login">Logout</a>
                    </div>
                </li>
            </c:when>

        </c:choose>
    </ul>

</div>
