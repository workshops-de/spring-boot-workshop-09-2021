package de.workshops.bookdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "myapp")
@Data
public class ApplicationConfig {

	/**
	 * Der Parameter 1.
	 */
	private String param1;
	
	private Integer param2;
	
	private InnerConfig inner;
	
	@Getter
	@Setter
	public static class InnerConfig {
		private String innerParam1;
	}
}
