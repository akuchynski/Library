package by.htp.library.controller.command;

import by.htp.library.util.UserTypeEnum;

public enum CommandEnum {
	
	LOGIN (UserTypeEnum.ALL),
    REGISTRATION(UserTypeEnum.ALL),
    LOGOUT (UserTypeEnum.ALL),
    AJAX_LOGIN_CHECK (UserTypeEnum.ALL),
    AJAX_ID_CHECK (UserTypeEnum.ALL),
    AJAX_BOOK_FIND (UserTypeEnum.ALL),
    CHANGE_LANGUAGE (UserTypeEnum.ALL),
    TO_PAGE (UserTypeEnum.ALL),
    TO_REGISTRATION(UserTypeEnum.ALL),
    TO_BOOK_EDIT (UserTypeEnum.ADMIN),
    TO_ORDER_EDIT (UserTypeEnum.ADMIN),
    TO_USER_EDIT (UserTypeEnum.ADMIN),
    SHOW_DASHBOARD(UserTypeEnum.ALL),
    SHOW_BOOKS(UserTypeEnum.ALL),
	SHOW_ORDERS(UserTypeEnum.ALL),
    SHOW_USERS(UserTypeEnum.ALL),
    SHOW_REPORT(UserTypeEnum.ADMIN),
	SHOW_EMPLOYEES(UserTypeEnum.ALL),
	ADD_BOOK(UserTypeEnum.ADMIN),
	ADD_ORDER(UserTypeEnum.ALL),
	ADD_USER(UserTypeEnum.ADMIN),
	EDIT_BOOK(UserTypeEnum.ADMIN),
	EDIT_ORDER(UserTypeEnum.ADMIN),
	EDIT_USER(UserTypeEnum.ALL),
	DELETE_BOOK(UserTypeEnum.ADMIN),
	DELETE_ORDER(UserTypeEnum.ADMIN),
	DELETE_USER(UserTypeEnum.ADMIN),
	UNKNOWN_COMMAND(UserTypeEnum.ALL);
	
	private UserTypeEnum userType;

	private CommandEnum(UserTypeEnum userType) {
		this.userType = userType;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

}
