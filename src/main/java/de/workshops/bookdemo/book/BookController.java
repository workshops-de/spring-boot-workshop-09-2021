package de.workshops.bookdemo.book;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/booklist")
public class BookController {

	private List<Book> books;

	@Autowired
	private ObjectMapper mapper;

	@PostConstruct
	public void init() throws Exception {
		this.books = Arrays.asList(mapper.readValue(new File("target/classes/books.json"), Book[].class));
	}

	@GetMapping
	public String booklist(Model model, HttpServletResponse response) {
		model.addAttribute("books", this.books);
		return "booklist";
	}
}
