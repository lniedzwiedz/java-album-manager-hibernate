package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

import java.util.List;

public class FriendRepository {

    private final Session session;

    public FriendRepository(Session session) {
        this.session = session;
    }

    public User getUserByName(String userName) {
        Query<User> query = session.createQuery("FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", userName);
        return query.uniqueResult();
    }

    public User getUserById(int userId) {
        Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", userId);
        return query.uniqueResult();
    }

    public List<User> getAllUsers() {
        Query<User> query = session.createQuery("FROM User", User.class);
        return query.list();
    }

    public void addFriend(User user, User friend) {
        Transaction transaction = session.beginTransaction();
        user.addUser(friend);
        session.save(user);
        transaction.commit();
    }

    public void deleteFriend(User user, User friend) {
        Transaction transaction = session.beginTransaction();
        user.removeUser(friend);
        friend.removeUser(user);
        session.save(user);
        session.save(friend);
        transaction.commit();
    }

    public boolean areFriends(User user, User friend) {
//        if (user == null || friend == null)
//            return false;
//        if (user.equals(friend))
//            return true;
//        if (user.getUsers().contains(friend))
//            return true;
//        return friend.getUsers().contains(user);
        if (user == null || friend == null)
            return false;

        return user.equals(friend)
                || user.getUsers().contains(friend)
                || friend.getUsers().contains(user);
    }

    public List<User> getFriends(User user) {
        List<User> friends = new java.util.ArrayList<>();
        if (user == null)
            return friends;
        List<User> users = getAllUsers();
        for (User otherUser : users) {
            if (otherUser.getUsers().contains(user)) {
                if (!friends.contains(otherUser)) {
                    friends.add(otherUser);
                }
            }
        }
        for (User friend : user.getUsers()) {
            if (!friends.contains(friend))
                friends.add(friend);
        }
        return friends;
    }
}