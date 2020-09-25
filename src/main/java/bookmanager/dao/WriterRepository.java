package bookmanager.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import bookmanager.model.Writer;

@Repository
public interface WriterRepository extends MongoRepository<Writer, Long>{
	
	Page<Writer> findByWriterNameContainingAndCountry(String writerName, @Nullable String country, Pageable pageable);
}
