package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

import java.util.List;

public class UserRepository {

    private final Session session;

    public UserRepository(Session session) {
        this.session = session;
    }

    public User getUserFromDatabase(String userName) {
        Query<User> query = session.createQuery("FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", userName);
        return query.uniqueResult();
    }

    public User getUserFromDatabase(int userId) {
        Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", userId);
        return query.uniqueResult();
    }

    public List<User> getUsersFromDatabase() {
        Query<User> query = session.createQuery("FROM User", User.class);
        return query.list();
    }

    public boolean isUserExists(String userName) {
        return getUserFromDatabase(userName) != null;
    }

    public void addUser(String userName) {
        User user = new User();
        user.setName(userName);

        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteUser(User user) {
        if (user == null)
            return;

        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}