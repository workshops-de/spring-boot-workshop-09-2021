package de.workshops.bookdemo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> loadAllBooks() {
		return bookRepository.findAllBooks();
	}

	public Book loadBook(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}

	public List<Book> search(BookSearchRequest request) {
		return bookRepository.search(request);
	}

	public Book create(Book newBook) {
		return bookRepository.save(newBook);
	} 
	    
	    
}
