package oss.factories;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.enums.Tile;

/**
 * <P>
 * The game's image library manager. Originally written for oscg, refactored for use in oss.
 * 
 * <UL>
 * <LI>Version 1.0 - 11/19/2010 - the original class
 * <LI>Version 1.1 - 05/28/2012 - added method to allow loading custom images to replace the standard set
 * <LI>Version 1.2 - 12/05/2012 - moved TileColourFilter into this class (it is not referenced anywhere else)
 * <LI>Version 2.0 - 09/01/2018 - copied and refactored for use in oss
 * </UL>
 * 
 * @version 2.0 - 09/091/2018
 */

public class ImageManager {

/** the game's image cache (public so that maps can use it) */
private static Map<Object, Image> images = new HashMap<>();

private static Logger logger = LogManager.getLogger(ImageManager.class);

/** the length of the side of a map square image, in pixels */
public static final int SQUARE_SIZE = 32;

/**
 * @param tile
 *            a tile we want the image for
 * 
 * @return the tile's image
 */
public static Image getImage(Tile tile) {
	if (!images.containsKey(tile)) {
		Image image = loadImage("/tiles/" + tile.getImageName(), false);
		if (image != null)
			images.put(tile, image);
	}
	return images.get(tile);
}

/**
 * Loads the specified image, optionally makes its background transparent, and returns it.
 * 
 * @param path
 *            the full path to the resource
 * @param transparentBackground
 *            if true, the image's background is made transparent
 * 
 * @return the image, or null if it was not found
 * 
 * @since 1.0
 */

private static Image loadImage(String name, boolean transparentBackground) {
	try {
		URL url = ImageManager.class.getResource(name);
		Objects.requireNonNull(url);

		BufferedImage image = ImageIO.read(url);
		logger.debug(String.format("loaded image %s", name));

		if (transparentBackground) {
			int color = image.getRGB(image.getWidth() - 1, 0);
			image = makeColorTransparent(image, new Color(color));
		}

		return image;
	} catch (Exception e) {
		logger.error(
				String.format("ImageManager.loadImage (%s, %b) failed; returning null", name, transparentBackground));
		logger.error(e.getMessage(), e);
		return null;
	}
}

/**
 * Makes a specified color transparent in the submitted image.
 * 
 * @param image
 *            the image to modify
 * @param color
 *            the colour to make transparent
 * 
 * @return the image, with a transparent colour
 *
 * @since 1.0
 */

private static BufferedImage makeColorTransparent(BufferedImage image, final Color color) {
	ImageFilter filter = new RGBImageFilter() {
	// the color we are looking for... Alpha bits are set to opaque
	public int markerRGB = color.getRGB() | 0xFF000000;

	public final int filterRGB(int x, int y, int rgb) {
		if ((rgb | 0xFF000000) == markerRGB) {
			// Mark the alpha bits as zero - transparent
			return 0x00FFFFFF & rgb;
		} else {
			// nothing to do
			return rgb;
		}
	}
	};

	ImageProducer ip = new FilteredImageSource(image.getSource(), filter);

	Image tmp = Toolkit.getDefaultToolkit().createImage(ip);

	// only create a new image if necessary
	if (image.getType() == BufferedImage.TYPE_INT_ARGB) {
		image.createGraphics().drawImage(tmp, 0, 0, null);
		return image;
	} else {
		BufferedImage i = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		i.createGraphics().drawImage(tmp, 0, 0, null);
		return i;
	}
}

}
