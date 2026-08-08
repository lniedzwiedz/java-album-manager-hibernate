package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

import java.util.ArrayList;
import java.util.List;

public class FriendRepository {

    private final SessionFactory sessionFactory;

    public FriendRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
//
//    public User getUser(String userName) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery("FROM User u WHERE u.name = :name", User.class);
//            query.setParameter("name", userName);
//            return query.uniqueResult();
//        }
//    }
//
//    public User getUser(int userId) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
//            query.setParameter("id", userId);
//            return query.uniqueResult();
//        }
//    }
//
//    public List<User> getUsers() {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery("FROM User", User.class);
//            return query.list();
//        }
//    }

    public void addFriend(User user, User friend) {
        if (user == null || friend == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                User managedUser = session.get(User.class, user.getId());
                User managedFriend = session.get(User.class, friend.getId());

                if (managedUser == null || managedFriend == null) {
                    transaction.rollback();
                    return;
                }

                managedUser.addUser(managedFriend);
                session.save(managedUser);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public void deleteFriend(User user, User friend) {
        if (user == null || friend == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                User managedUser = session.get(User.class, user.getId());
                User managedFriend = session.get(User.class, friend.getId());

                if (managedUser == null || managedFriend == null) {
                    transaction.rollback();
                    return;
                }
                managedUser.removeUser(managedFriend);
                managedFriend.removeUser(managedUser);

                session.save(managedUser);
                session.save(managedFriend);

                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public boolean areFriends(User user, User friend) {
        if (user == null || friend == null)
            return false;

        try (Session session = sessionFactory.openSession()) {
            User managedUser = session.get(User.class, user.getId());
            User managedFriend = session.get(User.class, friend.getId());
            if (managedUser == null || managedFriend == null)
                return false;

            return managedUser.equals(managedFriend) || managedUser.getUsers().contains(managedFriend) || managedFriend.getUsers().contains(managedUser);
        }
    }

    public List<User> getFriends(User user) {
        List<User> friends = new ArrayList<>();
        if (user == null)
            return friends;

        try (Session session = sessionFactory.openSession()) {

            User managedUser = session.get(User.class, user.getId());
            if (managedUser == null)
                return friends;

            Query<User> query = session.createQuery("FROM User", User.class);

            List<User> users = query.list();
            for (User otherUser : users) {
                if (otherUser.getUsers().contains(managedUser) && !friends.contains(otherUser))
                    friends.add(otherUser);
            }

            for (User friend : managedUser.getUsers()) {
                if (!friends.contains(friend))
                    friends.add(friend);
            }
            return friends;
        }
    }
}