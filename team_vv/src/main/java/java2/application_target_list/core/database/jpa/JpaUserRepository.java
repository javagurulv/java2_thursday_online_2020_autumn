package java2.application_target_list.core.database.jpa;

import java2.application_target_list.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE Users u SET u.user_first_name =:newFirstName WHERE u.id =:userId", nativeQuery = true)
    int changeUserFirstName(@Param(value = "userId") Long userId,
                                @Param(value = "newFirstName") String newFirstName);

    @Modifying
    @Query(value = "UPDATE Users u SET u.user_last_name =:newLastName WHERE u.id =:userId", nativeQuery = true)
    int changeUserLastName(@Param(value = "userId") Long userId,
                           @Param(value = "newLastName") String newLastName);


    @Query(value = "SELECT * FROM Users u WHERE u.user_first_name LIKE %:userFirstName%", nativeQuery = true)
    List<User> findByFirstName(@Param(value = "userFirstName") String firstName);

    @Query(value = "SELECT * FROM Users u WHERE u.user_last_name LIKE %:userLastName%", nativeQuery = true)
    List<User> findByLastName(@Param("userLastName") String lastName);
}
