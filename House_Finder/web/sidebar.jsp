<%-- 
    Document   : sidebar
    Created on : Aug 18, 2023, 2:48:31 PM
    Author     : SMILY
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.*"%>
<c:choose>
    <c:when test="${sessionScope.account==null}">
        <div class="sidebar" id="sidebar">
            <div class="sidebar-inner slimscroll">
                <div id="sidebar-menu" class="sidebar-menu">
                    <ul>
                        <li class="menu-title">
                            <span>Main Menu</span>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fas fa-home"></i> <span> Dashboard</span> <span class="menu-arrow"></span></a>
                            <ul>
                                <li><a href="./home">List Available House</a></li>
                            </ul>
                        </li>
                        <li class="menu-title">
                            <span>Pages</span>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fas fa-shield-alt"></i> <span> Authentication </span> <span class="menu-arrow"></span></a>
                            <ul>
                                <li><a href="./login">Login</a></li>
                                <li><a href="./register">Register</a></li>
                                <li><a href="forgotpassword.jsp">Forgot Password</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </c:when>

    <c:when test="${sessionScope.account!=null}">
        <c:if test="${sessionScope.account.role == 3}">
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <ul>
                            <li class="menu-title">
                                <span>Main Menu</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-home"></i> <span> Dashboard</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./home">List Available House</a></li>
                                    <li><a href="./viewhouseowner">List House Owner</a></li>
                                    <li><a href="./viewusers">List User</a></li>
                                    <li><a href="./orderadmin">List Order</a></li>
                                </ul>
                            </li>
                            <li class="menu-title">
                                <span>Management</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-shield-alt"></i> <span> Authentication</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./changepassword">Change Password</a></li>
                                    <li><a href="./viewuserprofile">Profile</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-comment"></i> <span> Feedbacks</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./viewfeedback">Feedback List</a></li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.account.role == 2}">
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <ul>
                            <li class="menu-title">
                                <span>Main Menu</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-home"></i> <span> Dashboard</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./home">List Available House</a></li>
                                </ul>
                            </li>
                            <li class="menu-title">
                                <span>Management</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-shield-alt"></i> <span> Authentication</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./changepassword">Change Password</a></li>
                                    <li><a href="./viewuserprofile">Profile</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-house-user"></i> <span> House</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./housesowner">My House</a></li>
                                    <li><a href="./ordercontroller">Order</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-comment"></i> <span> Feedback</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./sendfeedback">Send Feedback</a></li>
                                    
                                    <li><a href="./feedbackhistory">Feedback History</a></li>
                                </ul>
                            </li>                       
                        </ul>
                    </div>
                </div>
            </div>
        </c:if> 
        <c:if test="${sessionScope.account.role == 1}">
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <ul>
                            <li class="menu-title">
                                <span>Main Menu</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-home"></i> <span> Dashboard</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./home">List Available House</a></li>
                                </ul>
                            </li>
                            <li class="menu-title">
                                <span>Management</span>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-shield-alt"></i> <span> Authentication</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./changepassword">Change Password</a></li>
                                    <li><a href="./viewuserprofile">Profile</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fas fa-house-user"></i> <span> House</span> <span class="menu-arrow"></span></a>
                                <ul>
                                    <li><a href="./orderrenter">My Order</a></li>
                                </ul>
                            </li>

                            <li class="submenu">
                                <a href="#"><i class="fas fa-comment"></i> <span> Feedback</span>  <span class="menu-arrow"></span></a> 
                                <ul>
                                    <li><a href="./sendfeedback">Send Feedback</a></li>
                                
                                    <li><a href="./feedbackhistory">Feedback History</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if> 

    </c:when>

</c:choose>
