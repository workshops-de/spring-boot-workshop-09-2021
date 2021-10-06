package de.workshops.bookdemo.book;

import static de.workshops.bookdemo.generated.Tables.BOOK;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryJooq {

	@Autowired
	private DSLContext jooq;
	
	public de.workshops.bookdemo.generated.tables.pojos.Book findByIsbn(String isbn) {
		return jooq.select(BOOK.fields()).from(BOOK).where(BOOK.ISBN.eq(isbn)).fetchSingleInto(de.workshops.bookdemo.generated.tables.pojos.Book.class);
	}
	    
	    
}
