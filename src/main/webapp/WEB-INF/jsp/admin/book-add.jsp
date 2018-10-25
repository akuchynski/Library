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
		
		<fmt:message bundle="${loc}" key="local.bookTitle" var="bookTitle" />
		<fmt:message bundle="${loc}" key="local.bookDescription" var="bookDescription" />
		<fmt:message bundle="${loc}" key="local.bookYear" var="bookYear" />
		<fmt:message bundle="${loc}" key="local.bookAuthor" var="bookAuthor" />
		<fmt:message bundle="${loc}" key="local.bookQuantity" var="bookQuantity" />
		
		<fmt:message bundle="${loc}" key="local.submitButton" var="submitButton" />

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${bookAddText}"/> – <c:out value="${appName}"/></title>

        <link rel="shortcut icon" href="assets/images/favicon.ico">

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
                                <h4 class="m-b-20 header-title"><c:out value="${bookAddText}"/></h4>
                                <div class="row">
                                    <div class="col-md-6 m-b-20">
                                    	<c:if test="${successBookSubmit}">
                                    		<div class="alert alert-success alert-dismissible fade in" role="alert">
                                    			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        			<span aria-hidden="true">×</span>
                                        		</button>
                                        		<strong>Well done!</strong> New book was successfully submitted!
                                    		</div>
                                    	</c:if>
                                    	<c:set var="successBookSubmit" value="false" scope="session"/>
                                        <form class="form-validation" role="form" method="POST" action="controller" id="bookForm">
                                            <div class="form-group">
                                                <label for="booktitle"><c:out value="${bookTitle}"/></label>
                                                <input type="text" class="form-control" id="title" name="title" data-parsley-trigger="change" required placeholder="<c:out value="${bookTitle}"/>">
                                            </div>
                                            <div class="form-group">
                                                <label for="description"><c:out value="${bookDescription}"/></label>
                                                <textarea class="form-control" id="description" name="description" rows="5" data-parsley-trigger="change" required placeholder="<c:out value="${bookDescription}"/>"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="author"><c:out value="${bookAuthor}"/></label>
                                                <input type="text" class="form-control" id="author" name="author" data-parsley-trigger="change" required placeholder="<c:out value="${bookAuthor}"/>">
                                            </div>
                                            <div class="form-group">
                                                <label for="year"><c:out value="${bookYear}"/></label>
                                                <input data-parsley-type="number" data-parsley-range="[1950, 2018]" class="form-control" id="year" name="year" data-parsley-trigger="change" required placeholder="<c:out value="${bookYear}"/>">
                                            </div>
                                            <div class="form-group m-b-30">
                                                <label for="quantity"><c:out value="${bookQuantity}"/></label>
                                                <input data-parsley-type="number" data-parsley-range="[1, 1000]" class="form-control" id="count" name="count" data-parsley-trigger="change" required placeholder="<c:out value="${bookQuantity}"/>">
                                            </div>
                                            
                                            <input type="hidden" name="commandName" value="ADD_BOOK" />
                                            <input type="hidden" name="pageName" value="book-add" />
                                            
                                            <div class="form-group">    
                                                <button type="submit" class="btn btn-primary"><c:out value="${submitButton}"/></button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- end row -->
                            </div>
							<!-- end col -->
                        </div>
                        <!-- end row -->
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

        <script src="assets/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js" type="text/javascript"></script>
        <script src="assets/plugins/switchery/switchery.min.js"></script>
        <script src="assets/plugins/parsleyjs/parsley.min.js" type="text/javascript"></script>
        <script src="assets/plugins/parsleyjs/i18n/ru.js" type="text/javascript"></script>
        
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

        <!-- App Js -->
        <script src="assets/js/jquery.app.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $('#bookForm').parsley();
            });
        </script>

    </body>
</html>