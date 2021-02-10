package adventure_time.core.services.guides;

import adventure_time.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayGuideListService {

    @Autowired
    private DatabaseGuides databaseGuides;

//    public DisplayGuideListService(DatabaseGuides databaseGuides) {
//        this.databaseGuides = databaseGuides;
//    }

    public List<Guides> getGuidesList() {
        return databaseGuides.getGuidesList();
    }

}
