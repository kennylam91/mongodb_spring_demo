package bookmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "categories")
public class Category {

	@Transient
	public static final String SEQUENCE_NAME = "category_sequence";

	@Id
	@Field("category_id")
	private Long categoryId;

	@Field("category_name")
	private String categoryName;

}
