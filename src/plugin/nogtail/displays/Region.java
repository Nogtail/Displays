package plugin.nogtail.displays;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class Region {
	private BlockFace direction;
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

	public BlockFace getDirection() {
		return direction;
	}

	public void setDirection(BlockFace direction) {
		this.direction = direction;
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
		return new Vector(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
	}

	public Vector getMaximumPoint() {
		return new Vector(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));
	}

	public boolean is2D() {
		if (getWidth() > 1 && getLegnth() > 1) {
			return false;
		}

		if (getWidth() == 1) {
			return true;
		}

		if (getHeight() == 1) {
			return true;
		}

		if (getLegnth() == 1) {
			return true;
		}

		return false;
	}

	public Display createDisplay() throws Region3DException {
		if (!is2D()) {
			throw new Region3DException();
		}

		Vector min = getMinimumPoint();
		Vector max = getMaximumPoint();
		World world = getWorld();
		int amount = 0;

		int minX = min.getX();
		int minY = min.getY();
		int minZ = min.getZ();
		int maxX = max.getX();
		int maxY = max.getY();
		int maxZ = max.getZ();

		DisplaySegment[] segments = new DisplaySegment[getWidth() * getHeight() * getLegnth()];
		Set<Chunk> chunks = new HashSet<Chunk>();

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					Location location = new Location(world, x, y, z);
					MapView map = Bukkit.getServer().createMap(world);
					short id = map.getId();

					ItemFrame it = (ItemFrame) world.spawnEntity(location, EntityType.ITEM_FRAME);
					it.teleport(location.getBlock().getRelative(direction).getLocation());
					it.setFacingDirection(direction, true);
					it.setItem(new ItemStack(Material.MAP, 1, id));
					chunks.add(it.getLocation().getChunk());

					// testing code for renderer
					for (MapRenderer mr : map.getRenderers()) {
						map.removeRenderer(mr);
					}
					map.addRenderer(new Renderer());

					segments[amount] = new DisplaySegment(id, it.getUniqueId());

					amount++;
				}
			}
		}
		for (Chunk chunk : chunks) { // Still seems to glitch
			chunk.unload(true, false);
			chunk.load();
			Bukkit.getLogger().info("Legnth: " + chunks.size());
		}

		return new Display(this, segments);
	}
}
