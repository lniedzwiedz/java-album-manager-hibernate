package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

import java.util.List;

public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getUser(String userName) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User u WHERE u.name = :name", User.class);
            query.setParameter("name", userName);
            return query.uniqueResult();
        }
    }

    public User getUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", userId);
            return query.uniqueResult();
        }
    }

    public List<User> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    public boolean exists(String userName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.name = :name", Long.class);
            query.setParameter("name", userName);
            return query.uniqueResult() > 0;
        }
    }

//    public void save(String userName) {
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
//                User user = new User();
//                user.setName(userName);
                session.save(user);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public void delete(User user) {
        if (user == null)
            return;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                User managedUser = session.get(User.class, user.getId());
                if (managedUser != null)
                    session.delete(managedUser);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }
}