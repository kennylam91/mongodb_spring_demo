package bookmanager.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bookmanager.model.Writer;

@Repository
public interface WriterRepository extends MongoRepository<Writer, String>{
	
}
