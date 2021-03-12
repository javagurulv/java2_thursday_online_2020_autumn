package dental_clinic.core.database.role;

import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmRoleRepositoryImpl implements RoleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> getRoles() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM roles r", Role.class)
                .getResultList();
    }

    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Role r WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        Role role = sessionFactory.getCurrentSession().get(Role.class, id);
        return Optional.ofNullable(role);
    }
}
