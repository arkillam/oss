package oss.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.beans.OssMap;
import oss.enums.Tile;
import oss.interfaces.OssModel;

/**
 * <p>
 * The template editor consists of a panel for displayed work-in-progress templates (one at at time), a controller for
 * that panel, and this singleton class representing the model.
 * <p>
 * OSS uses the same data for games in progress as for new games that have not yet started. The "not started" ones are
 * called templates.
 */

public class TemplateEditorModel implements OssModel {

private static Logger logger = LogManager.getLogger(TemplateEditorModel.class);

private int[] cursor = new int[] { 0, 0 };

/** the current map we are working on */
private OssMap map;

/** the selected tile to set in squares */
private Tile selectedTile;

/** the current filename of the template (used by "save" to avoid repeatedly asking for the filename) */
private String templateFilename;

@Override
public int[] getCursor() {
	return cursor;
}

/**
 * @return the current map we are working on
 */
public OssMap getMap() {
	return map;
}

public Tile getSelectedTile() {
	return selectedTile;
}

public String getTemplateFilename() {
	return templateFilename;
}

@Override
public void setCursor(int[] cursor) {
	if ((cursor != null) && (cursor.length == 2)) {
		this.cursor[0] = cursor[0];
		this.cursor[1] = cursor[1];
	} else {
		logger.error("ignoring bad cursor:  " + cursor);
	}
}

/**
 * @param map
 *            a map we are going to work on
 */
public void setMap(OssMap map) {
	this.map = map;
	this.templateFilename = null;
}

public void setSelectedTile(Tile selectedTile) {
	this.selectedTile = selectedTile;
}

public void setTemplateFilename(String templateFilename) {
	this.templateFilename = templateFilename;
	logger.debug("templateFilename set to " + templateFilename);
}

}
