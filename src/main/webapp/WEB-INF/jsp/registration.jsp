<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="<c:out value="${not empty sessionScope.local ? sessionScope.local : 'en'}"/>">
    <head>
        <meta charset="utf-8" />
        
        <c:set var="local" value="${not empty local ? local : 'en'}" scope="session" />
		<fmt:setLocale value="${local}" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc}" key="local.appName" var="appName" />
		<fmt:message bundle="${loc}" key="local.regPage" var="regPage" />
		<fmt:message bundle="${loc}" key="local.userLogin" var="userLogin" />
		<fmt:message bundle="${loc}" key="local.userPassword" var="userPassword" />
		<fmt:message bundle="${loc}" key="local.userEmail" var="userEmail" />
		<fmt:message bundle="${loc}" key="local.loginForm" var="loginForm" />
		<fmt:message bundle="${loc}" key="local.passwordForm" var="passwordForm" />
		<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
		<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
		<fmt:message bundle="${loc}" key="local.signInText" var="signInText" />
		<fmt:message bundle="${loc}" key="local.employeeText" var="employeeText" />
		<fmt:message bundle="${loc}" key="local.itemId" var="itemId" />
		<fmt:message bundle="${loc}" key="local.employeeIdForm" var="employeeIdForm" />

        
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><c:out value="${regPage}"/> – <c:out value="${appName}"/></title>

        <link rel="shortcut icon" href="assets/images/favicon.ico">

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

        <!-- HOME -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">

                        <div class="wrapper-page">

                            <div class="m-t-40 card-box">
                            
                               	<ul class="nav navbar-nav navbar-right top-login-local-right pull-right">
									<li class="dropdown top-menu-item-xs">
										<a href="#" data-target="#" class="dropdown-toggle menu-right-item local" data-toggle="dropdown" aria-expanded="false">
											<i class="mdi mdi-earth"></i>
										</a>
										<ul class="dropdown-menu">
											<li><a href="controller?commandName=change_language&local=en">Eng</a></li>
											<li><a href="controller?commandName=change_language&local=ru">Rus</a></li>
										</ul>
									</li>
								</ul>
                            
                                <div class="text-center">
                                    <h2 class="text-uppercase m-t-0 m-b-30">
                                        <a href="index.html" class="text-success">
                                            <span><img src="assets/images/logo.png" alt="" height="30"></span>
                                        </a>
                                    </h2>
                                    <!--<h4 class="text-uppercase font-bold m-b-0">Sign In</h4>-->
                                </div>
                                <div class="account-content">
                                    <form class="form-horizontal" method="POST" action="controller">
                                    	<input type="hidden" name="commandName" value="registration" />

										<div class="form-group m-b-20">
                                            <div class="col-xs-12" >
                                                <label for="login"><c:out value="${userLogin}"/><span class="text-danger">*</span></label>
                                                <input class="form-control" type="text" name="login" id="login" data-parsley-login data-parsley-trigger="change" required placeholder="<c:out value="${loginForm}"/>" autocomplete="off">
                                            </div>
                                        </div>

                                        <div class="form-group m-b-20">
                                            <div class="col-xs-12">
                                                <label for="email"><c:out value="${userEmail}"/><span class="text-danger">*</span></label>
                                                <input class="form-control" type="email" name="email" id="email" data-parsley-trigger="change" required placeholder="john@gmail.com">
                                            </div>
                                        </div>
										
										<div class="form-group m-b-20">
                                            <div class="col-xs-12">
                                                <label for="emplId"><c:out value="${employeeText}"/> <c:out value="${itemId}"/><span class="text-danger">*</span></label>
                                                <input class="form-control" data-parsley-type="number" name="emplId" id="emplId" data-parsley-emplid data-parsley-trigger="change" required placeholder="<c:out value="${employeeIdForm}"/>">
                                            </div>
                                        </div>

                                        <div class="form-group m-b-20">
                                            <div class="col-xs-12">
                                                <label for="password"><c:out value="${userPassword}"/><span class="text-danger">*</span></label>
                                                <input class="form-control" type="password" data-parsley-trigger="change" data-parsley-minlength="4" required name="password" id="password" placeholder="<c:out value="${passwordForm}"/>">
                                            </div>
                                        </div>

                                        <div class="form-group account-btn text-center m-t-10">
                                            <div class="col-xs-12">
                                                <button class="btn btn-lg btn-primary btn-block" type="submit"><c:out value="${signUp}"/></button>
                                            </div>
                                        </div>

                                    </form>

                                    <div class="clearfix"></div>

                                </div>
                            </div>
                            <!-- end card-box-->


                            <div class="row m-t-50">
                                <div class="col-sm-12 text-center">
                                    <p class="text-muted"><c:out value="${signInText}"/> <a href="controller?commandName=to_login" class="text-dark m-l-5"><c:out value="${signIn}"/></a></p>
                                </div>
                            </div>

                        </div>
                        <!-- end wrapper -->

                    </div>
                </div>
            </div>
        </section>
        <!-- END HOME -->


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

        <!-- App Js -->
        <script src="assets/js/jquery.app.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $('.form-horizontal').parsley();
            });
        </script>

    </body>
</html>