<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="<c:out value="${sessionScope.local}"/>">
    <head>
        <meta charset="utf-8" />
        
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.appName" var="appName" />
		<fmt:message bundle="${loc}" key="local.searchBookText" var="searchBookText" />			
		<fmt:message bundle="${loc}" key="local.adminText" var="adminText" />
		<fmt:message bundle="${loc}" key="local.userText" var="userText" />
		<fmt:message bundle="${loc}" key="local.profileText" var="profileText" />
		<fmt:message bundle="${loc}" key="local.logoutText" var="logoutText" />
		<fmt:message bundle="${loc}" key="local.devByText" var="devByText" />
		<fmt:message bundle="${loc}" key="local.dashboardText" var="dashboardText" />
		<fmt:message bundle="${loc}" key="local.booksText" var="booksText" />
		<fmt:message bundle="${loc}" key="local.ordersText" var="ordersText" />
		<fmt:message bundle="${loc}" key="local.usersText" var="usersText" />
		<fmt:message bundle="${loc}" key="local.bookAddText" var="bookAddText" />
		<fmt:message bundle="${loc}" key="local.bookListText" var="bookListText" />
		<fmt:message bundle="${loc}" key="local.orderAddText" var="orderAddText" />
		<fmt:message bundle="${loc}" key="local.orderListText" var="orderListText" />
		<fmt:message bundle="${loc}" key="local.userAddText" var="userAddText" />
		<fmt:message bundle="${loc}" key="local.userListText" var="userListText" />
		<fmt:message bundle="${loc}" key="local.reportText" var="reportText" />
		
		<fmt:message bundle="${loc}" key="local.newBooksText" var="newBooksText" />
		<fmt:message bundle="${loc}" key="local.itemId" var="itemId" />
		
		<fmt:message bundle="${loc}" key="local.bookTitle" var="bookTitle" />
		<fmt:message bundle="${loc}" key="local.bookDescription" var="bookDescription" />
		<fmt:message bundle="${loc}" key="local.bookYear" var="bookYear" />
		<fmt:message bundle="${loc}" key="local.bookAuthor" var="bookAuthor" />
		<fmt:message bundle="${loc}" key="local.userLogin" var="userLogin" />
		<fmt:message bundle="${loc}" key="local.employeeText" var="employeeText" />
		<fmt:message bundle="${loc}" key="local.orderDate" var="orderDate" />
		<fmt:message bundle="${loc}" key="local.orderStatus" var="orderStatus" />
		
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${dashboardText}"/> – <c:out value="${appName}"/></title>

        <link rel="shortcut icon" href="assets/images/favicon.ico">
        
        <!-- Sweet Alert -->
        <link href="assets/plugins/sweet-alert2/sweetalert2.min.css" rel="stylesheet" type="text/css">

        <!-- DataTables -->
        <link href="assets/plugins/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/buttons.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/scroller.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/dataTables.colVis.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/fixedColumns.dataTables.min.css" rel="stylesheet" type="text/css"/>

        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- MetisMenu CSS -->
        <link href="assets/css/metisMenu.min.css" rel="stylesheet">
        <!-- Icons CSS -->
        <link href="assets/css/icons.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="assets/css/style.css" rel="stylesheet">

    </head>


    <body>

        <div id="page-wrapper">

            <!-- Top Bar Start -->
            <%@include file="header.jsp"%>
            <!-- Top Bar End -->

            <!-- Page content start -->
            <div class="page-contentbar">

                <!--left navigation start-->
                <%@include file="sidebar.jsp"%>
                <!--left navigation end-->

                <!-- START PAGE CONTENT -->
                <div id="page-right-content">

                    <div class="container">                      
                        <div class="row">
                            <div class="col-sm-12">
                         	<h4 class="m-t-0 header-title"><c:out value="${dashboardText}"/></h4>
                         	
                         		<div class="message ${messageClass} hide" ></div>
                                <c:remove var="messageClass" scope="session" />
                         	
                                <div class="card-box">
                                    <h4 class="m-t-0"><c:out value="${ordersText}"/></h4>
                                    <div class="table-responsive">
                                        <table class="table table-hover mails m-0 table table-actions-bar">
                                            <thead>
                                                <tr>
                                                    <th><c:out value="${itemId}"/></th>
                                                    <th><c:out value="${bookTitle}"/></th>
                                                    <th><c:out value="${userLogin}"/></th>
                                                    <th><c:out value="${employeeText}"/></th>
                                                    <th><c:out value="${orderDate}"/></th>
                                                    <th><c:out value="${orderStatus}"/></th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                            	<c:forEach var="order" items="${orderLastList}">
                                                <tr>
                                                    <td><c:out value="${order.id}" /></td>
                                                    <td><c:set var="book" value = "${bookMap[order.bookId]}"/><c:out value="${book.title}" /></td>
         											<td><c:set var="user" value = "${userMap[order.emplId]}"/><c:out value="${empty user.login ? 'no login' : user.login}" /></td>
                                                    <td><c:set var="employee" value = "${employeeMap[order.emplId]}"/><c:out value="${employee.name} ${employee.surname}" /></td>
                                                    <td><c:out value="${order.date}" /></td>
                                                    <td><span class="label label-default"><c:out value="${order.status}" /></span></td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--end row -->
                        
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    <h4 class="m-t-0"><c:out value="${newBooksText}"/></h4>
                                    <div class="table-responsive">
                                        <table class="table table-hover mails m-0 table table-actions-bar">
                                            <thead>
                                        		<tr>
                                            		<th><c:out value="${itemId}"/></th>
                                            		<th><c:out value="${bookTitle}"/></th>
                                            		<th><c:out value="${bookDescription}"/></th>
                                            		<th><c:out value="${bookYear}"/></th>
                                            		<th><c:out value="${bookAuthor}"/></th>
                                        		</tr>
                                            </thead>

                                            <tbody>
                                        	<c:forEach var="book" items="${bookLastList}">
                                        		<tr>
                                            		<td><c:out value="${book.id}" /></td>
                                            		<td><c:out value="${book.title}" /></td>
                                            		<td><c:out value="${book.description}" /></td>
													<td><c:out value="${book.year}" /></td>
													<td><c:out value="${book.author}" /></td>
                                        		</tr>
                                        	</c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--end row -->
                    </div>
                    <!-- end container -->
					<%@include file="footer.jsp"%>
                    <!-- end footer -->

                </div>
                <!-- End #page-right-content -->

            </div>
            <!-- end .page-contentbar -->
        </div>
        <!-- End #page-wrapper -->



        <!-- js placed at the end of the document so the pages load faster -->
        <script src="assets/js/jquery-2.1.4.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/metisMenu.min.js"></script>
        <script src="assets/js/jquery.slimscroll.min.js"></script>
        
        <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="assets/plugins/parsleyjs/parsley.min.js" type="text/javascript"></script>

        <!-- Datatable js -->
        <script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
        <script src="assets/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="assets/plugins/datatables/buttons.bootstrap.min.js"></script>
        <script src="assets/plugins/datatables/jszip.min.js"></script>
        <script src="assets/plugins/datatables/pdfmake.min.js"></script>
        <script src="assets/plugins/datatables/vfs_fonts.js"></script>
        <script src="assets/plugins/datatables/buttons.html5.min.js"></script>
        <script src="assets/plugins/datatables/buttons.print.min.js"></script>
        <script src="assets/plugins/datatables/dataTables.keyTable.min.js"></script>
        <script src="assets/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="assets/plugins/datatables/responsive.bootstrap.min.js"></script>
        <script src="assets/plugins/datatables/dataTables.scroller.min.js"></script>
        <script src="assets/plugins/datatables/dataTables.colVis.js"></script>
        <script src="assets/plugins/datatables/dataTables.fixedColumns.min.js"></script>

        <!-- init -->
        <script src="assets/pages/jquery.datatables.init.js"></script>
        
        <!-- Sweet-Alert  -->
        <script src="assets/plugins/sweet-alert2/sweetalert2.min.js"></script>
        <script src="assets/pages/jquery.sweet-alert.init.js"></script>

        <!-- App Js -->
        <script src="assets/js/jquery.app.js"></script>

    </body>
</html>