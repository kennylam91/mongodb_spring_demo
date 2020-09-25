package bookmanager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bookmanager.dto.BookDetailDto;

public interface BookRepositoryCustom {

	List<BookDetailDto> findBookDetails(Pageable pageable);
}
