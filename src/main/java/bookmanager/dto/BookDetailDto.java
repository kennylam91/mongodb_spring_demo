package bookmanager.dto;

import java.util.Date;

import bookmanager.model.Category;
import bookmanager.model.Writer;
import lombok.Data;

@Data
public class BookDetailDto {

	private String bookId;
	
	private String title;
	
	private Integer noOfPages;
	
	private Date publishAt;
	
	private Category[] categoryList;
	
	private Writer writer;
}
