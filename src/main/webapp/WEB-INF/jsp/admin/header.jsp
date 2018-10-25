            <div class="topbar">

                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="">
                        <a href="controller?commandName=show_dashboard" class="logo">
                            <img src="assets/images/logo.png" alt="logo" class="logo-lg" />
                            <img src="assets/images/logo_sm.png" alt="logo" class="logo-sm hidden" />
                        </a>
                    </div>
                </div>

                <!-- Top navbar -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="">

                            <!-- Mobile menu button -->
                            <div class="pull-left">
                                <button type="button" class="button-menu-mobile visible-xs visible-sm">
                                    <i class="fa fa-bars"></i>
                                </button>
                                <span class="clearfix"></span>
                            </div>

                            <!-- Top nav Right menu -->
                            <ul class="nav navbar-nav navbar-right top-navbar-items-right pull-right">
                            
                            	<li class="dropdown top-menu-item-xs">
								    <a href="#" data-target="#" class="dropdown-toggle menu-right-item local" data-toggle="dropdown" aria-expanded="true">
                                    <i class="mdi mdi-earth"></i>
                                    </a><ul class="dropdown-menu">
										<li><a href="controller?commandName=change_language&local=en">Eng</a></li>
                                        <li><a href="controller?commandName=change_language&local=ru">Rus</a></li>
                                    </ul>
                                </li>
                            
                                <li class="hidden-xs">
                                    <form role="search" class="navbar-left app-search pull-left">
                                         <input type="text" placeholder="<c:out value="${searchBookText}"/>" class="form-control" id="search-header" autocomplete="off">
                                         <a href=""><i class="fa fa-search"></i></a>
                                         <div class="search-results hidden"></div>
                                    </form>
                                </li>

                                <li class="dropdown top-menu-item-xs">
                                    <a href="#" class="dropdown-toggle menu-right-item profile" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar${avatarNumber}.jpg" alt="user-img" class="img-circle"> </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="controller?commandName=to_user_edit"><i class="ti-user m-r-10"></i> <c:out value="${profileText}"/></a></li>
                                        <li class="divider"></li>
                                        <li><a href="controller?commandName=logout"><i class="ti-power-off m-r-10"></i> <c:out value="${logoutText}"/></a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div> <!-- end container -->
                </div> <!-- end navbar -->
            </div>