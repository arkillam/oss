package oss.factories;

import oss.beans.OssMap;
import oss.enums.Tile;

public class MapFactory {

public static OssMap createSimpleMap(String title, int width, int height, Tile baseTile) {
	OssMap map = new OssMap();
	map.setTitle(title);

	Tile[][] tiles = new Tile[width][height];
	for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			tiles[x][y] = baseTile;
		}
	}

	map.setTiles(tiles);

	map.setWidth(width);

	map.setHeight(height);

	return map;
}

}
