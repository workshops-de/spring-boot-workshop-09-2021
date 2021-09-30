package de.workshops.bookdemo.book;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(BookRestController.REQEST_URL)
public class BookRestController {
 
	public static final String REQEST_URL = "/book"; 
	
	private List<Book> books;
	
	@Autowired
    private ObjectMapper mapper;
    
    @PostConstruct
    public void init() throws Exception {
        this.books = Arrays.asList(mapper.readValue(new File("target/classes/books.json"), Book[].class));
    }
    
    @GetMapping
    public List<Book> getAllBooks() {
    	return this.books;
    }
    
    @GetMapping("/{isbn}")
    public Book getSingleBooks(@PathVariable String isbn) {
    	return this.books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow();
    }
    
    @GetMapping(params = "author")
    public Book getBookByAuthor(@RequestParam String author) throws BookException {
    	return this.books.stream().filter(book -> hasAuthor(book, author)).findFirst().orElseThrow(BookException::new);
    	//return ResponseEntity.status(201).body(bookResult);
    }
    
    @PostMapping("/search")
    public List<Book>  search(@RequestBody BookSearchRequest request) {
    	return this.books.stream().filter(book -> hasIsbnOrAuthor(book, request)).collect(Collectors.toList());

    }
    
    private boolean hasIsbnOrAuthor(Book book, BookSearchRequest request) {
        return book.getIsbn().equals(request.getIsbn()) 
        		&& book.getAuthor().contains(request.getAuthor()) ;
    }

    private boolean hasIsbn(Book book, String isbn) {
    	return book.getIsbn().equals(isbn);
    }
    
    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }
 
}