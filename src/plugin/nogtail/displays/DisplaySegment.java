package plugin.nogtail.displays;

import java.util.UUID;

public class DisplaySegment {
	private short data;
	private UUID id;

	public DisplaySegment(short data, UUID id) {
		this.setData(data);
		this.setId(id);
	}

	public short getData() {
		return data;
	}

	public void setData(short data) {
		this.data = data;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}