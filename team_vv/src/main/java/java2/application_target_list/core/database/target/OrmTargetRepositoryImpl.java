package java2.application_target_list.core.database.target;

import java2.application_target_list.core.domain.Target;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class OrmTargetRepositoryImpl implements TargetRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTarget(Target target) {
        sessionFactory.getCurrentSession().save(target);
    }

    @Override
    public boolean deleteTarget(Long targetId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Target WHERE id = :targetId");
        query.setParameter("targetId", targetId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean changeTargetName(Long targetId, String newTargetName) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Target SET target_name =: newTargetName " +
                "WHERE id =: targetId");
        query.setParameter("newTargetName", newTargetName);
        query.setParameter("targetId", targetId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean changeTargetDescription(Long targetId, String newTargetDescription) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Target SET target_description =: newTargetDescription " +
                "WHERE id =: targetId");
        query.setParameter("newTargetDescription", newTargetDescription);
        query.setParameter("targetId", targetId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean changeTargetDeadline(Long targetId, Long newTargetDeadline) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Target SET target_deadline =: newTargetDeadline " +
                "WHERE id =: targetId");
        query.setParameter("newTargetDeadline", newTargetDeadline);
        query.setParameter("targetId", targetId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Target> getTargetsList() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Target", Target.class)
                .getResultList();
    }

    @Override
    public boolean isIdInTargetList(Long targetId) {
        List<Target> targets = getTargetsList();

        for (Target tempTarget : targets) {
            if (tempTarget.getId().equals(targetId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Target> findByTargetName(String targetName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Target WHERE target_name LIKE :targetName");
        query.setParameter("targetName","%" + targetName + "%");
        return query.getResultList();
    }

    @Override
    public List<Target> findByTargetDescription(String targetDescription) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Target WHERE target_description LIKE :targetDescription");
        query.setParameter("targetDescription","%" + targetDescription + "%");
        return query.getResultList();
    }

    @Override
    public Optional<Target> getById(Long id) {
        Target target = sessionFactory.getCurrentSession().get(Target.class, id);
        if (target == null) {
            return Optional.empty();
        } else {
            return Optional.of(target);
        }
    }
}
