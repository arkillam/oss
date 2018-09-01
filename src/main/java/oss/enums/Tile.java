package oss.enums;

public enum Tile {

	DIRT("Dirt", "dirt.png"),
	GRASS("Grass", "grass.png"),
	MUD("Mud", "mud.png"),
	WALL("Wall", "wall.png");

	private String displayName;

	/** names of tile images, all of which are found in resources/tiles */
	private String imageName;

	private Tile(String displayName, String imageName) {
		this.displayName = displayName;
		this.imageName = imageName;
	}

	/**
	 * @return the displayName
	 *
	 * @since x
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

}
