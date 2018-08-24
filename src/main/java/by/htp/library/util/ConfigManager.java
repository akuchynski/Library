package by.htp.library.util;

import java.util.ResourceBundle;

public class ConfigManager {

	public static final String RESOURCE_PROPERTY = "config";

	private static ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PROPERTY);

	public static String getProperty(String key) {
		return resource.getString(key);
	}
}
