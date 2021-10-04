package de.workshops.bookdemo.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppConfigTest {

	@Autowired
	private ApplicationConfig appConfig;
	
	
	@Test
	void testAppConfig() {
		assertEquals("value", appConfig.getParam1());
		assertEquals(42, appConfig.getParam2());
		assertEquals("Test", appConfig.getInner().getInnerParam1());
	}
}
