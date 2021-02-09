package adventure_time.ui.guides;

import adventure_time.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUpGuideUIAction implements UIAction {

    @Autowired
    private DatabaseGuides databaseGuides;

//    public StartUpGuideUIAction(DatabaseGuides databaseGuides) {
//        this.databaseGuides = databaseGuides;
//    }

    @Override
    public void execute() {
        Guides guides = new Guides("Alex","alex@guides.com","+37112345678");
        databaseGuides.add(guides);

        guides = new Guides("Alex33","aleksej22@guides.com","+37123456789");
        databaseGuides.add(guides);

        guides = new Guides("Alex","alex@guides.com","+37112345678");
        databaseGuides.add(guides);

        guides = new Guides("Alex2","alex2@guides.com","+37123456789");
        databaseGuides.add(guides);

        guides = new Guides("Alexandr","alexandr@guides.com","+37145678900");
        databaseGuides.add(guides);

        guides = new Guides("Boris","boris@guides.com","+371456789001");
        databaseGuides.add(guides);

        guides = new Guides("Bogdan","bogdan@guides.com","+37156789002");
        databaseGuides.add(guides);

        guides = new Guides("Susanin","susanin@guides.com","+371666000666");
        databaseGuides.add(guides);
    }
}