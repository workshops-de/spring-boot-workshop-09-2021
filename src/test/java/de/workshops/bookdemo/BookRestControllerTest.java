package de.workshops.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.workshops.bookdemo.book.BookRestController;

@SpringBootTest
class BookRestControllerTest {

	@Autowired
	private BookRestController controller; 
	    
	    
	@Test
	void test() {
		assertEquals(3, controller.getAllBooks().size());
	}

}
