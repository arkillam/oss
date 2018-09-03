package oss.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import oss.factories.ImageManager;
import oss.interfaces.OssModel;

/**
 * Displays a map, and passes keystrokes to a controller.
 */

public class MapView extends JPanel {

private static final long serialVersionUID = 1L;

/** graphics object for buffer */
private Graphics2D bufferedGraphics2D;

/** used for double-buffering the display */
private BufferedImage bufferedImage;

/** the colour of the cursor (currently transparent white) */
private Color cursorColour = new Color(255, 255, 255, 100);

/** contains the map we are displaying */
private OssModel ossModel;

public MapView() {
	super();
	setFocusable(true);
}

/**
 * @return the ossModel
 */
public OssModel getOssModel() {
	return ossModel;
}

@Override
protected void paintComponent(Graphics g) {

	// re-create buffer if needed
	if ((bufferedImage == null) || (bufferedImage.getWidth() != getWidth())
			|| (bufferedImage.getHeight() != getHeight())) {
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		bufferedGraphics2D = (Graphics2D) bufferedImage.getGraphics();
	}

	// black background
	bufferedGraphics2D.setColor(Color.BLACK);
	bufferedGraphics2D.fillRect(0, 0, getWidth(), getHeight());

	if (ossModel.getMap() != null) {
		for (int x = 0; x < ossModel.getMap().getWidth(); x++) {
			for (int y = 0; y < ossModel.getMap().getHeight(); y++) {
				Image image = ImageManager.getImage(ossModel.getMap().getTile(x, y));
				bufferedGraphics2D.drawImage(image, x * ImageManager.SQUARE_SIZE, y * ImageManager.SQUARE_SIZE, this);
			}
		}

		// draw the cursor
		bufferedGraphics2D.setColor(cursorColour);
		bufferedGraphics2D.fillRect(ossModel.getCursor()[0] * ImageManager.SQUARE_SIZE,
				ossModel.getCursor()[1] * ImageManager.SQUARE_SIZE, ImageManager.SQUARE_SIZE, ImageManager.SQUARE_SIZE);
	}

	g.drawImage(bufferedImage, 0, 0, this);
}

/**
 * @param ossModel
 *            the ossModel to set
 */
public void setOssModel(OssModel ossModel) {
	this.ossModel = ossModel;
}

}
