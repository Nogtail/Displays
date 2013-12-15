package plugin.nogtail.displays;

public class Display {
	public Region region;
	private DisplaySegment[] segments;
	private int id;

	public Display(Region region, DisplaySegment[] segments) {
		this(region, segments, 10); // TODO generate random ID
	}

	public Display(Region region, DisplaySegment[] segments, int id) {
		this.region = region;
		this.setId(id);
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public DisplaySegment[] getSegments() {
		return segments;
	}

	public void setSegments(DisplaySegment[] segments) {
		this.segments = segments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}