package de.workshops.bookdemo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.workshops.bookdemo.book.Book;
import de.workshops.bookdemo.book.BookRestController;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class BookRestControllerTest {

//	@TestConfiguration
//    static class Config {
//         @Bean
//        public ObjectMapper mapper() {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper = mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//            return mapper;
//           }
//    }
	
	@Autowired
	private BookRestController controller;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@LocalServerPort
	int port;
	

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
		log.debug(jsonPayload);
		Book[] books = objectMapper.readValue(jsonPayload, Book[].class);
		assertEquals(3, books.length);
		assertEquals("Clean Code", books[1].getTitle());
	}
	
	@Test
	void testWithRestAssuredMockMvc() {
	        RestAssuredMockMvc.standaloneSetup(controller);
	        RestAssuredMockMvc.given().
	             log().all().
	        when().
	             get("/book").
	        then().
	             log().all().
	             statusCode(200).
	             body("author[0]", equalTo("Erich Gamma"));
	}

	@Test
	void testWithRestAssuredRealHttp() {
		RestAssured.given()
			.port(port)
			.log().all().
		when().
			get("/book").
		then().
			log().all().
			statusCode(200).
			body("author[0]", equalTo("Erich Gamma"));
	}

}
