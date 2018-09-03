package oss.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import oss.controllers.TemplateEditorController;
import oss.enums.Tile;
import oss.factories.ImageManager;
import oss.misc.TileButton;
import oss.model.TemplateEditorModel;

public class TemplateEditorView extends JPanel {

private static final long serialVersionUID = 1L;

/** displays the map */
private MapView mapView;

private TemplateEditorModel model;

/** displays the map's description */
private JTextArea ta_mapDescription;

/** displays the map's author */
private JTextField tf_mapAuthor;

/** displays the map's title */
private JTextField tf_mapTitle;

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

	// top panel display
	JPanel topPanel = new JPanel(new FlowLayout());
	topPanel.add(new JLabel("Title"));
	tf_mapTitle = new JTextField(50);
	topPanel.add(tf_mapTitle);
	topPanel.add(new JLabel("Author"));
	tf_mapAuthor = new JTextField(30);
	topPanel.add(tf_mapAuthor);
	tf_mapAuthor.setText(System.getProperty("user.name"));
	add(topPanel, BorderLayout.NORTH);

	// map-displaying panel
	mapView = new MapView();
	mapView.setOssModel(null);
	TemplateEditorController.getInstance().setMapView(mapView);
	mapView.addKeyListener(TemplateEditorController.getInstance());
	add(mapView, BorderLayout.CENTER);

	// bottom panel
	JPanel bottomPanel = new JPanel(new BorderLayout());
	bottomPanel.add(new JLabel("Description"), BorderLayout.WEST);
	ta_mapDescription = new JTextArea(4, 50);
	bottomPanel.add(ta_mapDescription, BorderLayout.CENTER);
	add(bottomPanel, BorderLayout.SOUTH);

}

public String getMapAuthor() {
	return tf_mapAuthor.getText();
}

public String getMapDescription() {
	return ta_mapDescription.getText();
}

public String getMapTitle() {
	return tf_mapTitle.getText();
}

/**
 * @return the model
 *
 * @since x
 */
public TemplateEditorModel getModel() {
	return model;
}

public void setMapAuthor(String mapAuthor) {
	tf_mapAuthor.setText(mapAuthor);
}

public void setMapDescription(String mapDescription) {
	ta_mapDescription.setText(mapDescription);
}

public void setMapTitle(String mapTitle) {
	tf_mapTitle.setText(mapTitle);
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
	TemplateEditorController.getInstance().setModel(model);
}

}
