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
		
		<fmt:message bundle="${loc}" key="local.bookEditText" var="bookEditText" />
		<fmt:message bundle="${loc}" key="local.bookTitle" var="bookTitle" />
		<fmt:message bundle="${loc}" key="local.bookDescription" var="bookDescription" />
		<fmt:message bundle="${loc}" key="local.bookYear" var="bookYear" />
		<fmt:message bundle="${loc}" key="local.bookAuthor" var="bookAuthor" />
		<fmt:message bundle="${loc}" key="local.bookQuantity" var="bookQuantity" />
		
		<fmt:message bundle="${loc}" key="local.saveButton" var="saveButton" />
		<fmt:message bundle="${loc}" key="local.cancelButton" var="cancelButton" />
		<fmt:message bundle="${loc}" key="local.deleteButton" var="deleteButton" />
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${bookEditText}"/> – <c:out value="${appName}"/></title>

        <link rel="shortcut icon" href="assets/images/favicon.ico">

        <!-- Plugins css-->
        <link href="assets/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets/plugins/switchery/switchery.min.css">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
		<link href="assets/plugins/clockpicker/css/bootstrap-clockpicker.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
        <!-- Summernote css -->
        <link href="assets/plugins/summernote/summernote.css" rel="stylesheet" />

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
                                <h4 class="m-b-20 header-title"><c:out value="${bookEditText}"/></h4>
                                <div class="row">
                                    <div class="col-md-6 m-b-20">
                                    
                                		<div class="message ${messageClass} hide" ></div>
                                		<c:remove var="messageClass" scope="session" />
                                		
                                        <form class="form-validation" role="form" method="POST" action="controller" id="bookForm">
                                            <div class="form-group">
                                                <label for="booktitle"><c:out value="${bookTitle}"/></label>
                                                <input type="text" class="form-control" id="title" name="title" data-parsley-trigger="change" value="${editBook.title}" required placeholder="<c:out value="${bookTitle}"/>">
                                            </div>
                                            <div class="form-group">
                                                <label for="description"><c:out value="${bookDescription}"/></label>
                                                <textarea class="form-control" id="description" name="description" rows="5" data-parsley-trigger="change" required placeholder="<c:out value="${bookDescription}"/>">${editBook.description}</textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="author"><c:out value="${bookAuthor}"/></label>
                                                <input type="text" class="form-control" id="author" name="author" data-parsley-trigger="change" value="${editBook.author}" required placeholder="<c:out value="${bookAuthor}"/>">
                                            </div>
                                            <div class="form-group">
                                                <label for="year"><c:out value="${bookYear}"/></label>
                                                <input data-parsley-type="number" data-parsley-range="[1950, 2018]" class="form-control" id="year" name="year" data-parsley-trigger="change" value="${editBook.year}" required placeholder="<c:out value="${bookYear}"/>">
                                            </div>
                                            <div class="form-group m-b-30">
                                                <label for="quantity"><c:out value="${bookQuantity}"/></label>
                                                <input data-parsley-type="number" data-parsley-range="[1, 1000]" class="form-control" id="count" name="count" data-parsley-trigger="change" value="${editBook.count}" required placeholder="<c:out value="${bookQuantity}"/>">
                                            </div>
                                                                                   
                                            <div class="form-group col-xs-6 no-padding">
                                            
                                                <input type="hidden" name="commandName" value="EDIT_BOOK" />
                                            	<input type="hidden" name="editId" value="${editBook.id}" />
                                            	<input type="hidden" name="forwardPage" value="book-edit.jsp" />
                                            	
                                                <button type="submit" id="update" class="btn btn-primary" name="submit" value="update"><c:out value="${saveButton}"/></button>
                                                <button type="reset" class="btn btn-default waves-effect m-l-5"><c:out value="${cancelButton}"/></button>
                                            </div>
                                        </form>
                                        <div class="col-xs-6 no-padding"> 
                                        	<form role="form" method="POST" action="controller" id="bookDeleteForm">
                                        	
                                        		<input type="hidden" name="commandName" value="DELETE_BOOK" />
                                            	<input type="hidden" name="deleteId" value="${editBook.id}" />
                                            	<input type="hidden" name="forwardPage" value="book-edit.jsp" />
                                        	
                                        		<button type="submit" id="delete" class="btn btn-danger btn-primary pull-right" name="submit" value="delete"><c:out value="${deleteButton}"/></button>
                                        	</form>
										</div>

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
        
        <script src="assets/plugins/moment/moment.js"></script>
     	<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script>
     	<script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
     	<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
     	<script src="assets/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script>
     	<script src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
        <script src="assets/plugins/summernote/summernote.min.js"></script>

        <!-- form advanced init js -->
        <script src="assets/pages/jquery.form-advanced.init.js"></script>

        <!-- App Js -->
        <script src="assets/js/jquery.app.js"></script>
        
        
        <script type="text/javascript">
        	$(document).ready(function () {
        	  	$('#bookForm').parsley();
        	});
        </script>

    </body>
</html>