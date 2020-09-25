package bookmanager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;

import bookmanager.dao.BookRepositoryCustom;
import bookmanager.dto.BookDetailDto;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<BookDetailDto> findBookDetails(Pageable pageable) {

		
//		Aggregation convertAgg = ConvertOperators.valueOf("_id").convertToObjectId().
		
//		AddFieldsOperation addFieldOperation = AddFieldsOperation.addField("writerObjId").equals(obj)
		LookupOperation lookupOperation = LookupOperation.newLookup()
				.from("category")
				.localField("categories")
				.foreignField("categoryName")
				.as("categoryList");
		Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
		List<BookDetailDto> resultList = mongoTemplate.aggregate(aggregation, "book", BookDetailDto.class).getMappedResults();
		return resultList;
	}

}
