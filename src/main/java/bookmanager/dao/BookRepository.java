package bookmanager.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bookmanager.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom{

}