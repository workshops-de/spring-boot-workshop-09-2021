package de.workshops.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import de.workshops.bookdemo.book.BookRestController;
import de.workshops.bookdemo.book.BookService;

@ExtendWith(MockitoExtension.class)
public class BookRestControllerUnitTest {

	@Mock
	private BookService service;
	
	@InjectMocks
	private BookRestController controller;
	
	
	@Test
	void testGetAllBooks() {
		Mockito.when(service.loadAllBooks()).thenReturn(new ArrayList<>());
		assertEquals(0, controller.getAllBooks().size());
	}
}
