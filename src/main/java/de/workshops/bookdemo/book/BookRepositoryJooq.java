package de.workshops.bookdemo.book;

import static de.workshops.bookdemo.generated.public_.Tables.BOOK;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.workshops.bookdemo.generated.public_.tables.pojos.Book;

@Repository
public class BookRepositoryJooq {

	@Autowired
	private DSLContext jooq;
	
	public Book findByIsbn(String isbn) {
		return jooq.select(BOOK.fields()).from(BOOK).where(BOOK.ISBN.eq(isbn)).fetchSingleInto(Book.class);
	}
	    
	    
}
