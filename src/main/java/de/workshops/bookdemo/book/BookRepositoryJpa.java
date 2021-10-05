package de.workshops.bookdemo.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryJpa extends CrudRepository<Book, Long>  {

	@Query(nativeQuery = true, value = "select * from book where isbn = :isbn")
	public Book findByIsbn(@Param("isbn") String isbn);
	
	
}
