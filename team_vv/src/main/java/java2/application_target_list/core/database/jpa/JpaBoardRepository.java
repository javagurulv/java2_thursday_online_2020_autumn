package java2.application_target_list.core.database.jpa;

import java2.application_target_list.core.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaBoardRepository extends JpaRepository<Record, Long> {

    @Modifying
    @Query(value = "UPDATE Targets_board r SET r.target_date_of_completion =:date WHERE r.id =:recordId", nativeQuery = true)
    int setRecordCompleteDate(@Param(value = "recordId") Long recordId,
                               @Param(value = "date") String date);

    @Query(value = "SELECT * From Targets_board r WHERE r.target_date_of_completion IS null", nativeQuery = true)
    List<Record> findUnfinishedRecords();
}
