package de.workshops.bookdemo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.workshops.bookdemo.book.Book;
import de.workshops.bookdemo.book.BookRestController;
import de.workshops.bookdemo.book.BookService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookRestControllerIntegrationTest {

	@MockBean
	private BookService service;

	@Autowired
	private BookRestController controller;
	

	@Test
	void testGetAllBooks() {
		Mockito.when(service.loadAllBooks()).thenReturn(new ArrayList<>());
		
		// wenn @Mock anstatt@MockBean
		// ReflectionTestUtils.setField(controller, "bookService", service);
		
		//assertEquals(0, controller.getAllBooks().size());
	}
	
	@Test
	void testCreateBook() {
		// @formatter:off
		Book book = Book.builder()
				.author("Autor")
				.title("Titel")
				.isbn("1234567890")
				.description("Test")
				.build();
		
		RestAssured
			.given()
				.log().all()
				.body(book)
				.contentType(ContentType.JSON)
			.when()
				.put(BookRestController.REQEST_URL)
			.then()
				.log().all()
				.statusCode(200);
		
		// @formatter:on
		
	}
}
