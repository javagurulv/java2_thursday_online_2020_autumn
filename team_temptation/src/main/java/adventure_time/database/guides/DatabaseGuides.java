package adventure_time.database.guides;

import adventure_time.core.domain.Guides;

import java.util.List;

public interface DatabaseGuides {

    boolean add(Guides guide);

    boolean removeByName (String eventName);

    boolean removeById (Long id);

    List<Guides> getGuidesList();

    List<Guides> findByGuideName(String guideName);

    List<Guides> findByGuideEmail(String guideEmail);

    List<Guides> findByGuideNameAndEmail(String guideName, String guideEmail);
}