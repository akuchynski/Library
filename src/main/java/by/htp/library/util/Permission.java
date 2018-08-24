package by.htp.library.util;

import javax.servlet.http.HttpServletRequest;

import by.htp.library.bean.User;
import by.htp.library.controller.command.CommandEnum;

public class Permission {

    public static final String PARAM_USER = "currentUser";
    public static final String ROLE_ADMIN = "ADMIN";
   
    public static boolean checkPermission(CommandEnum commandType, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(PARAM_USER);
        switch (commandType.getUserType()) {
            case ADMIN:
                if (user != null) {
                	if(isAdmin(user)) {
                        return true;
                    }
                }
                break;
            case USER:
                if (user != null) {
                	if(!isAdmin(user)) {
                         return true;
                    }
                }
                break;
            case ALL:
                return true;
        }
        return false;
    }
    
    public static boolean isAdmin(User user) {
    	if(user.getRole().equals(ROLE_ADMIN)){
    		return true;
    	}
		return false;
    }
}
