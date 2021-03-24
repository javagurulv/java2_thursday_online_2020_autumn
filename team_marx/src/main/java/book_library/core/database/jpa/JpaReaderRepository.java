package book_library.core.database.jpa;

import book_library.core.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReaderRepository extends JpaRepository<Reader, Long> {
}
