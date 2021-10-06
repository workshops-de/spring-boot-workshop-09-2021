package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepositoryJooq bookRepository;

	public Iterable<Book> loadAllBooks() {
		return null; //bookRepository.findAll();
	}

	public de.workshops.bookdemo.generated.tables.pojos.Book loadBook(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}

	public List<Book> search(BookSearchRequest request) {
		// return bookRepository.search(request);
		return null;
	}

	public Book create(Book newBook) {
		return null; //bookRepository.save(newBook);
	} 
	    
	    
}
