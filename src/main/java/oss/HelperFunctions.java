package oss;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelperFunctions {

/** the directory game templates are stored in */
private static File templateDirectory;

private static Logger logger = LogManager.getLogger(HelperFunctions.class);

/**
 * @return the directory game templates are stored in
 */
public static File getTemplateDirectory() {
	if (templateDirectory == null) {
		templateDirectory = new File(
				System.getProperty("user.home") + File.separator + "oss" + File.separator + "templates");
		if (!templateDirectory.exists()) {
			templateDirectory.mkdirs();
			logger.info(String.format("created template directory '%s'", templateDirectory.getAbsolutePath()));
		}
	}
	return templateDirectory;
}

}
