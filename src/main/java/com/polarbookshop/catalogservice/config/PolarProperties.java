package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class holds configuration data using the `@ConfigurationProperties`
 * annotation. So Spring Boot can find this automatically.
 *
 * The final property key names that will be used are prefixed with the
 * configured `prefix` from the annotation itself plus the name of the
 * field from the class definition. So in this example, the key would be
 * `polar.greeting`!
 *
 * When the fields are commented with JavaDoc, those comments can be converted
 * to metadata.
 */
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
	/**
	 * A message to welcome users.
	 */
	private String greeting;

	public String getGreeting() {
		return this.greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
