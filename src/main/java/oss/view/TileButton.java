package oss.view;

import javax.swing.JButton;

import oss.enums.Tile;

/**
 * A standard JButton in most aspects, but it knows what tile it represents.
 */

public class TileButton extends JButton {

private static final long serialVersionUID = 1L;

private Tile tile;

public Tile getTile() {
	return tile;
}

public void setTile(Tile tile) {
	this.tile = tile;
}

}
