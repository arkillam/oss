package oss.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import oss.beans.OssMap;

public class HelperFunctions {

private static Logger logger = LogManager.getLogger(HelperFunctions.class);

/** the directory game templates are stored in */
private static File templateDirectory;

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

/**
 * Saves a map to disk. May be a running game or a template. Overwrites files without warning.
 * 
 * @param map
 * @param directory
 * @param filename
 * 
 * @return true if successful, false if an error occurred
 */
public static boolean saveMap(OssMap map, File directory, String filename) {
	try {
		if ((filename == null) || (filename.trim().length() == 0))
			throw new Exception(String.format("Invalid filename '%s' (null or too short)", filename));

		Objects.requireNonNull(map);
		Objects.requireNonNull(directory);

		if (!filename.endsWith(MC.SAVEGAME_SUFFIX) && !filename.endsWith(MC.TEMPLATE_SUFFIX))
			throw new Exception(String.format("Invalid filename '%s' (bad suffix)", filename));

		File file = new File(directory, filename);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new BufferedOutputStream(new FileOutputStream(file)),
				map);
		logger.info(String.format("saved " + file.getAbsolutePath()));
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		return false;
	}
	return true;
}

}
