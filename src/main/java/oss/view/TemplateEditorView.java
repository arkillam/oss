package oss.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import oss.controllers.TemplateEditorController;
import oss.enums.Tile;
import oss.factories.ImageManager;
import oss.model.TemplateEditorModel;

public class TemplateEditorView extends JPanel {

private static final long serialVersionUID = 1L;

private TemplateEditorModel model;

/** displays the map */
private MapView mapView;

/** handles keyboard input from the mapView */
private TemplateEditorController templateEditorController;

public TemplateEditorView() {
	super();

	setLayout(new BorderLayout());

	// tile-selecting buttons go on the left
	JPanel tileButtonsPanel = new JPanel(new GridLayout(Tile.values().length, 1));
	for (Tile tile : Tile.values()) {
		TileButton b = new TileButton();
		b.setTile(tile);
		Image image = ImageManager.getImage(tile);
		b.setIcon(new ImageIcon(image));
		b.setToolTipText(tile.getDisplayName());
		b.addActionListener(e -> {
			model.setSelectedTile(b.getTile());
			mapView.requestFocusInWindow();
		});
		tileButtonsPanel.add(b);
	}
	add(tileButtonsPanel, BorderLayout.WEST);

	// map-displaying panel
	mapView = new MapView();
	mapView.setOssModel(null);
	templateEditorController = new TemplateEditorController();
	templateEditorController.setMapView(mapView);
	mapView.addKeyListener(templateEditorController);
	add(mapView, BorderLayout.CENTER);
}

/**
 * @return the model
 *
 * @since x
 */
public TemplateEditorModel getModel() {
	return model;
}

/**
 * @param model
 *            the model to set
 *
 * @since x
 */
public void setModel(TemplateEditorModel model) {
	this.model = model;
	this.mapView.setOssModel(model);
	this.templateEditorController.setOssModel(model);
}

}
