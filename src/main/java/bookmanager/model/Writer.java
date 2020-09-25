package bookmanager.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "writers")
public class Writer {
	
	@Transient
	public static final String SEQUENCE_NAME = "writer_sequence";
	
	@Id
	private Long writerId;
	
	@Field("writer_name")
	private String writerName;
	
	private Date dob;
	
	private String country;

}
