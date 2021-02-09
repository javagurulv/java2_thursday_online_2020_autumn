package adventure_time.database.guides;

import adventure_time.core.domain.Guides;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryGuides implements adventure_time.database.guides.DatabaseGuides {

    private long idCounter = 1L;
    private final List<Guides> guides = new ArrayList<>();

    public boolean add(Guides guides) {
        if (!this.guides.isEmpty()) {
            for (Guides item : this.guides) {
                if (item.getGuideName().equals(guides.getGuideName())) return false;
            }
        }
        guides.setGuideId(idCounter);
        this.guides.add(guides);
        idCounter++;
        return true;
    }

    @Override
    public boolean removeByName(String guideName) {
        return getGuidesList().removeIf(items -> items.getGuideName().equals(guideName));
    }

    @Override
    public boolean removeById(Long id) {
        return getGuidesList().removeIf(items -> items.getGuideId().equals(id));
    }

    public List<Guides> getGuidesList() {
        return guides;
    }

    @Override
    public List<Guides> findByGuideName(String guideName) {
         return guides.stream()
                    .filter(guide -> guide.getGuideName().equals(guideName))
                    .collect(Collectors.toList());
        }

    @Override
    public List<Guides> findByGuideEmail(String guideEmail) {
        return guides.stream()
                .filter(guide -> guide.getGuideEmail().equals(guideEmail))
                .collect(Collectors.toList());
    }

    @Override
    public List<Guides> findByGuideNameAndEmail(String guideName, String guideEmail) {
        return guides.stream()
                .filter(guide -> guide.getGuideName().equals(guideName))
                .filter(guide -> guide.getGuideEmail().equals(guideEmail))
                .collect(Collectors.toList());
    }
}