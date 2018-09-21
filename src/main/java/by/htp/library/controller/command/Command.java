package by.htp.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.controller.exception.ControllerException;
import by.htp.library.util.MessageManager;

public abstract class Command {

	public static final String ATTR_LAST_COMMAND = "lastCommand";
	public static final String ATTR_LAST_FORWARD = "lastForward";
	public static final String ATTR_LAST_QUERY = "lastQuery";
	public static final String ATTR_LAST_PAGE_NAME = "lastPageName";
	public static final String ATTR_MENU_PATH = "menuPath";
	public static final String ATTR_USER = "currentUser";
	public static final String ATTR_USER_ID = "currentUserId";
	public static final String ATTR_EMPLOYEE = "currentEmployee";
	public static final String ATTR_ROLE = "role";

	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASS = "password";
	public static final String PARAM_NEW_PASS = "new_password";
	public static final String PARAM_OLD_PASS = "old_password";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_COMMAND_NAME = "commandName";
	public static final String PARAM_PAGE_NAME = "pageName";
	public static final String PARAM_TITLE = "title";
	public static final String PARAM_DESCRIPTION = "description";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_SURNAME = "surname";
	public static final String PARAM_AUTHOR = "author";
	public static final String PARAM_YEAR = "year";
	public static final String PARAM_COUNT = "count";
	public static final String PARAM_EMPLOYEE_ID = "emplId";
	public static final String PARAM_BOOK_ID = "bookId";
	public static final String PARAM_DELETE_ID = "deleteId";
	public static final String PARAM_EDIT_ID = "editId";
	public static final String PARAM_DAYS = "days";
	public static final String PARAM_DATE = "date";
	public static final String PARAM_STATUS = "status";
	public static final String PARAM_ACTIVE_RADIO_BTN = "radioActive";
	public static final String PARAM_NUMBER = "num";

	public static final String ATTR_BOOK_COUNT = "bookCount";
	public static final String ATTR_EMPLOYEE_COUNT = "employeeCount";
	public static final String ATTR_USER_COUNT = "userCount";
	public static final String ATTR_ORDER_COUNT = "orderCount";
	public static final String ATTR_EDIT_ID = "editId";
	public static final String ATTR_EDIT_BOOK = "editBook";
	public static final String ATTR_EDIT_ORDER = "editOrder";
	public static final String ATTR_EDIT_USER = "editUser";
	public static final String ATTR_EDIT_EMPLOYEE = "editEmployee";
	public static final String ATTR_BOOK_LIST = "bookList";
	public static final String ATTR_EMPLOYEE_LIST = "employeeList";
	public static final String ATTR_EMPLOYEE_NOT_REG_LIST = "employeeNotRegList";
	public static final String ATTR_ORDER_LIST = "orderList";
	public static final String ATTR_ORDER_LIST_EMPL = "orderListEmpl";
	public static final String ATTR_USER_LIST = "userList";
	public static final String ATTR_BOOK_MAP = "bookMap";
	public static final String ATTR_EMPLOYEE_MAP = "employeeMap";
	public static final String ATTR_USER_MAP = "userMap";
	public static final String ATTR_ORDER_LIST_WAIT = "orderListWait";
	public static final String ATTR_USER_LAST_LIST = "userLastList";
	public static final String ATTR_BOOK_LAST_LIST = "bookLastList";
	public static final String ATTR_ORDER_LAST_LIST = "orderLastList";

	public static final String ATTR_BOOKS_REPORT = "emplBooksReport";
	public static final String ATTR_BOOKS_DELAY_REPORT = "emplBooksDelayReport";
	
	public static final String CONTROLLER_QUERY = "controller?";

	public static final String FORWARD_LOGIN = "forward.login";
	public static final String FORWARD_REGISTRATION = "forward.registration";
	public static final String FORWARD_TO_PAGE = "forward.to.page";
	public static final String FORWARD_ADMIN = "forward.admin";
	public static final String FORWARD_USER = "forward.user";
	public static final String FORWARD_DASHBOARD = "forward.dashboard";
	public static final String FORWARD_ORDER_ADD = "forward.order.add";
	public static final String FORWARD_ORDER_LIST = "forward.order.list";
	public static final String FORWARD_ORDER_EDIT = "forward.order.edit";
	public static final String FORWARD_BOOK_ADD = "forward.book.add";
	public static final String FORWARD_BOOK_LIST = "forward.book.list";
	public static final String FORWARD_BOOK_EDIT = "forward.book.edit";
	public static final String FORWARD_USER_ADD = "forward.user.add";
	public static final String FORWARD_USER_LIST = "forward.user.list";
	public static final String FORWARD_USER_EDIT = "forward.user.edit";
	public static final String FORWARD_REPORT = "forward.report";
	public static final String FORWARD_ERROR = "forward.error";

	public static final String REDIRECT_INDEX = "redirect.index";
	public static final String REDIRECT_DASHBOARD = "redirect.dashboard";
	public static final String REDIRECT_ORDER_ADD = "redirect.order.add";
	public static final String REDIRECT_ORDER_LIST = "redirect.order.list";
	public static final String REDIRECT_ORDER_EDIT = "redirect.order.edit";
	public static final String REDIRECT_BOOK_ADD = "redirect.book.add";
	public static final String REDIRECT_BOOK_LIST = "redirect.book.list";
	public static final String REDIRECT_BOOK_EDIT = "redirect.book.edit";
	public static final String REDIRECT_USER_ADD = "redirect.user.add";
	public static final String REDIRECT_USER_LIST = "redirect.user.list";
	public static final String REDIRECT_USER_EDIT = "redirect.user.edit";
	public static final String REDIRECT_ERROR = "redirect.error";

	public static final String MSG_ILLEGAL_COMMAND = "error.illegal.command";
	public static final String MSG_NOT_SUPPORTED_COMMAND = "not.supported.command";
	public static final String MSG_NOT_ALLOWED = "not.allowed";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	public static final String PAGE_NAME = "&pageName=";
	public static final String JSP = ".jsp";

	private MessageManager messages = new MessageManager();

	public MessageManager getMessages() {
		return messages;
	}

	public void setMessages(MessageManager messages) {
		this.messages = messages;
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;

}
