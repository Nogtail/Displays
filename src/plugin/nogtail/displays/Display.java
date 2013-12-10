package plugin.nogtail.displays;

import org.bukkit.Location;
import org.bukkit.World;

public class Display {

	public Region region;
	private int id;

	public Display(Region region) {
		this(region, 10); // TODO generate random ID
	}

	public Display(Region region, int id) {
		this.region = region;
		this.setId(id);
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int createDisplay() {
		Vector min = region.getMinimumPoint();
		Vector max = region.getMaximumPoint();
		World world = region.getWorld();
		int amount = 0;

		int minX = min.getX();
		int minY = min.getY();
		int minZ = min.getZ();
		int maxX = max.getX();
		int maxY = max.getY();
		int maxZ = max.getZ();

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					amount++;

					Location location = new Location(world, x, y, z);
				}
			}
		}
		return amount;
	}
}