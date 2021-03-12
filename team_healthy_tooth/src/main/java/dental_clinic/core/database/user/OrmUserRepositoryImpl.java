package dental_clinic.core.database.user;

import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UserRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM Users u", User.class)
                .getResultList();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Users WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void setRole(Long userId, Long roleId) {
        String sql = "UPDATE Users SET role_id = :roleId";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("roleId", roleId);
        query.executeUpdate();
    }

    @Override
    public Role userRole(Long id) {
        String sql = "SELECT r FROM Roles r WHERE roles_id = " + id;
        return sessionFactory.getCurrentSession()
                .createQuery(sql, Role.class)
                .getSingleResult();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean containsUser(String login, String password) {
        return getUsers().stream()
                .anyMatch(user -> user.getLogin().equals(login) && user.getPassword().equals(password));
    }
}
