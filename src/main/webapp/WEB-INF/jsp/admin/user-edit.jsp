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
		
		<fmt:message bundle="${loc}" key="local.userEditText" var="userEditText" />
		<fmt:message bundle="${loc}" key="local.userLogin" var="userLogin" />
		<fmt:message bundle="${loc}" key="local.userEmail" var="userEmail" />
		<fmt:message bundle="${loc}" key="local.employeeName" var="employeeName" />
		<fmt:message bundle="${loc}" key="local.employeeSurname" var="employeeSurname" />
		<fmt:message bundle="${loc}" key="local.employeeYear" var="employeeYear" />
		<fmt:message bundle="${loc}" key="local.userPassword" var="userPassword" />
		<fmt:message bundle="${loc}" key="local.userOldPassword" var="userOldPassword" />
		<fmt:message bundle="${loc}" key="local.userNewPassword" var="userNewPassword" />
		<fmt:message bundle="${loc}" key="local.userActive" var="userActive" />
		<fmt:message bundle="${loc}" key="local.userDeactive" var="userDeactive" />
		
		<fmt:message bundle="${loc}" key="local.addAvatarText" var="addAvatarText" />
		<fmt:message bundle="${loc}" key="local.addAvatarForm" var="addAvatarForm" />
		<fmt:message bundle="${loc}" key="local.selectFileButton" var="selectFileButton" />
		
		<fmt:message bundle="${loc}" key="local.saveButton" var="saveButton" />
		<fmt:message bundle="${loc}" key="local.cancelButton" var="cancelButton" />
		<fmt:message bundle="${loc}" key="local.deleteButton" var="deleteButton" />
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${userEditText}"/> – <c:out value="${appName}"/></title>

        <link rel="shortcut icon" href="assets/images/favicon.ico">
        
        <!-- Sweet Alert -->
        <link href="assets/plugins/sweet-alert2/sweetalert2.min.css" rel="stylesheet" type="text/css">

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
                                <h4 class="m-b-20 header-title"><c:out value="${userEditText}"/></h4>
                                
                                <div class="message ${messageClass} hide" ></div>
                                <c:remove var="messageClass" scope="session" />
                                
                                <div class="row">
                                    <div class="col-md-4 m-b-20">

                                        <form class="form-validation" role="form" method="POST" action="controller" id="userForm">
 
                                            <div id="hdata"></div>
                                        	
                                        	<div class="form-group">
                                                <label for="login"><c:out value="${userLogin}"/></label>
                                                <input type="text" class="form-control" id="login" name="login" data-parsley-trigger="change" readonly value="${editUser.login}">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="email"><c:out value="${userEmail}"/></label>
                                                <input type="email" class="form-control" id="email" name="email" data-parsley-trigger="change" value="${editUser.email}" required placeholder="<c:out value="${userEmail}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="name"><c:out value="${employeeName}"/></label>
                                                <input type="text" class="form-control" id="name" name="name" data-parsley-trigger="change" value="${editEmployee.name}" required placeholder="<c:out value="${employeeName}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="surname"><c:out value="${employeeSurname}"/></label>
                                                <input type="text" class="form-control" id="surname" name="surname" data-parsley-trigger="change" value="${editEmployee.surname}" required placeholder="<c:out value="${employeeSurname}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="year"><c:out value="${employeeYear}"/></label>
                                                <input data-parsley-type="number" class="form-control" id="year" name="year" data-parsley-range="[1950, 2000]" data-parsley-trigger="change" value="${editEmployee.year}" required placeholder="<c:out value="${employeeYear}"/>">
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="old_password"><c:out value="${userOldPassword}"/></label>
                                                <input type="password" class="form-control" id="old_password" name="old_password" data-parsley-trigger="change" data-parsley-minlength="4" value="${editUser.password}" required placeholder="<c:out value="${userOldPassword}"/>">
                                            </div>
                                            
                                            <div class="form-group m-b-20">
                                                <label for="new_password"><c:out value="${userNewPassword}"/></label>
                                                <input type="password" class="form-control" id="new_password" name="new_password" data-parsley-trigger="change" data-parsley-minlength="4" placeholder="<c:out value="${userNewPassword}"/>">
                                            </div>
                                            
                                            <div class="form-group m-b-20">
                                            	<div class="radio radio-info radio-inline">
                                                	<input id="inlineRadio1" value="true" name="radioActive" ${editUser.active eq true?'checked':''} type="radio">
                                                	<label for="inlineRadio1"> <c:out value="${userActive}"/> </label>
                                            	</div>
                                            	<div class="radio radio-inline">
                                                	<input id="inlineRadio2" value="false" name="radioActive" ${editUser.active eq false?'checked':''} ${editUser.id eq currentUserId?'disabled':''} type="radio">
                                                	<label for="inlineRadio2"> <c:out value="${userDeactive}"/> </label>
                                            	</div>
                                            </div>

                                            <div class="form-group col-xs-6 no-padding">
                                            
                                                <input type="hidden" name="commandName" value="edit_user" />
                                            	<input type="hidden" name="editId" value="${editUser.id}" />
                                            
                                                <button type="submit" id="update" class="btn btn-primary"><c:out value="${saveButton}"/></button>
                                                <button type="reset" class="btn btn-default waves-effect m-l-5"><c:out value="${cancelButton}"/></button>
                                            </div>
                                        </form>
                                        <div class="col-xs-6 no-padding"> 
                                        	<form role="form" method="POST" action="controller" id="userDeleteForm">
                                        	
                                        	    <input type="hidden" name="commandName" value="delete_user" />
                                            	<input type="hidden" name="deleteId" value="${editUser.id}" />
                                        	
                                        		<button type="submit" id="delete" class="btn btn-danger btn-primary pull-right"><c:out value="${deleteButton}"/></button>
                                        	</form>
										</div>
                                    </div>
                                    
                                    	<div class="col-md-4 m-b-20"> 
                                        	<form role="form" method="POST" action="controller?commandName=upload_avatar&editId=${editUser.id}" enctype="multipart/form-data">
                                        	
                                        	    <div class="form-group">
                                        	    
                                            		<label class="control-label"><c:out value="${addAvatarText}"/></label>
                                            		<input type="file" name="file" class="filestyle" data-buttontext="<c:out value="${selectFileButton}"/>" data-buttonname="btn-default">
                                            		<span class="help-block"><small><c:out value="${addAvatarForm}"/></small></span>
                                        		</div>
    											<button type="submit" id="change" class="btn btn-primary"><c:out value="${addAvatarText}"/></button>
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
        
        <!-- Sweet-Alert  -->
        <script src="assets/plugins/sweet-alert2/sweetalert2.min.js"></script>
        <script src="assets/pages/jquery.sweet-alert.init.js"></script>

        <!-- App Js -->
        <script src="assets/js/jquery.app.js"></script>
        
        
        <script type="text/javascript">
        	$(document).ready(function () {
        	  	$('#orderForm').parsley();
        	});
        </script>

    </body>
</html>