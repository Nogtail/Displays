package plugin.nogtail.displays;

import java.util.UUID;

public class DisplaySegment {
	private short id;
	private UUID uid;

	public DisplaySegment(short id, UUID uid) {
		this.id = id;
		this.uid = uid;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public UUID getUniqueId() {
		return uid;
	}

	public void setUniqueId(UUID uid) {
		this.uid = uid;
	}
}