package plugin.nogtail.displays;

import org.bukkit.World;

public class Region {
	private Direction direction;
	private World world;
	private Vector pos1;
	private Vector pos2;

	public Region(World world, Vector pos1, Vector pos2) {
		this.world = world;
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public int getArea() {
		int width = getWidth();
		int height = getHeight();
		int legnth = getLegnth();

		return width * height * legnth;
	}

	public int getWidth() {
		Vector min = getMinimumPoint();
		Vector max = getMaximumPoint();

		return max.getX() - min.getX() + 1;
	}

	public int getHeight() {
		Vector min = getMinimumPoint();
		Vector max = getMaximumPoint();

		return max.getY() - min.getY() + 1;
	}

	public int getLegnth() {
		Vector min = getMinimumPoint();
		Vector max = getMaximumPoint();

		return max.getZ() - min.getZ() + 1;
	}

	public Direction getDirection() {
		return direction;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Vector getPos1() {
		return pos1;
	}

	public void setPos1(Vector pos1) {
		this.pos1 = pos1;
	}

	public Vector getPos2() {
		return pos2;
	}

	public void setPos2(Vector pos2) {
		this.pos2 = pos2;
	}

	public Vector getMinimumPoint() {
		return new Vector(Math.min(pos1.getX(), pos2.getX()), Math.min(
				pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
	}

	public Vector getMaximumPoint() {
		return new Vector(Math.max(pos1.getX(), pos2.getX()), Math.max(
				pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));
	}

	enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
}