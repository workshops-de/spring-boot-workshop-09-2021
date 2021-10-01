package de.workshops.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.workshops.bookdemo.book.Book;
import de.workshops.bookdemo.book.BookRestController;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerTest {

	@Autowired
	private BookRestController controller;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void test() {
		assertEquals(3, controller.getAllBooks().size());
	}

	@Test
	void getAllBooks() throws Exception {
		// use static imports

		// @formatter:off
 		mockMvc.perform(MockMvcRequestBuilders.get(BookRestController.REQEST_URL))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].title", CoreMatchers.is("Clean Code")));
		// @formatter:on
	}

	@Test
	void getAllBooksAndresult() throws Exception {
		// @formatter:off
		MvcResult mvcResult = mockMvc.perform(get(BookRestController.REQEST_URL))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		// @formatter:on

		String jsonPayload = mvcResult.getResponse().getContentAsString();
		Book[] books = objectMapper.readValue(jsonPayload, Book[].class);
		assertEquals(3, books.length);
		assertEquals("Clean Code", books[1].getTitle());
	}

}
