package oss.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.enums.Tile;
import oss.interfaces.OssModel;
import oss.model.TemplateEditorModel;
import oss.view.MapView;

public class TemplateEditorController implements KeyListener {

private static Logger logger = LogManager.getLogger(TemplateEditorController.class);

/** the map view we are working with */
private MapView mapView;

/** the model we are working with */
private OssModel ossModel;

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

public void setMapView(MapView mapView) {
	this.mapView = mapView;
}

public void setOssModel(OssModel ossModel) {
	this.ossModel = ossModel;
}

}
