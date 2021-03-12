package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;

import java.util.List;
import java.util.Optional;

public interface ManipulationRepository {

    void addManipulation(Manipulation manipulation);

    List <Manipulation> getManipulationsList();

    Optional<Manipulation> getManipulationById (Long id);

    void deactivateManipulation(Long id);

    boolean containsTheSameManipulation (String manipulationType, Integer price);

    boolean manipulationIsActive(Long id);

    boolean containsId (Long id);

}
