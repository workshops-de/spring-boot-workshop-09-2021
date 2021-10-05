package de.workshops.bookdemo.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryJpa extends CrudRepository<Book, Long>  {

}
