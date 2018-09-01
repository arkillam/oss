package oss.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import oss.enums.Tile;
import oss.factories.ImageManager;
import oss.model.TemplateEditorModel;

public class TemplateEditorView extends JPanel {

private static final long serialVersionUID = 1L;

private TemplateEditorModel model;

/** displays the map */
private MapView mapView;

public TemplateEditorView() {
	super();

	setLayout(new BorderLayout());

	// tile-selecting buttons go on the left
	JPanel tileButtonsPanel = new JPanel(new GridLayout(Tile.values().length, 1));
	for (Tile tile : Tile.values()) {
		JButton b = new JButton();
		Image image = ImageManager.getImage(tile);
		b.setIcon(new ImageIcon(image));
		b.setToolTipText(tile.getDisplayName());
		b.addActionListener(e -> {
			// TOOD: set selected tile in model
		});
		tileButtonsPanel.add(b);
	}
	add(tileButtonsPanel, BorderLayout.WEST);

	// map-displaying panel
	// TODO: add key listener
	mapView = new MapView();
	mapView.setOssModel(null);
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
}

}
