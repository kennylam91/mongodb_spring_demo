package bookmanager.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "books")
@Data
public class Book{
	
	@Transient
	public static final String SEQUENCE_NAME = "book_sequence";
	
	@Id
	private Long bookId;
	
	private String title;
	
	@Field("no_of_pages")
	private Integer noOfPages;
	
	@Field("writer_id")
	private Long writerId;
	
	private String[] categories;
	
	@Field("publish_at")
	private Date publishAt;
	
}
