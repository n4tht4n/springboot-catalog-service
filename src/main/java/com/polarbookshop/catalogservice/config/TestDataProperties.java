package com.polarbookshop.catalogservice.config;

/**
 * To enable the use of nested custom properties, we need to build a corresponding
 * class hierachy :(.
 *
 * So this flag would be available as `polar.testdata.enabled`.
 */
public class TestDataProperties {
	private Boolean enabled;

	public Boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
