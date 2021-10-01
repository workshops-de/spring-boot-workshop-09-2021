package de.workshops.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.workshops.bookdemo.book.BookRestController;
import de.workshops.bookdemo.book.BookService;

@SpringBootTest
public class BookRestControllerIntegrationTest {

	@MockBean
	private BookService service;

	@Autowired
	private BookRestController controller;
	

	@Test
	void testGetAllBooks() {
		Mockito.when(service.loadAllBooks()).thenReturn(new ArrayList<>());
		
		// wenn @Mock anstatt@MockBean
		// ReflectionTestUtils.setField(controller, "bookService", service);
		
		
		assertEquals(0, controller.getAllBooks().size());
	}
}
