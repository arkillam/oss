package oss.enums;

public enum Tile {

	DIRT("dirt.png"),
	GRASS("grass.png"),
	MUD("mud.png"),
	WALL("wall.png");

	/** names of tile images, all of which are found in resources/tiles */
	private String imageName;

	private Tile(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

}
