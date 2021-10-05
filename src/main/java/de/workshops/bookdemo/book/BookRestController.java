package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BookRestController.REQEST_URL)
@Slf4j
public class BookRestController {
 
	public static final String REQEST_URL = "/book"; 
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private  ObjectMapper mapper;
	
	
	
    @GetMapping
    public Iterable<Book> getAllBooks() {
    	return bookService.loadAllBooks();
    }
    
    @GetMapping("/{isbn}")
    public Book getSingleBooks(@PathVariable String isbn) {
    	return bookService.loadBook(isbn);
    }
    
    @PostMapping("/search")
    public List<Book>  search(@RequestBody BookSearchRequest request) {
    	return bookService.search(request);
    }

    @PostMapping()
    public Book create(@RequestBody Book newBook) {
    	return bookService.create(newBook);
    }
    
    @PutMapping("/{isbn}")
    public Book update(@RequestBody Book book, @PathVariable String isbn) throws BookException {
    	if (isbn.equals(book.getIsbn())) {
    		return bookService.create(book);    		
    	} else {
    		throw new BookException("Nicht erlaubt");
    	}
    }
    
}