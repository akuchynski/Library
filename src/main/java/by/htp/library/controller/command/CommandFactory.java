package by.htp.library.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.htp.library.controller.command.action.AddBookCommand;
import by.htp.library.controller.command.action.AddOrderCommand;
import by.htp.library.controller.command.action.AddUserCommand;
import by.htp.library.controller.command.action.ChangeLanguageCommand;
import by.htp.library.controller.command.action.DeleteBookCommand;
import by.htp.library.controller.command.action.DeleteOrderCommand;
import by.htp.library.controller.command.action.DeleteUserCommand;
import by.htp.library.controller.command.action.EditBookCommand;
import by.htp.library.controller.command.action.EditOrderCommand;
import by.htp.library.controller.command.action.EditUserCommand;
import by.htp.library.controller.command.ajax.AjaxBookFindCommand;
import by.htp.library.controller.command.ajax.AjaxIdCheckCommand;
import by.htp.library.controller.command.ajax.AjaxLoginCheckCommand;
import by.htp.library.controller.command.authorization.LoginCommand;
import by.htp.library.controller.command.authorization.LogoutCommand;
import by.htp.library.controller.command.authorization.RegistrationCommand;
import by.htp.library.controller.command.navigation.ToBookListPageCommand;
import by.htp.library.controller.command.navigation.ToDashboardPageCommand;
import by.htp.library.controller.command.navigation.ToErrorPageCommand;
import by.htp.library.controller.command.navigation.ToBookEditPageCommand;
import by.htp.library.controller.command.navigation.ToOrderEditPageCommand;
import by.htp.library.controller.command.navigation.ToOrderListPageCommand;
import by.htp.library.controller.command.navigation.ToPageCommand;
import by.htp.library.controller.command.navigation.ToRegistrationPageCommand;
import by.htp.library.controller.command.navigation.ToReportPageCommand;
import by.htp.library.controller.command.navigation.ToUserEditPageCommand;
import by.htp.library.controller.command.navigation.ToUserListPageCommand;
import by.htp.library.util.ConfigManager;
import by.htp.library.util.Permission;


public class CommandFactory {

	public static final String PARAM_COMMAND = "commandName";
	public static final String MSG_ILLEGAL_COMMAND = "error.illegal.command";
	public static final String MSG_NOT_SUPPORTED_COMMAND = "error.not.supported.command";
	public static final String MSG_NOT_ALLOWED = "error.not.allowed";

	public static Command getCommand(HttpServletRequest request) {
		
		Command command = null;
		CommandEnum commandType = getCommandEnum(request.getParameter(PARAM_COMMAND));
		
		if (Permission.checkPermission(commandType, request)) {
			switch (commandType) {
			case LOGIN:
				command = new LoginCommand();
				break;
			case REGISTRATION:
				command = new RegistrationCommand();
				break;
			case LOGOUT:
				command = new LogoutCommand();
				break;
			case AJAX_LOGIN_CHECK:
				command = new AjaxLoginCheckCommand();
				break;
			case AJAX_ID_CHECK:
				command = new AjaxIdCheckCommand();
				break;
			case AJAX_BOOK_FIND:
				command = new AjaxBookFindCommand();
				break;	
			case CHANGE_LANGUAGE:
				command = new ChangeLanguageCommand();
				break;
			case TO_PAGE:
				command = new ToPageCommand();
				break;
			case TO_REGISTRATION:
				command = new ToRegistrationPageCommand();
				break;
			case TO_BOOK_EDIT:
				command = new ToBookEditPageCommand();
				break;
			case TO_ORDER_EDIT:
				command = new ToOrderEditPageCommand();
				break;
			case TO_USER_EDIT:
				command = new ToUserEditPageCommand();
				break;
			case ADD_BOOK:
				command = new AddBookCommand();
				break;
			case ADD_ORDER:
				command = new AddOrderCommand();
				break;
			case ADD_USER:
				command = new AddUserCommand();
				break;
			case SHOW_DASHBOARD:
				command = new ToDashboardPageCommand();
				break;
			case SHOW_BOOKS:
				command = new ToBookListPageCommand();
				break;
			case SHOW_USERS:
				command = new ToUserListPageCommand();
				break;
			case SHOW_ORDERS:
				command = new ToOrderListPageCommand();
				break;
			case SHOW_REPORT:
				command = new ToReportPageCommand();
				break;	
			case EDIT_BOOK:
				command = new EditBookCommand();
				break;
			case EDIT_ORDER:
				command = new EditOrderCommand();
				break;
			case EDIT_USER:
				command = new EditUserCommand();
				break;
			case DELETE_BOOK:
				command = new DeleteBookCommand();
				break;
			case DELETE_ORDER:
				command = new DeleteOrderCommand();
				break;
			case DELETE_USER:
				command = new DeleteUserCommand();
				break;
			case UNKNOWN_COMMAND:
				command = new ToErrorPageCommand(ConfigManager.getProperty(MSG_ILLEGAL_COMMAND));
				break;
			default:
				command = new ToErrorPageCommand(ConfigManager.getProperty(MSG_NOT_SUPPORTED_COMMAND));
				break;
			}

		} else {
			command = new ToErrorPageCommand(ConfigManager.getProperty(MSG_NOT_ALLOWED));
		}
		return command;
	}

	private static CommandEnum getCommandEnum(String commandName) {
		CommandEnum commandEnum = null;
		try {
			commandEnum = CommandEnum.valueOf(commandName.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandEnum = CommandEnum.UNKNOWN_COMMAND;
		} catch (NullPointerException e) {
			commandEnum = CommandEnum.UNKNOWN_COMMAND;
		}
		return commandEnum;
	}

}
