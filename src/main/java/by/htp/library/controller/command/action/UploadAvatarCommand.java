package by.htp.library.controller.command.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.util.ConfigManager;

public class UploadAvatarCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(UploadAvatarCommand.class);
	private static final String SAVE_DIR = "assets\\images\\users";
	private static final String FILE_TYPE = "jpg";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + SAVE_DIR + File.separator;

			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter(ATTR_EDIT_ID));

			for (Part part : request.getParts()) {
				String filePath = extractFileName(part);
				String fileName = new File(filePath).getName();
				String fileType = getFileExtension(fileName);
				if (fileType.equals(FILE_TYPE) == false) {
					session.setAttribute("messageClass", "upload-file-error");
					break;
				}
				part.write(savePath + "avatar" + id + "." + fileType);
				request.getSession().setAttribute("avatarNumber", id);
				session.setAttribute("messageClass", "upload-file-success");
			}
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_DASHBOARD));
		} catch (ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}
	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	private String getFileExtension(String name) {
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}
}