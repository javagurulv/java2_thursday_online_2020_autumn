package java2.application_target_list.core.database.user;

import java2.application_target_list.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class OrmUserRepositoryImpl implements UserRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete User where id = :userId");
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean changeUserFirstName(Long userId, String newFirstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE User set user_first_name =: newFirstName " +
                        "WHERE id =: userId"
        );
        query.setParameter("newFirstName", newFirstName);
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean changeUserLastName(Long userId, String newLastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE User set user_last_name =: newLastName " +
                        "WHERE id =: userId"
        );
        query.setParameter("newLastName", newLastName);
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<User> getUsersList() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }


    @Override
    public List<User> findUserByFirstName(String userFirstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM User WHERE user_first_name LIKE :userFirstName");
        query.setParameter("userFirstName","%" + userFirstName + "%");
        return query.getResultList();
    }

    @Override
    public List<User> findUserByLastName(String userLastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM User WHERE user_last_name LIKE :userLastName");
        query.setParameter("userLastName", "%" + userLastName+ "%");
        return query.getResultList();
    }

    @Override
    public boolean isIdInUserList(Long userId) {
        List<User> users = getUsersList();

        for (User tempUser : users) {
            if (tempUser.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<User> getById(Long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }
}
