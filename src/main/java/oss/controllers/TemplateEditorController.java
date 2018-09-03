package oss.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.Oss;
import oss.enums.Tile;
import oss.interfaces.OssModel;
import oss.model.TemplateEditorModel;
import oss.view.MapView;

/**
 * The controller for the template editing process. A singleton class.
 */

public class TemplateEditorController implements KeyListener {

private static Logger logger = LogManager.getLogger(TemplateEditorController.class);

/** the single instance of the template editor controller */
private static TemplateEditorController singleton;

/**
 * The factory method for this singleton class.
 * 
 * @return the controller instance
 */
public static TemplateEditorController getInstance() {
	if (singleton == null)
		singleton = new TemplateEditorController();
	return singleton;
}

/** the map view we are working with */
private MapView mapView;

/** the model we are working with */
private OssModel ossModel;

/**
 * A private constructor, to force this to be a singleton.
 */
private TemplateEditorController() {
}

public MapView getMapView() {
	return mapView;
}

public OssModel getOssModel() {
	return ossModel;
}

@Override
public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	// logger.debug("key code: " + keyCode);

	if (KeyEvent.VK_DOWN == keyCode) {
		int[] cursor = ossModel.getCursor();
		cursor[1] += 1;
		if (cursor[1] >= ossModel.getMap().getHeight())
			cursor[1] = 0;
		ossModel.setCursor(cursor);
	}

	else if (KeyEvent.VK_LEFT == keyCode) {
		int[] cursor = ossModel.getCursor();
		cursor[0] -= 1;
		if (cursor[0] < 0)
			cursor[0] = ossModel.getMap().getWidth() - 1;
		ossModel.setCursor(cursor);
	}

	else if (KeyEvent.VK_RIGHT == keyCode) {
		int[] cursor = ossModel.getCursor();
		cursor[0] += 1;
		if (cursor[0] >= ossModel.getMap().getWidth())
			cursor[0] = 0;
		ossModel.setCursor(cursor);
	}

	else if (KeyEvent.VK_T == keyCode) {
		Tile tile = ((TemplateEditorModel) ossModel).getSelectedTile();
		if (tile != null) {
			int[] cursor = ossModel.getCursor();
			ossModel.getMap().setTile(cursor[0], cursor[1], tile);
		}
	}

	if (KeyEvent.VK_UP == keyCode) {
		int[] cursor = ossModel.getCursor();
		cursor[1] -= 1;
		if (cursor[1] < 0)
			cursor[1] = ossModel.getMap().getHeight() - 1;
		ossModel.setCursor(cursor);
	}

	mapView.repaint();
}

@Override
public void keyReleased(KeyEvent e) {
}

@Override
public void keyTyped(KeyEvent e) {
}

/**
 * Creates a fresh map in the editor.
 */
public void newTemplate() {
	// TODO: detect unsaved work in previous, ask if user wants to save
}

/**
 * Saves the current map in the editor as a template. Only requests a new name if one has not already been provided.
 */
public void saveCurrentTemplate() {
	if (ossModel.getMap() == null) {
		JOptionPane.showMessageDialog(Oss.handle, "No template to save!", "Error", JOptionPane.ERROR_MESSAGE);
		return;
	}
}

/**
 * Saves the current map in the editor as a template. Always requests a new name for the file.
 */
public void saveCurrentTemplateAs() {
	if (ossModel.getMap() == null) {
		JOptionPane.showMessageDialog(Oss.handle, "No template to save!", "Error", JOptionPane.ERROR_MESSAGE);
		return;
	}
}

public void setMapView(MapView mapView) {
	this.mapView = mapView;
}

public void setOssModel(OssModel ossModel) {
	this.ossModel = ossModel;
}

}
