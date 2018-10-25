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
		
		<fmt:message bundle="${loc}" key="local.employeeText" var="employeeText" />
		<fmt:message bundle="${loc}" key="local.employeeForm" var="employeeForm" />
		<fmt:message bundle="${loc}" key="local.userLogin" var="userLogin" />
		<fmt:message bundle="${loc}" key="local.userEmail" var="userEmail" />
		<fmt:message bundle="${loc}" key="local.userPassword" var="userPassword" />
		<fmt:message bundle="${loc}" key="local.submitButton" var="submitButton" />
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${userAddText}"/> – <c:out value="${appName}"/></title>

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
                                <h4 class="m-b-20 header-title"><c:out value="${userAddText}"/></h4>
                                <div class="row">
                                    <div class="col-md-6 m-b-20">
                                        <c:if test="${successUserSubmit}">
                                    		<div class="alert alert-success alert-dismissible fade in" role="alert">
                                    			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        			<span aria-hidden="true">×</span>
                                        		</button>
                                        		<strong>Well done!</strong> New user was successfully submitted!
                                    		</div>
                                    	</c:if>
                                    	<c:set var="successUserSubmit" value="false" scope="session"/>
                                        <form class="form-validation" role="form" method="POST" action="controller" id="userForm">
                                            <div class="form-group">
                                                <label for="employee"><c:out value="${employeeText}"/></label>
                                    			<select class="select2 form-control" id="selectEmployee" data-parsley-trigger="change" required data-placeholder="<c:out value="${employeeForm}"/>">
                                    			<option></option>
												<c:forEach var="employee" items="${employeeNotRegList}">
                                            		<option value="${employee.id}">${employee.name} ${employee.surname}</option>
                                        		</c:forEach>
                                    			</select>	
                                            </div>
                                            
                                            <div id="hdata"></div>
                                            
                                            <div class="form-group">
                                                <label for="login"><c:out value="${userLogin}"/></label>
                                                <input type="text" class="form-control" id="login" name="login" data-parsley-trigger="change" required placeholder="<c:out value="${userLogin}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="email"><c:out value="${userEmail}"/></label>
                                                <input type="email" class="form-control" id="email" name="email" data-parsley-trigger="change" required placeholder="<c:out value="${userEmail}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="password"><c:out value="${userPassword}"/></label>
                                                <input type="password" class="form-control" id="password" name="password" data-parsley-trigger="change" data-parsley-minlength="4" required placeholder="<c:out value="${userPassword}"/>">
                                            </div>
                                            
                                            <input type="hidden" name="commandName" value="ADD_USER" />
                                            <input type="hidden" name="pageName" value="user-add" />

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
                $('#userForm').parsley();
            });
        </script>

    </body>
</html>