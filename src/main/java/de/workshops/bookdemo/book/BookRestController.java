package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookRestController.REQEST_URL)
public class BookRestController {
 
	public static final String REQEST_URL = "/book"; 
	
	@Autowired
	private BookService bookService;
	

    @GetMapping
    public List<Book> getAllBooks() {
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
    
 
}