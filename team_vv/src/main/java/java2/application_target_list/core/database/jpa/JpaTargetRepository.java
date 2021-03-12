package java2.application_target_list.core.database.jpa;

import java2.application_target_list.core.domain.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaTargetRepository extends JpaRepository<Target, Long> {

    @Modifying
    @Query(value = "UPDATE Targets t SET t.target_name =:newTargetName WHERE t.id =:targetId", nativeQuery = true)
    int changeTargetName(@Param(value = "targetId") Long targetId,
                            @Param(value = "newTargetName") String newTargetName);

    @Modifying
    @Query(value = "UPDATE Targets t SET t.target_description =:newTargetDescription WHERE t.id =:targetId", nativeQuery = true)
    int changeTargetDescription(@Param(value = "targetId") Long targetId,
                                    @Param(value = "newTargetDescription") String newTargetDescription);


    @Modifying
    @Query(value = "UPDATE Targets t SET t.target_deadline =:newTargetDeadline WHERE t.id =:targetId", nativeQuery = true)
    int changeTargetDeadline(@Param(value = "targetId") Long targetId,
                                 @Param(value = "newTargetDeadline") Long newTargetDeadline);

    @Query(value = "SELECT * FROM Targets t WHERE t.target_name LIKE %:targetName%", nativeQuery = true)
    List<Target> findByName(@Param(value = "targetName") String targetName);

    @Query(value = "SELECT * FROM Targets t WHERE t.target_description LIKE %:targetDescription%", nativeQuery = true)
    List<Target> findByDescription(@Param(value = "targetDescription") String targetDescription);
}
