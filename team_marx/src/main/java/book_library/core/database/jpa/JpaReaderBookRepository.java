package book_library.core.database.jpa;

import book_library.core.domain.ReaderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReaderBookRepository extends JpaRepository<ReaderBook, Long> {
}
