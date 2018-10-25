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
                                    <p class="text-muted m-0"><c:out value="${adminText}"/></p>
                                </div>
                            </div>
                            <!--- End User Detail box -->

                            <!-- Left Menu Start -->
                            <ul class="metisMenu nav" id="side-menu">
                                <li><a href="controller?commandName=show_dashboard"><i class="ti-home"></i> <c:out value="${dashboardText}"/> </a></li>
                                
                                <li>
                                    <a href="javascript: void(0);" aria-expanded="true"><i class="ti-package"></i> <c:out value="${ordersText}"/> <span class="fa arrow"></span></a>
                                    <ul class="nav-second-level nav" aria-expanded="true">
                                        <li><a href="controller?commandName=show_orders"><c:out value="${orderListText}"/></a></li>
                                        <li><a href="controller?commandName=to_page&pageName=order-add"><c:out value="${orderAddText}"/></a></li>
                                    </ul>
                                </li>

                                <li>
                                    <a href="javascript: void(0);" aria-expanded="true"><i class="ti-book"></i> <c:out value="${booksText}"/> <span class="fa arrow"></span></a>
                                    <ul class="nav-second-level nav" aria-expanded="true">
                                        <li><a href="controller?commandName=show_books"><c:out value="${bookListText}"/></a></li>
                                        <li><a href="controller?commandName=to_page&pageName=book-add"><c:out value="${bookAddText}"/></a></li>
                                    </ul>
                                </li>


                                <li>
                                    <a href="javascript: void(0);" aria-expanded="true"><i class="ti-user"></i> <c:out value="${usersText}"/> <span class="fa arrow"></span></a>
                                    <ul class="nav-second-level nav" aria-expanded="true">
                                        <li><a href="controller?commandName=show_users"><c:out value="${userListText}"/></a></li>
                                        <li><a href="controller?commandName=to_page&pageName=user-add"><c:out value="${userAddText}"/></a></li>
                                    </ul>
                                </li>

                                <li><a href="controller?commandName=show_report"><i class="ti-files"></i> <c:out value="${reportText}"/> </a></li>
                            </ul>
                        </div>
                    </div><!--Scrollbar wrapper-->
                </aside>