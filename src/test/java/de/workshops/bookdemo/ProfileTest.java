package de.workshops.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import springfox.documentation.spring.web.plugins.Docket;

@SpringBootTest
@ActiveProfiles("prod")
class ProfileTest {

	@Value("${server.port}")
	private int port;
	
	@Autowired(required = false)
	private Docket swaggerDocket; 
	
	@Autowired
	private ApplicationContext context; 
	
	
	@Test
	void testPort() {
		assertEquals(8081,  port);
	}

	@Test
	void testSwaggerBean() {
		assertNull(swaggerDocket);
		assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(Docket.class));
	}

}
