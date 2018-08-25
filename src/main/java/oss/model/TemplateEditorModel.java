package oss.model;

import oss.beans.OssMap;
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

/** the current map we are working on */
private OssMap map;

/**
 * @return the current map we are working on
 */
public OssMap getMap() {
	return map;
}

/**
 * @param map
 *            a map we are going to work on
 */
public void setMap(OssMap map) {
	this.map = map;
}

}
