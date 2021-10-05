package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepositoryJpa bookRepository;

	public Iterable<Book> loadAllBooks() {
		return bookRepository.findAll();
	}

	public Book loadBook(String isbn) {
		// return bookRepository.findBookByIsbn(isbn);
		return null;
	}

	public List<Book> search(BookSearchRequest request) {
		// return bookRepository.search(request);
		return null;
	}

	public Book create(Book newBook) {
		return bookRepository.save(newBook);
	} 
	    
	    
}
