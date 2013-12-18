package plugin.nogtail.displays;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

public class Renderer extends MapRenderer {
	boolean updateNeeded = true;

	public Renderer() {
		super(true);
	}

	public void render(MapView view, MapCanvas canvas, Player player) {
		if (updateNeeded) {
			canvas.drawText(10, 10, MinecraftFont.Font, "§" + MapPalette.BLUE + ";Hi there\n§" + MapPalette.RED + ";I am a map\n§" + MapPalette.DARK_GREEN + ";And you are a human!");
			updateNeeded = false;
			player.sendMap(view);
		}
	}
}
