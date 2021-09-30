package de.workshops.bookdemo.book;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookRepository {

	private List<Book> books;
	
	@Autowired
    private ObjectMapper mapper;
    
	    
	    
    @PostConstruct
    public void init() throws Exception {
        this.books = Arrays.asList(mapper.readValue(new File("target/classes/books.json"), Book[].class));
    }

	public List<Book> findAllBooks() {
		return this.books;
	}
	
    public Book findBookByIsbn(String isbn) {
    	return this.books.stream().filter(book -> hasIsbn(book, isbn)).findFirst().orElseThrow();
    }

    public List<Book> search(BookSearchRequest request) {
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
