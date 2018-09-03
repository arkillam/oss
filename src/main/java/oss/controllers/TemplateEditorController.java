package oss.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.Oss;
import oss.enums.Tile;
import oss.misc.HelperFunctions;
import oss.misc.MC;
import oss.misc.TemplateFileFilter;
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
private TemplateEditorModel model;

/**
 * A private constructor, to force this to be a singleton.
 */
private TemplateEditorController() {
}

public MapView getMapView() {
	return mapView;
}

public TemplateEditorModel getModel() {
	return model;
}

@Override
public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	// logger.debug("key code: " + keyCode);

	if (KeyEvent.VK_DOWN == keyCode) {
		int[] cursor = model.getCursor();
		cursor[1] += 1;
		if (cursor[1] >= model.getMap().getHeight())
			cursor[1] = 0;
		model.setCursor(cursor);
	}

	else if (KeyEvent.VK_LEFT == keyCode) {
		int[] cursor = model.getCursor();
		cursor[0] -= 1;
		if (cursor[0] < 0)
			cursor[0] = model.getMap().getWidth() - 1;
		model.setCursor(cursor);
	}

	else if (KeyEvent.VK_RIGHT == keyCode) {
		int[] cursor = model.getCursor();
		cursor[0] += 1;
		if (cursor[0] >= model.getMap().getWidth())
			cursor[0] = 0;
		model.setCursor(cursor);
	}

	else if (KeyEvent.VK_T == keyCode) {
		Tile tile = ((TemplateEditorModel) model).getSelectedTile();
		if (tile != null) {
			int[] cursor = model.getCursor();
			model.getMap().setTile(cursor[0], cursor[1], tile);
		}
	}

	if (KeyEvent.VK_UP == keyCode) {
		int[] cursor = model.getCursor();
		cursor[1] -= 1;
		if (cursor[1] < 0)
			cursor[1] = model.getMap().getHeight() - 1;
		model.setCursor(cursor);
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
	// anything to save?
	if (model.getMap() == null) {
		JOptionPane.showMessageDialog(Oss.handle, "No template to save!", "Error", JOptionPane.ERROR_MESSAGE);
		return;
	}

	// do we already know the file name we are saving to?
	if ((model.getTemplateFilename() == null) || (model.getTemplateFilename().trim().length() == 0)) {
		saveCurrentTemplateAs();
		return;
	}

	boolean rc = HelperFunctions.saveMap(model.getMap(), HelperFunctions.getTemplateDirectory(),
			model.getTemplateFilename());
	if (rc) {
		JOptionPane.showMessageDialog(Oss.handle, "Template saved.", "Success", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(Oss.handle, "Template save failed.  See logs for details.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}

/**
 * Saves the current map in the editor as a template. Always requests a new name for the file.
 */
public void saveCurrentTemplateAs() {
	// anything to save?
	if (model.getMap() == null) {
		JOptionPane.showMessageDialog(Oss.handle, "No template to save!", "Error", JOptionPane.ERROR_MESSAGE);
		return;
	}

	JFileChooser fc = new JFileChooser(HelperFunctions.getTemplateDirectory());
	fc.setDialogTitle("Save Template As");
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fc.setDialogType(JFileChooser.SAVE_DIALOG);
	fc.setFileFilter(new TemplateFileFilter());
	fc.showDialog(Oss.handle, "Save As");

	File sf = fc.getSelectedFile();
	if (sf == null)
		return;

	if (sf.exists()) {
		int rc = JOptionPane.showConfirmDialog(Oss.handle,
				String.format("Are you sure you want to overwrite '%s'?", sf.getName()), "Are you sure?",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (rc == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(Oss.handle, String.format("'%s' not saved", sf.getName()),
					"Template not saved", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	String fn = sf.getName();
	if (!fn.endsWith(MC.TEMPLATE_SUFFIX)) {
		if (fn.endsWith("."))
			fn = fn + MC.TEMPLATE_SUFFIX;
		else
			fn = fn + "." + MC.TEMPLATE_SUFFIX;
	}
	model.setTemplateFilename(fn);

	// having established the filename, run the "save" logic
	saveCurrentTemplate();
}

public void setMapView(MapView mapView) {
	this.mapView = mapView;
}

public void setModel(TemplateEditorModel model) {
	this.model = model;
}

}
