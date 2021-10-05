package de.workshops.bookdemo.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatasourceConfigTest {

	@Autowired
	@Qualifier("ds1")
	private DataSource ds1; 

	@Autowired
	@Qualifier("ds2")
	private DataSource ds2; 
	    
	@Test
	void testDs1() {
		assertNotNull(ds1);
		assertNotNull(ds2);
	}
}
