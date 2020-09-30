package com.provigil.enums;

/** Location Enums
 * @author abhilash.bachu
 *
 */
public enum Location {
	INDOOR("Indoor"), OUTDOOR("Outdoor");

	private String location;

	Location(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}
}
