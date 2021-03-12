package internet_store.core.persistence;

import internet_store.core.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Boolean existsBySessionId(String sessionId);

    @Query(value = "SELECT * FROM session WHERE session_id=?", nativeQuery = true)
    List<Session> getSessionBySessionId(String sessionId);
}