package oss.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import oss.enums.Tile;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OssMap {

@JsonProperty
private Integer height;

@JsonProperty
private Tile tiles[][];

@JsonProperty
private String title;

@JsonProperty
private Integer width;

public OssMap()
{
	width = null;
	height = null;
	tiles = null;
}

/**
 * @return the height
 *
 * @since x
 */
public Integer getHeight() {
	return height;
}

@JsonIgnore
public Tile getTile(int x, int y)
{
	return tiles[x][y];
}

/**
 * @return the tiles
 *
 * @since x
 */
public Tile[][] getTiles() {
	return tiles;
}

/**
 * @return the title
 *
 * @since x
 */
public String getTitle() {
	return title;
}

/**
 * @return the width
 *
 * @since x
 */
public Integer getWidth() {
	return width;
}

/**
 * @param height the height to set
 *
 * @since x
 */
public void setHeight(Integer height) {
	this.height = height;
}

@JsonIgnore
public void setTile(int x, int y, Tile t)
{
	tiles[x][y] = t;;
}

/**
 * @param tiles the tiles to set
 *
 * @since x
 */
public void setTiles(Tile[][] tiles) {
	this.tiles = tiles;
}

/**
 * @param title the title to set
 *
 * @since x
 */
public void setTitle(String title) {
	this.title = title;
}

/**
 * @param width the width to set
 *
 * @since x
 */
public void setWidth(Integer width) {
	this.width = width;
}

}
