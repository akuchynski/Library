package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.controller.command.Command;
import by.htp.library.util.ConfigManager;

public class ToErrorPageCommand extends Command{
    private String message;

    public ToErrorPageCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getMessages().addMessage(message);
        getMessages().saveMessages(request);
        request.getRequestDispatcher(ConfigManager.getProperty(FORWARD_ERROR)).forward(request, response);
    }
}
