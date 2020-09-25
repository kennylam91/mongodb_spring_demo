package bookmanager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;

import bookmanager.dao.BookRepositoryCustom;
import bookmanager.dto.BookDetailDto;
import bookmanager.model.Writer;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<BookDetailDto> findBookDetails(Pageable pageable) {

		
		
		LookupOperation categoryLookup = LookupOperation.newLookup()
				.from("categories")
				.localField("categories")
				.foreignField("category_name")
				.as("categoryList")
				;
		LookupOperation writerLookup = LookupOperation
				.newLookup()
				.from(mongoTemplate.getCollectionName(Writer.class))
				.localField("writer_id")
				.foreignField("_id")
				.as("writer")
				;
		
		UnwindOperation writerUnwind = UnwindOperation.newUnwind().path("$writer").arrayIndex("0").preserveNullAndEmptyArrays();
	
		
		
		AggregationOperation project = Aggregation.project("title", "categoryList")
				.and("_id").as("bookId")
				.and("no_of_pages").as("noOfPages")
				.and("publish_at").as("publishAt")
				.and("writer").nested(Fields.fields("writer._id", "writer.writer_name"))
				;
		Aggregation aggregation = Aggregation.newAggregation(categoryLookup, writerLookup, writerUnwind, project);
		List<BookDetailDto> resultList = mongoTemplate.aggregate(aggregation, "books", BookDetailDto.class).getMappedResults();
		return resultList;
	}

}
