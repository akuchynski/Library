                <aside class="sidebar-navigation">
                    <div class="scrollbar-wrapper">
                        <div>
                            <button type="button" class="button-menu-mobile btn-mobile-view visible-xs visible-sm">
                                <i class="mdi mdi-close"></i>
                            </button>
                            <!-- User Detail box -->
                            <div class="user-details">
                                <div class="pull-left">
                                    <img src="assets/images/users/avatar${avatarNumber}.jpg" alt="" class="thumb-md img-circle">
                                </div>
                                <div class="user-info">
                                    <a href="controller?commandName=to_user_edit"><c:out value="${currentEmployee.name}" /> <c:out value="${currentEmployee.surname}" /></a>
                                    <p class="text-muted m-0"><c:out value="${userText}"/></p>
                                </div>
                            </div>
                            <!--- End User Detail box -->

                            <!-- Left Menu Start -->
                            <ul class="metisMenu nav" id="side-menu">
                                <li><a href="controller?commandName=show_dashboard"><i class="ti-home"></i> <c:out value="${dashboardText}"/> </a></li>
                                
                                <li><a href="controller?commandName=show_books"><i class="ti-book"></i> <c:out value="${bookListText}"/> </a></li>
                                
                                <li>
                                    <a href="javascript: void(0);" aria-expanded="true"><i class="ti-package"></i> <c:out value="${orderListText}"/> <span class="fa arrow"></span></a>
                                    <ul class="nav-second-level nav" aria-expanded="true">
                                        <li><a href="controller?commandName=show_orders"><c:out value="${orderListText}"/></a></li>
                                        <li><a href="controller?commandName=to_page&pageName=order-add"><c:out value="${orderAddText}"/></a></li>
                                    </ul>
                                </li>
                                
                                <li><a href="controller?commandName=to_user_edit"><i class="ti-user"></i> <c:out value="${profileText}"/> </a></li>

                            </ul>
                        </div>
                    </div><!--Scrollbar wrapper-->
                </aside>